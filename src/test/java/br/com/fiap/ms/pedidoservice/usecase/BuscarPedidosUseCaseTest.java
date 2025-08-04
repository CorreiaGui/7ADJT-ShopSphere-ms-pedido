package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPedidosUseCaseTest {

    @InjectMocks
    private BuscarPedidosUseCase useCase;

    @Mock
    private PedidoGateway pedidoGateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarListaDePedidosQuandoGatewayRetornarLista() {
        PedidoResponseJson pedido1 = mock(PedidoResponseJson.class);
        PedidoResponseJson pedido2 = mock(PedidoResponseJson.class);
        List<PedidoResponseJson> listaPedidos = List.of(pedido1, pedido2);

        when(pedidoGateway.buscarTodos(0, 10)).thenReturn(listaPedidos);

        List<PedidoResponseJson> resultado = useCase.buscarPedidos(0, 10);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(listaPedidos, resultado);
    }

    @Test
    void deveRetornarListaVaziaQuandoGatewayRetornarNull() {
        when(pedidoGateway.buscarTodos(0, 10)).thenReturn(null);

        List<PedidoResponseJson> resultado = useCase.buscarPedidos(0, 10);

        assertNotNull(resultado);
        assertEquals(emptyList(), resultado);
    }
}
