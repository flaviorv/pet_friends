package com.agendamento.controller;

import com.agendamento.client.Passeador;
import com.agendamento.client.PasseadorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passeadores")
public class PasseadorController {
    @Autowired
    PasseadorClient passeadorClient;

    @GetMapping
    Passeador[] listaPasseadores() {
        return passeadorClient.listarPasseadores();
    }
}