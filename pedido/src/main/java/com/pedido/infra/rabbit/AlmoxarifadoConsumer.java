package com.pedido.infra.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedido.domain.Pedido;
import com.pedido.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlmoxarifadoConsumer {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    TransporteProducer transporteProducer;

    @RabbitListener(queues = {"pedido_queue"})
    public void receberRespostaAlmoxarifado(String resposta) throws JsonProcessingException, InterruptedException {
        Pedido pedido = mapper.readValue(resposta, Pedido.class);
        System.out.println(pedido);
        pedidoService.atualizarStatusPedido(pedido);
        Thread.sleep(5000);
        transporteProducer.solicitarStatusTransporte(pedido);
    }
}
