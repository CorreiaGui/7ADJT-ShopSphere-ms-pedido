package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoUtilsTest {

    @Test
    void deveConstruirPagamentoComDadosCorretos() {
        UUID pedidoId = UUID.randomUUID();
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);

        PedidoRequestJson pedidoRequestJson =new PedidoRequestJson("33344455566",
                null, 1, "1234567890123456");

        BigDecimal valorTotal = new BigDecimal("250.00");

        PagamentoRequest pagamentoRequest = PagamentoUtils.buildPagamento(pedidoRequestJson, pedido, valorTotal);

        assertEquals(pedidoId, pagamentoRequest.pedidoId());
        assertEquals(1, pagamentoRequest.formaPagamento());
        assertEquals("1234567890123456", pagamentoRequest.numeroCartaoCredito());
        assertEquals(valorTotal, pagamentoRequest.valor());
    }
}
