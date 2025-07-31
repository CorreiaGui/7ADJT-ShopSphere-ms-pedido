package br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity;

import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEntityTest {

    @Test
    void deveCriarPedidoComBuilderComTodosOsCampos() {
        // Given
        UUID id = UUID.randomUUID();
        Integer numeroPedido = 1234;
        UUID pagamentoId = UUID.randomUUID();
        String cpf = "12345678901";
        StatusPedido status = StatusPedido.ABERTO;
        BigDecimal valorTotal = new BigDecimal("99.90");
        LocalDateTime dataCriacao = LocalDateTime.now().minusDays(1);
        LocalDateTime dataUltimaAlteracao = LocalDateTime.now();

        ItemPedidoEntity item1 = ItemPedidoEntity.builder()
                .sku("PROD-001")
                .quantidade(2)
                .numeroPedido(numeroPedido)
                .build();

        // When
        PedidoEntity pedido = PedidoEntity.builder()
                .id(id)
                .numeroPedido(numeroPedido)
                .pagamentoId(pagamentoId)
                .cpf(cpf)
                .status(status)
                .valorTotal(valorTotal)
                .dataCriacao(dataCriacao)
                .dataUltimaAlteracao(dataUltimaAlteracao)
                .itens(List.of(item1))
                .build();

        // Then
        assertEquals(id, pedido.getId());
        assertEquals(numeroPedido, pedido.getNumeroPedido());
        assertEquals(pagamentoId, pedido.getPagamentoId());
        assertEquals(cpf, pedido.getCpf());
        assertEquals(status, pedido.getStatus());
        assertEquals(valorTotal, pedido.getValorTotal());
        assertEquals(dataCriacao, pedido.getDataCriacao());
        assertEquals(dataUltimaAlteracao, pedido.getDataUltimaAlteracao());
        assertNotNull(pedido.getItens());
        assertEquals(1, pedido.getItens().size());
        assertEquals("PROD-001", pedido.getItens().get(0).getSku());
    }

    @Test
    void deveSetarTodosOsValoresViaSetters() {
        // Given
        PedidoEntity pedido = new PedidoEntity();
        UUID id = UUID.randomUUID();
        UUID pagamentoId = UUID.randomUUID();
        Integer numeroPedido = 5555;
        String cpf = "99988877766";
        BigDecimal valor = new BigDecimal("120.50");
        StatusPedido status = StatusPedido.FECHADO_COM_SUCESSO;
        LocalDateTime criacao = LocalDateTime.now().minusHours(3);
        LocalDateTime alteracao = LocalDateTime.now();

        // When
        pedido.setId(id);
        pedido.setNumeroPedido(numeroPedido);
        pedido.setPagamentoId(pagamentoId);
        pedido.setCpf(cpf);
        pedido.setStatus(status);
        pedido.setValorTotal(valor);
        pedido.setDataCriacao(criacao);
        pedido.setDataUltimaAlteracao(alteracao);
        pedido.setItens(List.of());

        // Then
        assertEquals(id, pedido.getId());
        assertEquals(numeroPedido, pedido.getNumeroPedido());
        assertEquals(pagamentoId, pedido.getPagamentoId());
        assertEquals(cpf, pedido.getCpf());
        assertEquals(status, pedido.getStatus());
        assertEquals(valor, pedido.getValorTotal());
        assertEquals(criacao, pedido.getDataCriacao());
        assertEquals(alteracao, pedido.getDataUltimaAlteracao());
        assertNotNull(pedido.getItens());
        assertTrue(pedido.getItens().isEmpty());
    }
}
