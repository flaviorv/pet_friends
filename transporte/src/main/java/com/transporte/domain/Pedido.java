package com.transporte.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@Entity
public class Pedido {
    @Id
    private String id;
    @OneToMany(mappedBy = "pedido")
    private List<Transporte> transportes;

}