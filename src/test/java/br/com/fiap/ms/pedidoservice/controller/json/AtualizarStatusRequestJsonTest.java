package br.com.fiap.ms.pedidoservice.controller.json;

import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtualizarStatusRequestJsonTest {

    @Test
    @DisplayName("Deve construir corretamente com o Lombok Builder")
    void deveCriarAtualizarStatusRequestJsonComBuilder() {
        // given
        StatusPedido status = StatusPedido.FECHADO_SEM_ESTOQUE;

        // when
        AtualizarStatusRequestJson requestJson = AtualizarStatusRequestJson.builder()
                .status(status)
                .build();

        // then
        assertNotNull(requestJson);
        assertEquals(StatusPedido.FECHADO_SEM_ESTOQUE, requestJson.status());
    }

    @Test
    @DisplayName("Deve criar corretamente com construtor direto")
    void deveCriarAtualizarStatusRequestJsonComConstrutor() {
        // given
        StatusPedido status = StatusPedido.ABERTO;

        // when
        AtualizarStatusRequestJson requestJson = new AtualizarStatusRequestJson(status);

        // then
        assertEquals(status, requestJson.status());
    }

    @Test
    void deveConstruirComBuilderETestarToString() {
        // given
        AtualizarStatusRequestJson request = AtualizarStatusRequestJson.builder()
                .status(StatusPedido.ABERTO)
                .build();

        // when
        String resultadoToString = request.toString();

        // then
        assertNotNull(resultadoToString);
        assertTrue(resultadoToString.contains("ABERTO"));
    }
}
