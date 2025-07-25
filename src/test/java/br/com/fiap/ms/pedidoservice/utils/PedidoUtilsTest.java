package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class PedidoUtilsTest {

    @Test
    void convertToPedido_deveConverterComItens() {
        ItemPedidoEntity itemEntity = ItemPedidoEntity.builder()
                .id(UUID.randomUUID())
                .build();

        PedidoEntity pedidoEntity = PedidoEntity.builder()
                .id(UUID.randomUUID())
                .numeroPedido(123456)
                .valorTotal(BigDecimal.valueOf(100.0))
                .cpf("12345678900")
                .pagamentoId(UUID.randomUUID())
                .dataCriacao(LocalDateTime.now())
                .dataUltimaAlteracao(LocalDateTime.now())
                .itens(List.of(itemEntity))
                .build();

        ItemPedido itemPedidoMock = ItemPedido.builder().id(UUID.randomUUID()).build();

        try (MockedStatic<ItemPedidoUtils> mockedStatic = mockStatic(ItemPedidoUtils.class)) {
            mockedStatic.when(() -> ItemPedidoUtils.convertToItemPedido(itemEntity)).thenReturn(itemPedidoMock);

            Pedido pedido = PedidoUtils.convertToPedido(pedidoEntity);

            assertNotNull(pedido);
            assertEquals(pedidoEntity.getId(), pedido.getId());
            assertEquals(pedidoEntity.getNumeroPedido(), pedido.getNumeroPedido());
            assertEquals(pedidoEntity.getValorTotal(), pedido.getValorTotal());
            assertEquals(pedidoEntity.getCpf(), pedido.getCpf());
            assertEquals(pedidoEntity.getPagamentoId(), pedido.getPagamentoId());
            assertEquals(1, pedido.getItens().size());
            assertEquals(itemPedidoMock, pedido.getItens().get(0));

            mockedStatic.verify(() -> ItemPedidoUtils.convertToItemPedido(itemEntity));
        }
    }

    @Test
    void convertToPedido_deveConverterComListaItensNula() {
        PedidoEntity pedidoEntity = PedidoEntity.builder()
                .id(UUID.randomUUID())
                .itens(null)
                .build();

        Pedido pedido = PedidoUtils.convertToPedido(pedidoEntity);

        assertNotNull(pedido);
        assertNotNull(pedido.getItens());
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void convertToPedidoEntity_deveConverterComItens() {
        ItemPedido itemPedido = ItemPedido.builder()
                .id(UUID.randomUUID())
                .build();

        Pedido pedido = Pedido.builder()
                .id(UUID.randomUUID())
                .numeroPedido(123456)
                .valorTotal(BigDecimal.valueOf(100.0))
                .cpf("12345678900")
                .pagamentoId(UUID.randomUUID())
                .status(StatusPedido.ABERTO)
                .dataCriacao(LocalDateTime.now())
                .dataUltimaAlteracao(LocalDateTime.now())
                .itens(List.of(itemPedido))
                .build();

        ItemPedidoEntity itemEntityMock = ItemPedidoEntity.builder().id(UUID.randomUUID()).build();

        try (MockedStatic<ItemPedidoUtils> mockedStatic = mockStatic(ItemPedidoUtils.class)) {
            mockedStatic.when(() -> ItemPedidoUtils.convertToItemPedidoEntity(itemPedido)).thenReturn(itemEntityMock);

            PedidoEntity entity = PedidoUtils.convertToPedidoEntity(pedido);

            assertNotNull(entity);
            assertEquals(pedido.getId(), entity.getId());
            assertEquals(pedido.getNumeroPedido(), entity.getNumeroPedido());
            assertEquals(pedido.getValorTotal(), entity.getValorTotal());
            assertEquals(pedido.getCpf(), entity.getCpf());
            assertEquals(pedido.getPagamentoId(), entity.getPagamentoId());
            assertEquals(pedido.getStatus(), entity.getStatus());
            assertEquals(1, entity.getItens().size());
            assertEquals(itemEntityMock, entity.getItens().get(0));

            mockedStatic.verify(() -> ItemPedidoUtils.convertToItemPedidoEntity(itemPedido));
        }
    }

    @Test
    void convertToPedidoEntity_deveConverterComListaItensNula() {
        Pedido pedido = Pedido.builder()
                .id(UUID.randomUUID())
                .itens(null)
                .build();

        PedidoEntity entity = PedidoUtils.convertToPedidoEntity(pedido);

        assertNotNull(entity);
        assertNotNull(entity.getItens());
        assertTrue(entity.getItens().isEmpty());
    }

    @Test
    void convertToPedidoResponseJson_deveConverterCorretamente() {
        LocalDateTime now = LocalDateTime.now();
        PedidoEntity entity = PedidoEntity.builder()
                .id(UUID.randomUUID())
                .numeroPedido(123456)
                .cpf("12345678900")
                .valorTotal(BigDecimal.valueOf(100.0))
                .pagamentoId(UUID.randomUUID())
                .dataCriacao(now)
                .build();

        PedidoResponseJson responseJson = PedidoUtils.convertToPedidoResponseJson(entity);

        assertNotNull(responseJson);
        assertEquals(entity.getId(), responseJson.id());
        assertEquals(entity.getNumeroPedido(), responseJson.numeroPedido());
        assertEquals(entity.getCpf(), responseJson.documentoCliente());
        assertEquals(entity.getValorTotal(), responseJson.valorTotal());
        assertEquals(entity.getPagamentoId(), responseJson.pagamentoId());
        assertEquals(entity.getDataCriacao(), responseJson.dataCriacao());
    }

    @Test
    void gerarNumeroPedidoAleatorio_deveGerarNumeroCom6Digitos() {
        int numero = PedidoUtils.gerarNumeroPedidoAleatorio();

        assertTrue(numero >= 100_000 && numero <= 999_999,
                "Número gerado deve ter 6 dígitos e estar entre 100000 e 999999");
    }
}
