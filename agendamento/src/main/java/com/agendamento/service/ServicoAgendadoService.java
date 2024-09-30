package com.agendamento.service;

import com.agendamento.client.Passeador;
import com.agendamento.client.PasseadorClient;
import com.agendamento.domain.ServicoAgendado;
import com.agendamento.domain.Servicos;
import com.agendamento.infra.ServicoAgendadoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoAgendadoService extends ServicoAgendado{
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

    public void msgAgendamentoConcluido(ServicoAgendado evento)  {
        try {
            String msg = evento.toJsonMsg();
            pubSubTemplate.publish("pet-friends-servico-agendado", msg);
            LOG.info("=====Mensagem-Enviada=====: " + msg);
        }catch (JsonProcessingException e) {
            LOG.error("Falha ao converter evento para Json\n" +
                    "Evento: " + evento + "\n" +
                    e.getMessage());
        }
    }

    public ServicoAgendado salvar(ServicoAgendado sa) throws Exception {
        Optional<ServicoAgendado> existente = servicoAgendadoRepository.buscarPor(sa.getData(), sa.getServico(), sa.getIdPrestador());
        return servicoAgendadoRepository.save(sa.agendarServico(existente));
    }


}
