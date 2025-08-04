package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.controller.json.ItemPedidoRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.ItemPedidoResponseJson;
import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;

import static java.lang.Math.toIntExact;

public final class ItemPedidoUtils {

    private ItemPedidoUtils(){}

    public static ItemPedido convertToItemPedido(ItemPedidoEntity entity) {
        return ItemPedido.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .numeroPedido(entity.getNumeroPedido())
                .quantidade(entity.getQuantidade())
                .dataCriacao(entity.getDataCriacao())
                .dataUltimaAlteracao(entity.getDataUltimaAlteracao())
                .build();
    }

    public static ItemPedidoEntity convertToItemPedidoEntity(ItemPedido entity) {
        return ItemPedidoEntity.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .numeroPedido(entity.getNumeroPedido())
                .quantidade(entity.getQuantidade())
                .dataCriacao(entity.getDataCriacao())
                .dataUltimaAlteracao(entity.getDataUltimaAlteracao())
                .build();
    }

    public static ItemPedido convertToItemPedido(ItemPedidoRequestJson itemDTO, int numeroPedido) {
        return ItemPedido.builder()
                .sku(itemDTO.sku())
                .quantidade(toIntExact(itemDTO.quantidade()))
                .numeroPedido(numeroPedido)
                .build();
    }

    public static ItemPedidoResponseJson convertToItemPedidoResponseJson(ItemPedidoEntity e) {
        return ItemPedidoResponseJson.builder()
                .sku(e.getSku())
                .quantidade(e.getQuantidade())
                .build();
    }
}
