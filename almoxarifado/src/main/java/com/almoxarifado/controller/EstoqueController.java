package com.almoxarifado.controller;

import com.almoxarifado.Service.EstoqueService;
import com.almoxarifado.domain.EstoqueProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public Iterable<EstoqueProduto> obterEstoqueCompleto() {
        return estoqueService.obterEstoqueCompleto();
    }

    @GetMapping("/{id}")
    public Optional<EstoqueProduto> obterEstoque(@PathVariable int id) {
        return estoqueService.obterEstoque(id);
    }

}
