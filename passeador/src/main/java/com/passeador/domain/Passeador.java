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
    private String avaliacaoMedia;

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

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

    public String getAvaliacaoMedia() {
        if(avaliacoes == null || avaliacoes.isEmpty()){
            return "0";
        }
        float _avaliacaoMedia = Avaliacao.calcularMedia(avaliacoes);
        return String.format("%.1f", _avaliacaoMedia);
    }


}
