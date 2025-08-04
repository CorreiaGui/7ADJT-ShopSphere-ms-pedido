package br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoEntityTest {

    @Test
    void deveConstruirItemPedidoEntityComBuilderECamposPreenchidos() {
        // given
        UUID id = UUID.randomUUID();
        Integer numeroPedido = 12345;
        String sku = "SKU-TESTE";
        Integer quantidade = 10;
        LocalDateTime dataCriacao = LocalDateTime.now().minusDays(1);
        LocalDateTime dataUltimaAlteracao = LocalDateTime.now();

        PedidoEntity pedido = PedidoEntity.builder()
                .numeroPedido(numeroPedido)
                .cpf("12345678900")
                .build();

        // when
        ItemPedidoEntity item = ItemPedidoEntity.builder()
                .id(id)
                .numeroPedido(numeroPedido)
                .sku(sku)
                .quantidade(quantidade)
                .dataCriacao(dataCriacao)
                .dataUltimaAlteracao(dataUltimaAlteracao)
                .pedido(pedido)
                .build();

        // then
        assertEquals(id, item.getId());
        assertEquals(numeroPedido, item.getNumeroPedido());
        assertEquals(sku, item.getSku());
        assertEquals(quantidade, item.getQuantidade());
        assertEquals(dataCriacao, item.getDataCriacao());
        assertEquals(dataUltimaAlteracao, item.getDataUltimaAlteracao());
        assertNotNull(item.getPedido());
        assertEquals(pedido, item.getPedido());
        assertEquals(numeroPedido, item.getPedido().getNumeroPedido());
    }
}
