package com.passeador.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Passeador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    @OneToMany(mappedBy = "passeador")
    private List<Avaliacao> avaliacoes;
    @Transient
    private float avaliacaoMedia;



    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAvaliacaoMedia() {
        if(avaliacoes == null || avaliacoes.isEmpty()){
            return 0;
        }
        return Avaliacao.calcularMedia(avaliacoes);
    }

}
