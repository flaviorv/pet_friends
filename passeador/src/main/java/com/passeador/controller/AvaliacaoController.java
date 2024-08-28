package com.passeador.controller;

import com.passeador.domain.Avaliacao;
import com.passeador.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Avaliacao adicionar(@RequestBody Avaliacao avaliacao , @PathVariable int idPasseador) throws Exception {

        return avaliacaoService.salvar(avaliacao, idPasseador);
    }

}