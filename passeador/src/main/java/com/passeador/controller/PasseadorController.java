package com.passeador.controller;

import com.passeador.domain.Passeador;
import com.passeador.service.PasseadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity buscar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(passeadorService.porId(id));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public Passeador cadastrar(@RequestBody Passeador passeador) {
        return passeadorService.salvar(passeador);
    }

}
