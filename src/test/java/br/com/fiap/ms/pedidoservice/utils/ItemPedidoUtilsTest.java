package br.com.fiap.ms.pedidoservice.utils;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

        ItemPedido itemPedido = ItemPedidoUtils.convertToItemPedido(entity);

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
}
