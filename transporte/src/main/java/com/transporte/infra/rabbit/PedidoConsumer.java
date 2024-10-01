package com.transporte.infra.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transporte.domain.Pedido;

import com.transporte.domain.Status;
import com.transporte.service.TransporteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TransporteService transporteService;

    @RabbitListener(queues = {"transporte_queue"})
    public void receberPedido(String json) throws JsonProcessingException {
        JsonNode _id = mapper.readTree(json).get("id");
        JsonNode _status = mapper.readTree(json).get("status");
        String id = _id.asText();
        String status = _status.asText();
        if(status.equals("EM_TRANSITO")){
            Pedido pedido = new Pedido();
            pedido.setId(id);
            transporteService.iniciarTransporte(pedido);
        }
    }

}
