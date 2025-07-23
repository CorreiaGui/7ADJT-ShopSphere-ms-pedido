package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.PedidoNumeroGenerator;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PedidoPersistenceServiceTest {

    private PedidoRepository pedidoRepository;
    private PedidoNumeroGenerator pedidoNumeroGenerator;
    private PedidoPersistenceService pedidoPersistenceService;

    @BeforeEach
    void setUp() {
        pedidoRepository = mock(PedidoRepository.class);
        pedidoNumeroGenerator = mock(PedidoNumeroGenerator.class);
        pedidoPersistenceService = new PedidoPersistenceService(pedidoRepository, pedidoNumeroGenerator);
    }

    @Test
    void deveSalvarPedidoComNumeroGerado() {
        // Arrange
        when(pedidoNumeroGenerator.gerarNumeroPedido()).thenReturn(1001);

        Pedido pedido = new Pedido();
        pedido.setIdCliente("123");
        pedido.setNumeroCartao("4111111111111111");
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(150.00);
        pedido.setItens(Arrays.asList(
                new ItemPedido("SKU123", 1),
                new ItemPedido("SKU456", 2)
        ));

        // Act
        pedidoPersistenceService.salvar(pedido);

        // Assert
        verify(pedidoNumeroGenerator, times(1)).gerarNumeroPedido();
        verify(pedidoRepository, times(1)).save(argThat(entity ->
                entity.getCpf().equals("123") &&
                        entity.getNumeroPedido().equals(1001) &&
                        entity.getPagamentoId().equals("4111111111111111") &&
                        entity.getStatus().equals("ABERTO") &&
                        entity.getValorTotal().compareTo(BigDecimal.valueOf(150.00)) == 0 &&
                        entity.getItens().size() == 2
        ));
    }

    @Test
    void deveBuscarPedidoPorId() {
        // Arrange
        PedidoEntity entity = PedidoEntity.builder()
                .id(String.valueOf(1L))
                .cpf("456")
                .status("FECHADO")
                .valorTotal(BigDecimal.valueOf(200))
                .pagamentoId("9999999999999999")
                .build();

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(entity));

        // Act
        PedidoEntity resultado = pedidoPersistenceService.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("456", resultado.getCpf());
        assertEquals("FECHADO", resultado.getStatus());
        assertEquals("9999999999999999", resultado.getPagamentoId());
    }

    @Test
    void deveRetornarNullSeNaoEncontrarPedido() {
        // Arrange
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        PedidoEntity resultado = pedidoPersistenceService.buscarPorId(99L);

        // Assert
        assertNull(resultado);
    }
}
