package com.pedido.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {
    private int id;
    private int quantidade;
    private BigDecimal preco;
    private BigDecimal precoTotal;

    public void calcularPrecoTotal() {
        this.precoTotal = preco.multiply(new BigDecimal(quantidade));
    }

}
