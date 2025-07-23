package br.com.fiap.ms.pedidoservice.gateway.database.jpa.mapper;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEntityMapperTest {

    @Test
    void deveConverterPedidoParaEntity() {
        // Arrange
        ItemPedido item1 = new ItemPedido("SKU123", 2);
        ItemPedido item2 = new ItemPedido("SKU456", 1);

        Pedido pedido = new Pedido("123", Arrays.asList(item1, item2), "4111111111111111", 99.99, "ABERTO");

        // Act
        PedidoEntity entity = PedidoEntityMapper.toEntity(pedido, 42);

        // Assert
        assertNotNull(entity);
        assertEquals("123", entity.getCpf());
        assertEquals("ABERTO", entity.getStatus());
        assertEquals("4111111111111111", entity.getPagamentoId());
        assertEquals(42, entity.getNumeroPedido());
        assertEquals(BigDecimal.valueOf(99.99), entity.getValorTotal());

        assertNotNull(entity.getDataCriacao());
        assertNull(entity.getDataUltimaAlteracao());

        List<ItemPedidoEntity> itens = entity.getItens();
        assertEquals(2, itens.size());
        assertEquals("SKU123", itens.get(0).getSku());
        assertEquals(2, itens.get(0).getQuantidade());
        assertEquals(entity, itens.get(0).getPedido());
    }

    @Test
    void deveConverterEntityParaPedido() {
        // Arrange
        PedidoEntity entity = PedidoEntity.builder()
                .cpf("456")
                .numeroPedido(99)
                .status("FECHADO_COM_SUCESSO")
                .valorTotal(BigDecimal.valueOf(199.90))
                .pagamentoId("1234567890123456")
                .build();

        ItemPedidoEntity item1 = ItemPedidoEntity.builder()
                .sku("SKU789")
                .quantidade(3)
                .pedido(entity)
                .build();

        ItemPedidoEntity item2 = ItemPedidoEntity.builder()
                .sku("SKU999")
                .quantidade(5)
                .pedido(entity)
                .build();

        entity.setItens(Arrays.asList(item1, item2));

        // Act
        Pedido pedido = PedidoEntityMapper.toDomain(entity);

        // Assert
        assertNotNull(pedido);
        assertEquals("456", pedido.getIdCliente());
        assertEquals("1234567890123456", pedido.getNumeroCartao());
        assertEquals("FECHADO_COM_SUCESSO", pedido.getStatus());
        assertEquals(199.90, pedido.getValorTotal());

        List<ItemPedido> itens = pedido.getItens();
        assertEquals(2, itens.size());
        assertEquals("SKU789", itens.get(0).getSku());
        assertEquals(3, itens.get(0).getQuantidade());
    }
}
