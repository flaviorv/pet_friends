package com.almoxarifado.Service;

import com.almoxarifado.domain.Pedido;
import com.almoxarifado.domain.Produto;
import com.almoxarifado.domain.Status;
import com.almoxarifado.infra.EstoqueRepository;
import com.almoxarifado.domain.EstoqueProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Optional<EstoqueProduto> obterEstoque(int produtoId) {
        return estoqueRepository.findById(produtoId);
    }

    public Iterable<EstoqueProduto> obterEstoqueCompleto() {
        return estoqueRepository.findAll();
    }

    public void adicionarProdutos(int produtoId, int unidades) {
        Optional<EstoqueProduto> produtoExistente = estoqueRepository.findById(produtoId);
        if(produtoExistente.isPresent()) {
            produtoExistente.get().adicionarUnidadesAProdutoExistente(unidades);
            estoqueRepository.save(produtoExistente.get());
        }else {
            EstoqueProduto novoProduto = new EstoqueProduto(produtoId, unidades);
            estoqueRepository.save(novoProduto);
        }
    }

    public void prepararProduto(int produtoId, int unidades) {
        Optional<EstoqueProduto> produtoExistente = estoqueRepository.findById(produtoId);
        if(produtoExistente.isPresent()) {
            EstoqueProduto estoque = produtoExistente.get();
            estoque.prepararProduto(produtoId, unidades);
            estoqueRepository.save(estoque);
        }else {
            throw new RuntimeException("Produto não encontrado.");
        }
    }

    public Status prepararPedido(Pedido pedido) {
        for(Produto produto : pedido.getProdutos()) {
            Optional<EstoqueProduto> estoque = obterEstoque(produto.getId());
            if(estoque.isEmpty()) {
                pedido.setStatus(Status.CANCELADO);
                return Status.CANCELADO;
            }
            prepararProduto(produto.getId(), estoque.get().getUnidades());
        }
        pedido.setStatus(Status.EM_TRANSITO);
        return Status.EM_TRANSITO;
    }
}
