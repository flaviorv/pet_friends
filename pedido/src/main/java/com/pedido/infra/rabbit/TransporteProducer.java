package com.pedido.infra.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedido.domain.Pedido;
import com.pedido.domain.Status;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransporteProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ObjectMapper mapper;

    public void solicitarStatusTransporte(Pedido pedido) throws JsonProcessingException {
        if(pedido.getStatus() == Status.EM_TRANSITO){
        amqpTemplate.convertAndSend(
                "pedidos_exchange",
                "transporte_rk",
                mapper.writeValueAsString(pedido));
        }
    }

}
