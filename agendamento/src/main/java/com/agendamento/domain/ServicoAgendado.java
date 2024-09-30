package com.agendamento.domain;

import com.agendamento.client.PasseadorClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import java.util.Optional;

@Entity
public class ServicoAgendado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String data;
    private Servicos servico;
    private int idPrestador;
    private Status status;

    public ServicoAgendado agendarServico(Optional<ServicoAgendado> existente) throws Exception {
        checarDisponibilidade(existente);
        if(servico == Servicos.CONSULTA) {
            //veterinarioClient.buscarPor(idPrestador);
            throw new Exception("Serviço não disponível no momento.");
        } else if (servico == Servicos.PASSEIO) {
            PasseadorClient passeadorClient = new PasseadorClient();
            try{
                passeadorClient.porId(idPrestador);
            }catch (Exception e){
                throw new Exception("Passeador não existente.");
            }
            setData(data);
            status = Status.AGENDADO;
        }
        return this;
    }

    public void checarDisponibilidade(Optional<ServicoAgendado> existente) throws Exception {
        if(existente.isPresent() && existente.get().getStatus() == Status.AGENDADO){
            throw new Exception("Prestador já possui um serviço agendado nessa data.");
        }
    }

    public String toJsonMsg() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public int getId(){
        return id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

