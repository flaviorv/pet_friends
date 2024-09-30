package com.produto.service;

import com.produto.domain.Produto;
import com.produto.infra.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Iterable<Produto> obterTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> obter(int id) {
        return produtoRepository.findById(id);
    }

    public Produto adicionar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void remover(int id) {
        produtoRepository.deleteById(id);
    }

}
