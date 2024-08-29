package com.agendamento.dominio;

import jakarta.persistence.*;

@Entity
public class ServicoAgendado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String data;
    private Servicos servico;
    private int idPrestador;

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) throws Exception {
        String[] dataHora = data.split(" ");
        this.data = new Data(dataHora[0], dataHora[1]).toString();
    }

    public Servicos getServico() {
        return servico;
    }

    public void setServico(Servicos servico) {
        this.servico = servico;
    }

    public int getIdPrestador(){
        return idPrestador;
    }

    public void setIdPrestador(int idFuncionario){
        this.idPrestador = idFuncionario;
    }
}

