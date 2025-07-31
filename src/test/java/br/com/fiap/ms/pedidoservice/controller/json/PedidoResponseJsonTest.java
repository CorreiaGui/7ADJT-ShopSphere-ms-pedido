package br.com.fiap.ms.pedidoservice.controller.json;

import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PedidoResponseJsonTest {

    @Test
    @DisplayName("Deve construir corretamente um PedidoResponseJson com Lombok Builder")
    void deveConstruirComBuilder() {
        // given
        UUID id = UUID.randomUUID();
        Integer numeroPedido = 1001;
        String documentoCliente = "12345678901";
        BigDecimal valorTotal = new BigDecimal("150.50");
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataUltimaAlteracao = LocalDateTime.now();
        StatusPedido status = StatusPedido.FECHADO_COM_SUCESSO;
        List<ItemPedidoResponseJson> itens = List.of(
                new ItemPedidoResponseJson("ABC123", 2)
        );

        // when
        PedidoResponseJson response = PedidoResponseJson.builder()
                .id(id)
                .numeroPedido(numeroPedido)
                .documentoCliente(documentoCliente)
                .valorTotal(valorTotal)
                .pagamentoId(pagamentoId)
                .dataCriacao(dataCriacao)
                .dataUltimaAlteracao(dataUltimaAlteracao)
                .status(status)
                .itens(itens)
                .build();

        // then
        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals(numeroPedido, response.numeroPedido());
        assertEquals(documentoCliente, response.documentoCliente());
        assertEquals(valorTotal, response.valorTotal());
        assertEquals(pagamentoId, response.pagamentoId());
        assertEquals(dataCriacao, response.dataCriacao());
        assertEquals(dataUltimaAlteracao, response.dataUltimaAlteracao());
        assertEquals(status, response.status());
        assertEquals(itens, response.itens());
    }

    @Test
    void deveConstruirComBuilderETestarToString() {
        // given
        UUID id = UUID.randomUUID();
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        PedidoResponseJson response = PedidoResponseJson.builder()
                .id(id)
                .numeroPedido(789)
                .documentoCliente("12345678901")
                .valorTotal(BigDecimal.valueOf(199.90))
                .pagamentoId(pagamentoId)
                .dataCriacao(agora)
                .dataUltimaAlteracao(agora)
                .status(StatusPedido.FECHADO_COM_SUCESSO)
                .itens(List.of(
                        ItemPedidoResponseJson.builder().sku("SKU-001").quantidade(1).build()
                ))
                .build();

        // when
        String resultadoToString = response.toString();
        System.out.println(response);
        // then
        assertNotNull(resultadoToString);
        assertTrue(resultadoToString.contains("12345678901"));
    }
}
