package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PagamentoRequest(UUID pedidoId,
                               Integer formaPagamento,
                               String numeroCartaoCredito,
                               BigDecimal valor) {
}