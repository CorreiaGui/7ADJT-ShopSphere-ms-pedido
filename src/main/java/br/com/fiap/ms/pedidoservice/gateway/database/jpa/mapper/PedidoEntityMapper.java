package br.com.fiap.ms.pedidoservice.gateway.database.jpa.mapper;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoEntityMapper {

    public static PedidoEntity toEntity(Pedido pedido, Integer numeroPedido) {
        LocalDateTime agora = LocalDateTime.now();

        PedidoEntity entity = PedidoEntity.builder()
                .cpf(pedido.getIdCliente())
                .numeroPedido(numeroPedido)
                .status(pedido.getStatus())
                .valorTotal(BigDecimal.valueOf(pedido.getValorTotal()))
                .pagamentoId(pedido.getNumeroCartao())
                .dataCriacao(agora)
                .dataUltimaAlteracao(null)
                .build();

        List<ItemPedidoEntity> itens = pedido.getItens().stream().map(item ->
                ItemPedidoEntity.builder()
                        .sku(item.getSku())
                        .quantidade(item.getQuantidade())
                        .dataCriacao(agora)
                        .dataUltimaAlteracao(null)
                        .pedido(entity)
                        .build()

        ).collect(Collectors.toList());

        entity.setItens(itens);
        return entity;
    }

    public static Pedido toDomain(PedidoEntity entity) {
        List<ItemPedido> itens = entity.getItens().stream().map(item ->
                new ItemPedido(item.getSku(), item.getQuantidade())
        ).collect(Collectors.toList());

        return new Pedido(
                entity.getCpf(),
                itens,
                entity.getPagamentoId(),
                entity.getValorTotal().doubleValue(),
                entity.getStatus()
        );
    }
}
