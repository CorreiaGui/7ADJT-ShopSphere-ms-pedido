package br.com.fiap.ms.pedidoservice.gateway;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;

import java.util.UUID;

public interface PedidoGateway {

    PedidoEntity salvar(PedidoEntity pedidoEntity);

    PedidoEntity buscarPorNumeroPedido(Integer numeroPedido);

    PedidoEntity buscarPorId(UUID id);
    
}
