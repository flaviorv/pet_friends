package com.pedido.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pedido.domain.*;
import com.pedido.infra.ClienteClient;
import com.pedido.infra.PedidoRepository;
import com.pedido.infra.ProdutoClient;
import com.pedido.infra.rabbit.AlmoxarifadoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private AlmoxarifadoProducer almoxarifadoProducer;

    @Autowired
    private ProdutoClient   produtoClient;

    @Autowired
    private ClienteClient clienteClient;

    public Iterable<Pedido> obterTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obter(String id) {
        return pedidoRepository.findById(id);
    }

    public void excluir(String id) {
        pedidoRepository.deleteById(id);
    }

    public void adicionarProduto(int produtoId, int clienteId) {
        Pedido pedido = new Pedido();
        Cliente cliente = clienteClient.buscar(clienteId);
        Produto produto = produtoClient.buscar(produtoId);
        pedido.adicionarProduto(produto);
        pedido.setClienteId(cliente.getId());
        System.out.println("Cliente " + cliente.getNome() + " fez um novo pedido.");
        pedidoRepository.save(pedido);
    }

    public void adicionarProduto(String pedidoId, int produtoId) {
        Optional<Pedido> pedido = obter(pedidoId);
        Produto produto = produtoClient.buscar(produtoId);
        pedido.ifPresent(_pedido -> {
            _pedido.adicionarProduto(produto);
        });
    }

    public void fecharPedido(String pedidoId) {
        Optional<Pedido> pedido = obter(pedidoId);
        pedido.ifPresent(_pedido -> {
            if(_pedido.getStatus() == Status.NOVO){
                _pedido.setStatus(Status.FECHADO);
                pedidoRepository.save(_pedido);
                try {
                    almoxarifadoProducer.enviarPedidoParaAlmoxarifado(_pedido);
                } catch (JsonProcessingException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public void atualizarStatusPedido(Pedido pedidoRecebido) {
        Pedido pedidoSalvo = pedidoRepository.findById(pedidoRecebido.getId()).get();
        pedidoSalvo.setStatus(pedidoRecebido.getStatus());
        pedidoRepository.save(pedidoSalvo);
    }

    public void atualizarStatusPedido(TransporteEvents evento) {
        Pedido pedido = pedidoRepository.findById(evento.getIdPedido()).get();
        pedido.setStatus(evento.getStatus());
        pedidoRepository.save(pedido);
    }

}
