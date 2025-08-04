package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoResponseTest {

    @Test
    void testPagamentoResponseCreation() {
        UUID id = UUID.randomUUID();
        UUID pedidoId = UUID.randomUUID();
        Integer formaPagamento = 1;
        String numeroCartaoCredito = "4111111111111111";
        BigDecimal valor = new BigDecimal("100.00");
        String solicitacaoPagamentoExternoId = "222";
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataUltimaAlteracao = LocalDateTime.now();

        PagamentoResponse response = new PagamentoResponse(
                id,
                pedidoId,
                formaPagamento,
                numeroCartaoCredito,
                valor,
                solicitacaoPagamentoExternoId,
                dataCriacao,
                dataUltimaAlteracao
        );

        assertEquals(id, response.id());
        assertEquals(pedidoId, response.pedidoId());
        assertEquals(formaPagamento, response.formaPagamento());
        assertEquals(numeroCartaoCredito, response.numeroCartaoCredito());
        assertEquals(valor, response.valor());
        assertEquals(solicitacaoPagamentoExternoId, response.solicitacaoPagamentoExternoId());
        assertEquals(dataCriacao, response.dataCriacao());
        assertEquals(dataUltimaAlteracao, response.dataUltimaAlteracao());
    }
}
