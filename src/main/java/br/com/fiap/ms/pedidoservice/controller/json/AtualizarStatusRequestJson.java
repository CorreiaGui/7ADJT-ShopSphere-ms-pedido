package br.com.fiap.ms.pedidoservice.controller.json;

import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import lombok.Builder;

@Builder
public record AtualizarStatusRequestJson(StatusPedido status) { }