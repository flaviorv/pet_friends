package com.cliente.service;

import com.cliente.domain.Cliente;
import com.cliente.infra.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public void salvar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void excluir(int id) {
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> obter(int id) {
        return clienteRepository.findById(id);
    }

    public Iterable<Cliente> obterTodos() {
        return clienteRepository.findAll();
    }
}
