package br.com.fiap.ms.pedidoservice.gateway.database.jpa;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoJpaGatewayTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoJpaGateway pedidoJpaGateway;

    private PedidoEntity pedidoEntity;
    private UUID id;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        id = UUID.randomUUID();
        pedidoEntity = PedidoEntity.builder()
                .id(id)
                .numeroPedido(123456)
                .build();
    }

    @Test
    void salvar_deveChamarRepositoryESalvar() {
        when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoEntity);

        PedidoEntity resultado = pedidoJpaGateway.salvar(pedidoEntity);

        verify(pedidoRepository, times(1)).save(pedidoEntity);
        assertEquals(123456, resultado.getNumeroPedido());
    }

    @Test
    void buscarPorId_deveRetornarPedidoQuandoEncontrado() {
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoEntity));

        PedidoEntity resultado = pedidoJpaGateway.buscarPorId(id);

        verify(pedidoRepository, times(1)).findById(id);
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void buscarPorId_deveRetornarNullQuandoNaoEncontrado() {
        when(pedidoRepository.findById(id)).thenReturn(Optional.empty());

        PedidoEntity resultado = pedidoJpaGateway.buscarPorId(id);

        assertNull(resultado);
    }

    @Test
    void buscarPorNumeroPedido_deveRetornarPedidoQuandoEncontrado() {
        when(pedidoRepository.findByNumeroPedido(123456)).thenReturn(Optional.of(pedidoEntity));

        PedidoEntity resultado = pedidoJpaGateway.buscarPorNumeroPedido(123456);

        verify(pedidoRepository, times(1)).findByNumeroPedido(123456);
        assertNotNull(resultado);
        assertEquals(123456, resultado.getNumeroPedido());
    }

    @Test
    void buscarPorNumeroPedido_deveRetornarNullQuandoNaoEncontrado() {
        when(pedidoRepository.findByNumeroPedido(123456)).thenReturn(Optional.empty());

        PedidoEntity resultado = pedidoJpaGateway.buscarPorNumeroPedido(123456);

        assertNull(resultado);
    }
}
