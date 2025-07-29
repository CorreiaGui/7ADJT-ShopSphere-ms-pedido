package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoResponse(UUID id,
                                UUID pedidoId,
                                Integer formaPagamento,
                                String numeroCartaoCredito,
                                BigDecimal valor,
                                String solicitacaoPagamentoExternoId,
                                LocalDateTime dataCriacao,
                                LocalDateTime dataUltimaAlteracao) {
}
