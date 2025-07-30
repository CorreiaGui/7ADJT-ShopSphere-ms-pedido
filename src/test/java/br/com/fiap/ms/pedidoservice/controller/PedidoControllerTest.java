package br.com.fiap.ms.pedidoservice.controller;

import br.com.fiap.ms.pedidoservice.controller.json.AtualizarStatusRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import br.com.fiap.ms.pedidoservice.usecase.AtualizarStatusPedidoUseCase;
import br.com.fiap.ms.pedidoservice.usecase.BuscarPedidoUseCase;
import br.com.fiap.ms.pedidoservice.usecase.BuscarPedidosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @Mock
    private BuscarPedidosUseCase buscar;

    @Mock
    private BuscarPedidoUseCase buscarPedido;

    @Mock
    private AtualizarStatusPedidoUseCase atualizar;

    @InjectMocks
    private PedidoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarPedidosComSucesso() {
        List<PedidoResponseJson> listaPedidos = List.of(
                new PedidoResponseJson(UUID.randomUUID(), 123, "33344455566",
                        new BigDecimal(30.0), UUID.randomUUID(), LocalDateTime.now(), null, StatusPedido.ABERTO,
                        null)
        );

        when(buscar.buscarPedidos(0, 10)).thenReturn(listaPedidos);

        ResponseEntity<List<PedidoResponseJson>> response = controller.buscarPedidos(0, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(buscar, times(1)).buscarPedidos(0, 10);
    }

    @Test
    void deveBuscarPedidoPorIdComSucesso() {
        UUID id = UUID.randomUUID();
        PedidoResponseJson pedido = new PedidoResponseJson(id, 123, "33344455566",
                new BigDecimal(30.0), UUID.randomUUID(), LocalDateTime.now(), null, StatusPedido.ABERTO,
                null);
        when(buscarPedido.buscarPedidoById(id)).thenReturn(pedido);

        ResponseEntity<PedidoResponseJson> response = controller.buscarPedido(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(123, response.getBody().numeroPedido());
        verify(buscarPedido, times(1)).buscarPedidoById(id);
    }

    @Test
    void deveAtualizarPedidoComSucesso() {
        UUID id = UUID.randomUUID();
        AtualizarStatusRequestJson request = new AtualizarStatusRequestJson(StatusPedido.ABERTO);

        doNothing().when(atualizar).atualizarPedido(request, id);

        ResponseEntity<String> response = controller.atualizarPedido(id, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Status do Pedido atualizado com sucesso status: ABERTO", response.getBody());
        verify(atualizar, times(1)).atualizarPedido(request, id);
    }
}
