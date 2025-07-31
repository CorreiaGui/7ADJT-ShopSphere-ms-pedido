package br.com.fiap.ms.pedidoservice.controller.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoResponseJsonTest {

    @Test
    @DisplayName("Deve construir corretamente o ItemPedidoResponseJson usando o Builder")
    void deveConstruirComBuilder() {
        // given
        String sku = "CAMISA-TECH";
        Integer quantidade = 3;

        // when
        ItemPedidoResponseJson response = ItemPedidoResponseJson.builder()
                .sku(sku)
                .quantidade(quantidade)
                .build();

        // then
        assertNotNull(response);
        assertEquals(sku, response.sku());
        assertEquals(quantidade, response.quantidade());
    }

    @Test
    void deveConstruirComBuilderETestarToString() {
        // given
        ItemPedidoResponseJson item = ItemPedidoResponseJson.builder()
                .sku("ABC-123")
                .quantidade(2)
                .build();

        // when
        String resultadoToString = item.toString();

        // then
        assertNotNull(resultadoToString);
        assertTrue(resultadoToString.contains("ABC-123"));
        assertTrue(resultadoToString.contains("2"));
    }
}
