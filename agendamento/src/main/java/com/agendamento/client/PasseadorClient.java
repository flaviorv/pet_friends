package com.agendamento.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PasseadorClient {
    public Passeador porId(int id) {
        RestClient restClient = RestClient.create();
        String url = String.format("http://localhost:8081/%d", id);
        Passeador passeador = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(Passeador.class)
                .getBody();
        return passeador;
    }

    public Iterable<Passeador> listarPasseadores() {
        RestClient restClient = RestClient.create();
        String url = String.format("http://localhost:8081/");
        Iterable<Passeador> passeadores = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(Iterable.class)
                .getBody();
        return passeadores;
    }
}
