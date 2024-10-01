package com.pedido.infra;

import com.pedido.domain.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ClienteClient {
    public Cliente buscar(int id) {
        RestClient restClient = RestClient.create();
        String url = String.format("http://localhost:8082/%d", id);
        Cliente cliente = restClient
                .get()
                .uri(url)
                .retrieve()
                .toEntity(Cliente.class)
                .getBody();

        return cliente;
    }
}
