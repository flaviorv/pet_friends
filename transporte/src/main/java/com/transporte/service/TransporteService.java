package com.transporte.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transporte.domain.Pedido;
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
        pedidoProducer.responderPedido(transporte);
    }

    public Iterable<Pedido> obterPedidos() {
        return pedidoRepository.findAll();
    }

    public void entregarPedido(String idPedido) throws InterruptedException, JsonProcessingException {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        Transporte transporte = new Transporte();
        if (pedido.isPresent()) {
            transporte.entregarPedido(pedido.get());
            transporteRepository.save(transporte);
//            Thread.sleep(5000);
            Transporte salvo = transporteRepository.buscarTransporteEntregue(pedido.get().getId());
            pedidoProducer.responderPedido(salvo);
        }else {
            throw new RuntimeException("Pedido não encontrado");
        }

    }

}
