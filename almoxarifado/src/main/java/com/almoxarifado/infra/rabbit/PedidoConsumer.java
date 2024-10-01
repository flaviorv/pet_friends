package com.almoxarifado.infra.rabbit;

import com.almoxarifado.Service.EstoqueService;
import com.almoxarifado.domain.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EstoqueService estoqueService;

    @RabbitListener(queues = {"almoxarifado_queue"})
    public void receberPedido(@Payload String _pedido) throws JsonProcessingException, InterruptedException {
        Pedido pedido = mapper.readValue(_pedido, Pedido.class);
        System.out.println(pedido);
//        Thread.sleep(5000);
        estoqueService.prepararPedido(pedido);

    }
}
