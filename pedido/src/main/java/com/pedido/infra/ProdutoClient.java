package com.pedido.infra;

import com.pedido.domain.Produto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProdutoClient {
    public Produto[] buscarTodos(){
        RestClient restClient = RestClient.create();
        String url = String.format("http://localhost:8083/");
        Produto[] produtos = restClient
                        .get()
                        .uri(url)
                        .retrieve()
                        .toEntity(Produto[].class)
                        .getBody();

        return produtos;
    }

    public Produto buscar(int id) {
        RestClient restClient = RestClient.create();
        String url = String.format("http://localhost:8083/%d", id);
        Produto produto = restClient
                .get()
                .uri(url)
                .retrieve()
                .toEntity(Produto.class)
                .getBody();

        return produto;
    }

}
