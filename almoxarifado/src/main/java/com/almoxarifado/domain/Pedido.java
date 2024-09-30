package com.almoxarifado.domain;

import lombok.Data;
import java.util.List;

@Data
public class Pedido {
    private String id;
    private Status status;
    private List<Produto> produtos;
}
