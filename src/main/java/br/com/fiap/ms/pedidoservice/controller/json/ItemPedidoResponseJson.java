package br.com.fiap.ms.pedidoservice.controller.json;

import lombok.Builder;

@Builder
public record ItemPedidoResponseJson(String sku, Integer quantidade) {
}

