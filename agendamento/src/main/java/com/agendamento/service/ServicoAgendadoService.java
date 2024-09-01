package com.agendamento.service;

import com.agendamento.client.Passeador;
import com.agendamento.client.PasseadorClient;
import com.agendamento.domain.ServicoAgendado;
import com.agendamento.domain.Servicos;
import com.agendamento.domain.event.ServicoAgendadoEvent;
import com.agendamento.infra.ServicoAgendadoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoAgendadoService {
    private static final Logger LOG = LoggerFactory.getLogger(ServicoAgendadoService.class);

    @Autowired
    private ServicoAgendadoRepository servicoAgendadoRepository;

    @Autowired
    private PasseadorClient passeadorClient;

    @Autowired
    private PubSubTemplate pubSubTemplate;

    public Iterable<ServicoAgendado> listar() {
        return servicoAgendadoRepository.findAll();
    }

    public ServicoAgendado salvar(ServicoAgendado servicoAgendado) throws Exception {
        if(servicoAgendado.getServico() == Servicos.PASSEIO) {
            try {
                passeadorClient.porId(servicoAgendado.getIdPrestador());
            }catch (Exception e) {
                throw new Exception("Passeador não encontrado.");
            }
            return servicoAgendadoRepository.save(servicoAgendado);
        }else{
            throw new Exception("Prestador de serviços ainda não cadastrado. Escolha outro serviço");
        }
    }

    public void msgAgendamentoConcluido(ServicoAgendado servicoAgendado)  {
        ServicoAgendadoEvent event = new ServicoAgendadoEvent(servicoAgendado);
        try {
            String msg = event.toJsonMsg(event);
            pubSubTemplate.publish("pet-friends-servico-agendado", msg);
            LOG.info("=====Mensagem-Enviada=====: " + msg);
        }catch (JsonProcessingException e) {
            LOG.error("Falha ao converter evento para Json\n" +
                    "Evento: " + event + "\n" +
                    e.getMessage());
        }
    }

}
