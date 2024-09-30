package com.cliente.controller;

import com.cliente.domain.Cliente;
import com.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Iterable<Cliente> obterTodos() {
        return clienteService.obterTodos();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> obterPorId(@PathVariable int id) {
        return clienteService.obter(id);
    }

    @PostMapping
    public void adicionar(@RequestBody Cliente cliente) {
        clienteService.salvar(cliente);
    }

    @DeleteMapping
    public void remover(@RequestBody int id) {
        clienteService.excluir(id);
    }

}
