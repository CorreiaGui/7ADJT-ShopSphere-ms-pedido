package br.com.fiap.ms.pedidoservice.gateway;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoGateway {

    PedidoEntity salvar(PedidoEntity pedidoEntity);

    PedidoEntity buscarPorNumeroPedido(Integer numeroPedido);

    PedidoEntity buscarPorId(UUID id);

    List<PedidoResponseJson> buscarTodos(int page, int size);

    Optional<PedidoEntity> buscarById(UUID id);

    void excluirPedido(UUID id);
}
