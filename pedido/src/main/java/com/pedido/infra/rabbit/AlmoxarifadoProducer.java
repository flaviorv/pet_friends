package com.pedido.infra.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AlmoxarifadoProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void enviarPedidoParaAlmoxarifado(Pedido pedido) throws AmqpException, JsonProcessingException {
        amqpTemplate.convertAndSend("pedidos_exchange", "checar_estoque_rk", objectMapper.writeValueAsString(pedido));
    }

}
