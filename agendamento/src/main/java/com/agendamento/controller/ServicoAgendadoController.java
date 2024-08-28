package com.agendamento.controller;

import com.agendamento.dominio.ServicoAgendado;
import com.agendamento.service.ServicoAgendadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ServicoAgendadoController {
    @Autowired
    private ServicoAgendadoService servicoAgendadoService;

    @GetMapping
    public Iterable<ServicoAgendado> listar() {
        return servicoAgendadoService.listar();
    }

    @PostMapping
    public ServicoAgendado salvar(@RequestBody ServicoAgendado servicoAgendado) {

        return servicoAgendadoService.salvar(servicoAgendado);
    }

}
