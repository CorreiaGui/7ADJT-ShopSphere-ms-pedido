package br.com.fiap.ms.pedidoservice.controller.json;

import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record PedidoResponseJson(UUID id,
                                 Integer numeroPedido,
                                 String documentoCliente,
                                 BigDecimal valorTotal,
                                 UUID pagamentoId,
                                 LocalDateTime dataCriacao,
                                 LocalDateTime dataUltimaAlteracao,
                                 StatusPedido status,
                                 List<ItemPedidoResponseJson> itens) { }