package com.pedido.domain;

import lombok.Data;

@Data
public class TransporteEvents {
    private int id;
    private String horario;
    private Status status;
}
