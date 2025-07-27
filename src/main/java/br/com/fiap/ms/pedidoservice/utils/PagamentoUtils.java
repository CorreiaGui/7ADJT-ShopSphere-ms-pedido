package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;

import java.math.BigDecimal;

public final class PagamentoUtils {
    private  PagamentoUtils(){}

    public static PagamentoRequest buildPagamento(PedidoRequestJson pedidoRequestJson, Pedido pedido, BigDecimal valorTotal) {
        return new PagamentoRequest(
                pedido.getId(),
                pedidoRequestJson.formaPagamento(),
                pedidoRequestJson.numeroCartaoCredito(),
                valorTotal);
    }
}
