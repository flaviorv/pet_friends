package com.almoxarifado.infra.rabbit;

import com.almoxarifado.domain.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviarRespostaParaPedido(Pedido pedido) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "pedidos_exchange",
                "preparacao_pedido_rk",
                objectMapper.writeValueAsString(pedido));
    }
}
