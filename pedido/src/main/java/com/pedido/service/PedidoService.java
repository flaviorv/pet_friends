package com.pedido.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pedido.domain.Pedido;
import com.pedido.domain.Produto;
import com.pedido.domain.Status;
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

    public Iterable<Pedido> obterTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obter(String id) {
        return pedidoRepository.findById(id);
    }

    public void excluir(String id) {
        pedidoRepository.deleteById(id);
    }

    public void adicionarProduto(int produtoId) {
        Pedido pedido = new Pedido();
        pedido.setStatus(Status.NOVO);
        Produto produto = produtoClient.buscar(produtoId);
        pedido.adicionarProduto(produto);
        pedidoRepository.save(pedido);
    }

    public void adicionarProduto(String pedidoId, int produtoId) {
        Optional<Pedido> pedido = obter(pedidoId);
        Produto produto = produtoClient.buscar(produtoId);
        pedido.ifPresent(_pedido -> {
            _pedido.adicionarProduto(produto);
            pedidoRepository.save(_pedido);
        });
    }

    public void fecharPedido(String pedidoId) {
        Optional<Pedido> pedido = obter(pedidoId);
        pedido.ifPresent(_pedido -> {
            if(_pedido.getStatus() == Status.NOVO){
                _pedido.setStatus(Status.FECHADO);
                try {
                    almoxarifadoProducer.enviarPedidoParaAlmoxarifado(_pedido);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }else {
                throw new RuntimeException("Pedido está: "+_pedido.getStatus());
            }
        });
    }

}
