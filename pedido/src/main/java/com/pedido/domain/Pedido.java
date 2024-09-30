package com.pedido.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String id;
    private Status status;
    private BigDecimal precoTotal = BigDecimal.ZERO;
    private List<Produto> produtos = new ArrayList<>();

    public void fecharPedido() {
        setStatus(Status.FECHADO);
    }

    public void prepararPedido() {
        setStatus(Status.EM_PREPARACAO);
    }

    public void enviarPedido() {
        setStatus(Status.EM_TRANSITO);
    }

    public void entregarPedido() {
        setStatus(Status.ENTREGUE);
    }

    public void cancelarPedido() {
        setStatus(Status.CANCELADO);
    }

    public void adicionarProduto(Produto produto) {
        Boolean produtoExiste = adicionarExistente(produto);
        if (!produtoExiste) {
            setStatus(Status.NOVO);
            produto.setQuantidade(1);
            produto.calcularPrecoTotal();
            setPrecoTotal(precoTotal.add(produto.getPreco()));
            produtos.add(produto);
        }
    }

    public Boolean adicionarExistente(Produto produtoParaAdicionar) {
        for (Produto produto : produtos) {
            if(produto.getId() == (produtoParaAdicionar.getId())) {
                produto.setQuantidade(produto.getQuantidade() + 1);
                produto.calcularPrecoTotal();
                setPrecoTotal(precoTotal.add(produto.getPreco()));
                return true;
            }
        }
        return false;
    }


}
