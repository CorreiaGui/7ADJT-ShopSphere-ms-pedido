package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.utils.PedidoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPedidoUseCaseTest {

    @InjectMocks
    private BuscarPedidoUseCase useCase;

    @Mock
    private PedidoGateway pedidoGateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarPedidoQuandoEncontrar() {
        UUID id = UUID.randomUUID();
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        PedidoResponseJson pedidoResponse = mock(PedidoResponseJson.class);

        when(pedidoGateway.buscarById(id)).thenReturn(Optional.of(pedidoEntity));

        try (MockedStatic<PedidoUtils> utilities = mockStatic(PedidoUtils.class)) {
            utilities.when(() -> PedidoUtils.convertToPedidoResponseJson(pedidoEntity))
                    .thenReturn(pedidoResponse);

            PedidoResponseJson resultado = useCase.buscarPedidoById(id);

            assertNotNull(resultado);
            assertEquals(pedidoResponse, resultado);
        }
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoEncontrado() {
        UUID id = UUID.randomUUID();

        when(pedidoGateway.buscarById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            useCase.buscarPedidoById(id);
        });
    }
}
