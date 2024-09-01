package com.passeador.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int avaliacao;

    @ManyToOne
    @JoinColumn(name="ID_PASSEADOR", nullable=false)
    private Passeador passeador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }



    public void setPasseador(Passeador passeador) {
        this.passeador = passeador;
    }

    public static float calcularMedia(List<Avaliacao> avaliacoes) {
        float soma = 0;
        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getAvaliacao();
        }
        return soma/avaliacoes.size();
    }
}
