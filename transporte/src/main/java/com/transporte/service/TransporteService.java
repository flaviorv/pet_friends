package com.transporte.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transporte.domain.Pedido;
import com.transporte.domain.Status;
import com.transporte.domain.TransporteEvento;
import com.transporte.domain.Transporte;
import com.transporte.infra.PedidoRepository;
import com.transporte.infra.TransporteRepository;
import com.transporte.infra.rabbit.PedidoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransporteService {
    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoProducer pedidoProducer;

    public void iniciarTransporte(Pedido pedido) throws JsonProcessingException {
        Transporte transporte = new Transporte();
        transporte.iniciarTransporte(pedido);
        pedidoRepository.save(pedido);
        transporteRepository.save(transporte);
        TransporteEvento evento = new TransporteEvento(transporte);
        pedidoProducer.responderPedido(evento);
    }

    public Iterable<Pedido> obterPedidos() {
        return pedidoRepository.findAll();
    }

    public void entregarPedido(String idPedido) throws InterruptedException, JsonProcessingException {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if (pedido.isPresent()) {
            Transporte transporte = new Transporte();
            Transporte entregue = transporteRepository.buscarTransporteEntregue(pedido.get().getId());
            if (entregue == null) {
                transporte.entregarPedido(pedido.get());
                transporteRepository.save(transporte);
    //            Thread.sleep(5000);
                TransporteEvento evento = new TransporteEvento(transporte);
                pedidoProducer.responderPedido(evento);
            }else {
                throw new RuntimeException("Pedido já foi entregue");
            }
        }else {
            throw new RuntimeException("Pedido nao encontrado");
        }

    }

}
