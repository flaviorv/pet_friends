package com.agendamento.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PasseadorClient {
    public Passeador porId(int id) {
        RestClient restClient = RestClient.create();
        String url = String.format("http://localhost:8081/%d", id);
        return restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(Passeador.class)
                .getBody();
    }

    public Passeador[] listarPasseadores() {
        RestClient restClient = RestClient.create();
        String url = String.format("http://localhost:8081/");
        Passeador[] passeadores
                = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(Passeador[].class)
                .getBody();

        return passeadores;
    }
}
