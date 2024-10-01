package com.pedido.infra.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedido.domain.Pedido;
import com.pedido.domain.TransporteEvents;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransporteConsumer {
    @Autowired
    ObjectMapper mapper;

    @RabbitListener(queues = {"pedido_queue_2"})
    public void receberRespostaAlmoxarifado(String resposta) throws JsonProcessingException, InterruptedException {
        TransporteEvents transporte = mapper.readValue(resposta, TransporteEvents.class);
        System.out.println(transporte);
        //atualizar pedido e salvar
    }
}
