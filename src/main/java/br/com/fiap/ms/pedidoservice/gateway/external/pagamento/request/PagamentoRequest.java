package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequest(UUID pedidoId,
                               Integer formaPagamento,
                               String numeroCartaoCredito,
                               BigDecimal valor) {
}