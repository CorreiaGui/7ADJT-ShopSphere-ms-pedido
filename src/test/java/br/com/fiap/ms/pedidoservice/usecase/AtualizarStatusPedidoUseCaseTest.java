package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.AtualizarStatusRequestJson;
import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarStatusPedidoUseCaseTest {

    @InjectMocks
    private AtualizarStatusPedidoUseCase useCase;

    @Mock
    private PedidoGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarStatusEPersistirQuandoPedidoExistir() {
        UUID pedidoId = UUID.randomUUID();
        AtualizarStatusRequestJson json = mock(AtualizarStatusRequestJson.class);
        when(json.status()).thenReturn(StatusPedido.ABERTO);

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setStatus(StatusPedido.ABERTO);

        when(gateway.buscarPorId(pedidoId)).thenReturn(pedidoEntity);

        useCase.atualizarPedido(json, pedidoId);

        assertEquals(StatusPedido.ABERTO, pedidoEntity.getStatus());
        assertNotNull(pedidoEntity.getDataUltimaAlteracao());
        verify(gateway).salvar(pedidoEntity);
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoExistir() {
        UUID pedidoId = UUID.randomUUID();
        AtualizarStatusRequestJson json = mock(AtualizarStatusRequestJson.class);

        when(gateway.buscarPorId(pedidoId)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            useCase.atualizarPedido(json, pedidoId);
        });

        assertEquals("Pedido não encontrado.", exception.getMessage());
    }

}
