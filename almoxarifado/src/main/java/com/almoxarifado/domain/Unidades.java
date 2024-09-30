package com.almoxarifado.domain;

import lombok.Data;

@Data
public class Unidades {
    private int unidades = 0;

    public void adicionarUnidadesAProdutoExistente(int unidades) {
        this.unidades += unidades;
    }

    public void prepararProduto(int unidades) {
        int _unidades = this.unidades - unidades;
        if(_unidades >= 0) {
            this.unidades = _unidades;
        }
    }
}
