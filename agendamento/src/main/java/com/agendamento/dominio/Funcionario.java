package com.agendamento.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Funcionario {
    @Id
    private int id;
    private Funcao funcao;
    private String nome;

    enum Funcao {
        PASSEADOR, VETERINARIO
    }
}
