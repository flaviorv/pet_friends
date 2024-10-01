package com.transporte.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transporte.domain.Pedido;
import com.transporte.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @GetMapping
    public Iterable<Pedido> obterPedido() {
        return transporteService.obterPedidos();
    }

    @PostMapping("/{pedidoId}")
    public void entregarPedido(@PathVariable String pedidoId) throws InterruptedException, JsonProcessingException {
        transporteService.entregarPedido(pedidoId);

    }
}
