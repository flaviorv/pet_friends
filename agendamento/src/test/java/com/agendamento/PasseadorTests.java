package com.agendamento;

import com.agendamento.client.Passeador;
import com.agendamento.client.PasseadorClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class PasseadorTests {

    @Test
    @DisplayName("Receber dados do serviço de Passeador")
    void obterPasseador() {
        Passeador passeador = new Passeador();
        PasseadorClient pClient = new PasseadorClient();
        passeador = pClient.porId(1);

        int esperado = 1;
        int atual = passeador.getId();

        Assertions.assertEquals(esperado, atual);
    }

    @Test
    @DisplayName("Receber todos passeadores")
    void obterPasseadors() {
        Iterable<Passeador> passeadores = new ArrayList<>();
        PasseadorClient client = new PasseadorClient();
        passeadores  = client.listarPasseadores();

        Boolean atual = passeadores.spliterator().getExactSizeIfKnown() > 0;

        System.out.println(passeadores);
        Assertions.assertTrue(atual);
    }
}
