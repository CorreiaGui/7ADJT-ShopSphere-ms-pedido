package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static br.com.fiap.ms.pedidoservice.domain.StatusPedido.ABERTO;
import static java.time.LocalDateTime.now;
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
                .pagamentoId(e.getPagamentoId())
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
                .pagamentoId(e.getPagamentoId())
                .status(e.getStatus())
                .dataCriacao(e.getDataCriacao())
                .dataUltimaAlteracao(e.getDataUltimaAlteracao())
                .itens(e.getItens() != null
                        ? e.getItens().stream()
                        .map(ItemPedidoUtils::convertToItemPedidoEntity)
                        .collect(toList())
                        : emptyList())
                .build();
    }

    public static PedidoResponseJson convertToPedidoResponseJson(PedidoEntity e) {
        return PedidoResponseJson.builder()
                .id(e.getId())
                .numeroPedido(e.getNumeroPedido())
                .documentoCliente(e.getCpf())
                .valorTotal(e.getValorTotal())
                .pagamentoId(e.getPagamentoId())
                .dataCriacao(e.getDataCriacao())
                .dataUltimaAlteracao(e.getDataUltimaAlteracao())
                .status(e.getStatus())
                .itens(e.getItens() != null
                        ? e.getItens().stream()
                        .map(ItemPedidoUtils::convertToItemPedidoResponseJson)
                        .collect(toList())
                        : emptyList())
                .build();
    }

    public static Pedido buildPedido(List<ItemPedido> itens, ClienteJsonResponse cliente, BigDecimal valorTotal, int numeroPedido) {
        return Pedido.builder()
                .id(UUID.randomUUID())
                .itens(itens)
                .cpf(cliente.cpf())
                .dataCriacao(now())
                .valorTotal(valorTotal)
                .numeroPedido(numeroPedido)
                .status(ABERTO)
                .build();
    }

    public static int gerarNumeroPedidoAleatorio() {
        return new Random().nextInt(900_000) + 100_000; // 6 dígitos
    }

    public static void checkNotNull(Object obj, String errorMessage) {
        if(obj == null){
            throw new RuntimeException(errorMessage);
        }
    }
}