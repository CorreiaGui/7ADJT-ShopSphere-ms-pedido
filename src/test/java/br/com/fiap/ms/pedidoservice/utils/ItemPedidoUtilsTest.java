package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.controller.json.ItemPedidoRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.ItemPedidoResponseJson;
import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.fiap.ms.pedidoservice.utils.ItemPedidoUtils.convertToItemPedido;
import static br.com.fiap.ms.pedidoservice.utils.ItemPedidoUtils.convertToItemPedidoResponseJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemPedidoUtilsTest {

    @Test
    void convertToItemPedido_deveConverterCorretamente() {
        ItemPedidoEntity entity = ItemPedidoEntity.builder()
                .id(UUID.randomUUID())
                .sku("CAMISA-VERDE-P")
                .numeroPedido(20)
                .quantidade(5)
                .dataCriacao(LocalDateTime.of(2025, 7, 25, 12, 0))
                .dataUltimaAlteracao(LocalDateTime.of(2025, 7, 25, 12, 30))
                .build();

        ItemPedido itemPedido = convertToItemPedido(entity);

        assertNotNull(itemPedido);
        assertEquals(entity.getId(), itemPedido.getId());
        assertEquals(entity.getSku(), itemPedido.getSku());
        assertEquals(entity.getNumeroPedido(), itemPedido.getNumeroPedido());
        assertEquals(entity.getQuantidade(), itemPedido.getQuantidade());
        assertEquals(entity.getDataCriacao(), itemPedido.getDataCriacao());
        assertEquals(entity.getDataUltimaAlteracao(), itemPedido.getDataUltimaAlteracao());
    }

    @Test
    void convertToItemPedidoEntity_deveConverterCorretamente() {
        ItemPedido itemPedido = ItemPedido.builder()
                .id(UUID.randomUUID())
                .sku("CAMISA-VERDE-PP")
                .numeroPedido(30)
                .quantidade(10)
                .dataCriacao(LocalDateTime.of(2025, 7, 20, 10, 0))
                .dataUltimaAlteracao(LocalDateTime.of(2025, 7, 20, 11, 0))
                .build();

        ItemPedidoEntity entity = ItemPedidoUtils.convertToItemPedidoEntity(itemPedido);

        assertNotNull(entity);
        assertEquals(itemPedido.getId(), entity.getId());
        assertEquals(itemPedido.getSku(), entity.getSku());
        assertEquals(itemPedido.getNumeroPedido(), entity.getNumeroPedido());
        assertEquals(itemPedido.getQuantidade(), entity.getQuantidade());
        assertEquals(itemPedido.getDataCriacao(), entity.getDataCriacao());
        assertEquals(itemPedido.getDataUltimaAlteracao(), entity.getDataUltimaAlteracao());
    }


    @Test
    void convertToItemPedido_shouldMapFieldsCorrectly() {
        ItemPedidoRequestJson dto = mock(ItemPedidoRequestJson.class);
        when(dto.sku()).thenReturn("CALCA-MARROM-G");
        when(dto.quantidade()).thenReturn(5L);

        int numeroPedido = 10;

        ItemPedido result = convertToItemPedido(dto, numeroPedido);

        assertNotNull(result);
        assertEquals("CALCA-MARROM-G", result.getSku());
        assertEquals(5, result.getQuantidade());
        assertEquals(numeroPedido, result.getNumeroPedido());
    }

    @Test
    void convertToItemPedidoResponseJson_shouldMapFieldsCorrectly() {
        ItemPedidoEntity entity = mock(ItemPedidoEntity.class);
        when(entity.getSku()).thenReturn("CALCA-GG");
        when(entity.getQuantidade()).thenReturn(3);

        ItemPedidoResponseJson result = convertToItemPedidoResponseJson(entity);

        assertNotNull(result);
        assertEquals("CALCA-GG", result.sku());
        assertEquals(3, result.quantidade());
    }
}
