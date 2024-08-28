package com.passeador.controller;

import com.passeador.domain.Passeador;
import com.passeador.service.PasseadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PasseadorController {
    @Autowired
    private PasseadorService passeadorService;

    @GetMapping
    public Iterable<Passeador> listar() {
        return passeadorService.listar();
    }

    @GetMapping("/{id}")
    public Passeador buscar(@PathVariable int id) throws Exception {
        return passeadorService.porId(id);
    }

    @PostMapping
    public Passeador cadastrar(@RequestBody Passeador passeador) {
        return passeadorService.salvar(passeador);
    }

}
