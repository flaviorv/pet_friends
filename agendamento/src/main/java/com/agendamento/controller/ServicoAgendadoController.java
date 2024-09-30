package com.agendamento.controller;

import com.agendamento.domain.ServicoAgendado;
import com.agendamento.service.ServicoAgendadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ServicoAgendadoController {
    private static final Logger LOG = LoggerFactory.getLogger(ServicoAgendadoController.class);

    @Autowired
    private ServicoAgendadoService servicoAgendadoService;

    @GetMapping
    public Iterable<ServicoAgendado> listar() {
        return servicoAgendadoService.listar();
    }

    @PostMapping("/")
    public ResponseEntity salvar(@RequestBody ServicoAgendado servicoAgendado) {
        try {
            servicoAgendadoService.salvar(servicoAgendado);
            servicoAgendadoService.msgAgendamentoConcluido(servicoAgendado);
        }catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(servicoAgendado);
    }

}
