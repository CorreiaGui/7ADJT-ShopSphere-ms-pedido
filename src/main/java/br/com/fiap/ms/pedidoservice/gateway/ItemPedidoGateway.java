package br.com.fiap.ms.pedidoservice.gateway;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;

import java.util.List;

public interface ItemPedidoGateway {

    List<ItemPedidoEntity> salvarTodos(List<ItemPedidoEntity> itens);

    List<ItemPedidoEntity> buscarItensPorPedidoId(Integer pedidoId);
}
