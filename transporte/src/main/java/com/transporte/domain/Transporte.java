package com.transporte.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Data
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String horario;
    private String status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @ToString.Exclude
    private Pedido pedido;

    public void entregarPedido(Pedido pedido) {
        this.pedido = pedido;
        setStatus(Status.ENTREGUE.toString());
        registrarHorario();
    }

    public void iniciarTransporte(Pedido pedido) {
        this.pedido = pedido;
        setStatus(Status.EM_TRANSITO.toString());
        registrarHorario();
    }

    public void registrarHorario() {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                new Locale("pt", "BR"));
        setHorario(sdf.format(new Date()));
    }
}
