package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;

public final class ItemPedidoUtils {

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
}
