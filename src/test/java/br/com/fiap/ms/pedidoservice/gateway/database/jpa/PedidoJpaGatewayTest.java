package br.com.fiap.ms.pedidoservice.gateway.database.jpa;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.PedidoRepository;
import br.com.fiap.ms.pedidoservice.utils.PedidoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

    @Test
    void buscarTodos_deveRetornarListaConvertida() {
        List<PedidoEntity> listaPedidos = List.of(pedidoEntity);
        Page<PedidoEntity> pagePedidos = new PageImpl<>(listaPedidos);

        when(pedidoRepository.findAll(any(PageRequest.class))).thenReturn(pagePedidos);

        // Mock do método estático PedidoUtils.convertToPedidoResponseJson
        try (MockedStatic<PedidoUtils> mockedStatic = mockStatic(PedidoUtils.class)) {
            PedidoResponseJson responseJsonMock =  new PedidoResponseJson(UUID.randomUUID(), 123, "33344455566",
                    new BigDecimal(30.0), UUID.randomUUID(), LocalDateTime.now(), null, StatusPedido.ABERTO,
                    null);
            mockedStatic.when(() -> PedidoUtils.convertToPedidoResponseJson(any(PedidoEntity.class)))
                    .thenReturn(responseJsonMock);

            List<PedidoResponseJson> resultado = pedidoJpaGateway.buscarTodos(0, 10);

            verify(pedidoRepository, times(1)).findAll(any(PageRequest.class));
            mockedStatic.verify(() -> PedidoUtils.convertToPedidoResponseJson(any(PedidoEntity.class)), times(1));

            assertNotNull(resultado);
            assertEquals(1, resultado.size());
            assertSame(responseJsonMock, resultado.get(0));
        }
    }

    @Test
    void buscarById_deveRetornarOptionalQuandoEncontrado() {
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoEntity));

        Optional<PedidoEntity> resultado = pedidoJpaGateway.buscarById(id);

        verify(pedidoRepository, times(1)).findById(id);
        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
    }

    @Test
    void buscarById_deveRetornarOptionalVazioQuandoNaoEncontrado() {
        when(pedidoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<PedidoEntity> resultado = pedidoJpaGateway.buscarById(id);

        assertFalse(resultado.isPresent());
    }
}
