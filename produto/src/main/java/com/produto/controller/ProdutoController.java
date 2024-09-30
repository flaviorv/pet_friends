package com.produto.controller;

import com.produto.domain.Produto;
import com.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public Iterable<Produto> obterTodos() {
        return produtoService.obterTodos();
    }

    @GetMapping("/{id}")
    public Optional<Produto> obterPorId(@PathVariable int id) {
        return produtoService.obter(id);
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto) {
        return produtoService.adicionar(produto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id) {
        produtoService.remover(id);
    }
}
