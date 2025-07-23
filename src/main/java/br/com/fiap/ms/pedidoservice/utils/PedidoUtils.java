package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;

import java.util.Random;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public final class PedidoUtils {

    private PedidoUtils () {}

    public static Pedido convertToPedido(PedidoEntity e) {
        return Pedido.builder()
                .id(e.getId())
                .numeroPedido(e.getNumeroPedido())
                .valorTotal(e.getValorTotal())
                .cpf(e.getCpf())
                .dataCriacao(e.getDataCriacao())
                .dataUltimaAlteracao(e.getDataUltimaAlteracao())
                .itens(e.getItens() != null
                        ? e.getItens().stream()
                        .map(ItemPedidoUtils::convertToItemPedido)
                        .collect(toList())
                        : emptyList())
                .build();
    }

    public static PedidoEntity convertToPedidoEntity(Pedido e) {
        return PedidoEntity.builder()
                .id(e.getId())
                .numeroPedido(e.getNumeroPedido())
                .valorTotal(e.getValorTotal())
                .cpf(e.getCpf())
                .dataCriacao(e.getDataCriacao())
                .dataUltimaAlteracao(e.getDataUltimaAlteracao())
                .itens(e.getItens() != null
                        ? e.getItens().stream()
                        .map(ItemPedidoUtils::convertToItemPedidoEntity)
                        .collect(toList())
                        : emptyList())
                .build();
    }

    public static int gerarNumeroPedidoAleatorio() {
        return new Random().nextInt(900_000) + 100_000; // 6 dígitos
    }


}