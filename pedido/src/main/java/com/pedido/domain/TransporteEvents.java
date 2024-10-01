package com.pedido.domain;

import lombok.Data;

@Data
public class TransporteEvents {
    private String horario;
    private Status status;
    private String idPedido;
}
