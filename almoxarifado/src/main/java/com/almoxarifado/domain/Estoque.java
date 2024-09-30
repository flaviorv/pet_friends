package com.almoxarifado.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estoque {
    @Id
    private int produtoId;
    private Unidades unidades;

    private int getUnidades() {
        return unidades.getUnidades();
    }

    private int getProdutoId() {
        return produtoId;
    }

    private void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

}
