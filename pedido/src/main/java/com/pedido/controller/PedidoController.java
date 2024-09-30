package com.pedido.controller;

import com.pedido.domain.Pedido;
import com.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public Iterable<Pedido> obterTodos() {
        return pedidoService.obterTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obterPorId(@PathVariable String id) {
        return pedidoService.obter(id);
    }

    @PostMapping("/{produtoId}")
    public void novoPedido(@PathVariable int produtoId) {
        pedidoService.adicionarProduto(produtoId);
    }

    @PostMapping("/{pedidoId}/{produtoId}")
    public void adicionarProduto(@PathVariable String pedidoId, @PathVariable int produtoId) {
        pedidoService.adicionarProduto(pedidoId, produtoId);
    }

    @DeleteMapping
    public void excluir(@PathVariable String pedidoId) {
        pedidoService.excluir(pedidoId);
    }

    @PostMapping("/{pedidoId}/fechar-pedido")
    public void fecharPedido(@PathVariable String pedidoId) {
        pedidoService.fecharPedido(pedidoId);
    }
}
