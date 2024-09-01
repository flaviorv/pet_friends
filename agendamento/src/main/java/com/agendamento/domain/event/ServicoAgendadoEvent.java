package com.agendamento.domain.event;

import com.agendamento.domain.ServicoAgendado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServicoAgendadoEvent {
    private int id;
    private String data;
    private int idPrestador;

    public ServicoAgendadoEvent(ServicoAgendado servicoAgendado) {
        this.id = servicoAgendado.getId();
        this.data = servicoAgendado.getData();
        this.idPrestador = servicoAgendado.getIdPrestador();
    }

    public String toJsonMsg(ServicoAgendadoEvent event) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(event);
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getIdPrestador() {
        return idPrestador;
    }
}
