package com.almoxarifado.Service;

import com.almoxarifado.Repository.EstoqueRepository;
import com.almoxarifado.domain.Estoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Optional<Estoque> estoque(int produtoId) {
        return estoqueRepository.findById(produtoId);
    }

    public Iterable<Estoque> estoqueCompleto() {
        return estoqueRepository.findAll();
    }

    public void adicionarProdutos(int produtoId, int unidades) {
        Optional<Estoque> produtoExistente = estoqueRepository.findById(produtoId);
        if(produtoExistente.isPresent()) {
            produtoExistente.get().adicionarUnidadesAProdutoExistente(unidades);
            estoqueRepository.save(produtoExistente.get());
        }else {
            Estoque novoProduto = new Estoque(produtoId, unidades);
            estoqueRepository.save(novoProduto);
        }
    }

    public void prepararProdutos(int produtoId, int unidades) {
        Optional<Estoque> produtoExistente = estoqueRepository.findById(produtoId);
        if(produtoExistente.isPresent()) {
            produtoExistente.get().prepararProduto(unidades);
        }else {
            throw new RuntimeException("Produto não encontrado.");
        }
    }
}
