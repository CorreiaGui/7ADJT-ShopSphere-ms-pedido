package br.com.fiap.ms.pedidoservice.controller.json;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoResponseJson(UUID id,
                                 Integer numeroPedido,
                                 String documentoCliente,
                                 BigDecimal valorTotal,
                                 UUID pagamentoId,
                                 LocalDateTime dataCriacao) {
}
