package com.agendamento.controller;

import com.agendamento.domain.ServicoAgendado;
import com.agendamento.service.ServicoAgendadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ServicoAgendadoController {
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
            return ResponseEntity.ok(servicoAgendado);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
