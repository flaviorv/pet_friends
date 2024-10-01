package com.transporte.infra.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transporte.domain.Transporte;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {
    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    ObjectMapper mapper;

    public void responderPedido(Transporte transporte) throws JsonProcessingException {
        System.out.println(transporte);
        amqpTemplate.convertAndSend(
                "pedidos_exchange",
                "evento_transporte_rk",
                mapper.writeValueAsString(transporte));
    }
}
