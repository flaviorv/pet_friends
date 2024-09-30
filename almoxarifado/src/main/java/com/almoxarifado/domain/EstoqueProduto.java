package com.almoxarifado.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "ESTOQUE_PRODUTO")
public class EstoqueProduto {
    @Id
    @Column(name = "PRODUTO_ID")
    private int produtoId;
    @Column(name = "UNIDADES")
    private int unidades;

    public void adicionarUnidadesAProdutoExistente(int unidades) {
        if(produtoEmEstoque(produtoId)) {
            this.unidades += unidades;
        }
    }

    public void prepararProduto(int produtoId, int unidades) {
        if(produtoEmEstoque(produtoId) && quantidadeSuficiente(unidades)) {
            this.unidades -= unidades;
        }else {
            throw new RuntimeException("Não há no estoque.");
        }
    }

    public Boolean produtoEmEstoque (int produtoId) {
        return this.produtoId == produtoId;
    }

    public Boolean quantidadeSuficiente (int unidades) {
        int quantidade = unidades - this.unidades;
        return quantidade >= 0;
    }

}
