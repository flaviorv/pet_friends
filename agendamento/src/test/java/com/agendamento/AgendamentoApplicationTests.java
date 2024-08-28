package com.agendamento;

import com.agendamento.dominio.Data;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AgendamentoApplicationTests {

    @Test
    @Name("Teste: inserindo data  com formato correto")
    void formatoDataCorreto() throws Exception {
        Data data = new Data("21/01/12", "13:55");

        String esperado = "21/01/12 13:55";
        String atual = data.toString();

        Assertions.assertEquals(esperado, atual);
    }

    @Test
    @Name("Teste: inserindo dia e hora com formato incorreto")
    void formatoDataIncorreto() throws Exception {

        Assertions.assertThrows(Exception.class, () -> {
            System.out.println("Teste de excessão:");
            new Data("3/01/12", "13:55");
        });

        Assertions.assertThrows(Exception.class, () -> {
            System.out.println("Teste de excessão:");
            new Data("21/01/12", "1:52");
        });
    }

}
