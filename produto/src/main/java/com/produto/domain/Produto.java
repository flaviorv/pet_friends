package com.produto.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Entity
public class Produto {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String marca;
    @Setter
    private String tipo;
    private BigDecimal preco;

    public void setPreco(BigDecimal preco) {
        this.preco = preco.setScale(2, RoundingMode.HALF_EVEN);
    }
}
