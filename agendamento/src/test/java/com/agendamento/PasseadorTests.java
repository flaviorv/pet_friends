package com.agendamento;

import com.agendamento.client.Passeador;
import com.agendamento.client.PasseadorClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasseadorTests {

    @Test
    @DisplayName("Receber dados do serviço de Passeador")
    void obterPasseador(){
        Passeador passeador = new Passeador();
        PasseadorClient pClient = new PasseadorClient();
        passeador = pClient.porId(1);

        int esperado = 1;
        int atual = passeador.getId();

        Assertions.assertEquals(esperado, atual);

    }
}
