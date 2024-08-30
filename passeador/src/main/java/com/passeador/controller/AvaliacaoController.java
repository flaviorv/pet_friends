package com.passeador.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.passeador.domain.Avaliacao;
import com.passeador.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/{idPasseador}")
    public Iterable<Avaliacao> listar(@PathVariable int idPasseador){
        return avaliacaoService.listarPorPasseador(idPasseador);
    }

    @PostMapping("/{idPasseador}")
    public ResponseEntity adicionar(@RequestBody Avaliacao avaliacao , @PathVariable int idPasseador) {
        try {
            System.out.println("Avaliacao adicionada: " + avaliacao);
            return ResponseEntity.ok(avaliacaoService.salvar(avaliacao, idPasseador));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}