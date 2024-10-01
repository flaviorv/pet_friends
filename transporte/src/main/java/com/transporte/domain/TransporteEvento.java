package com.transporte.domain;

import lombok.Getter;

@Getter
public class TransporteEvento {
    private String idPedido;
    private String horario;
    private String status;

    public TransporteEvento(Transporte transporte) {
        idPedido = transporte.getPedido().getId();
        horario = transporte.getHorario();
        status = transporte.getStatus();
    }
}
