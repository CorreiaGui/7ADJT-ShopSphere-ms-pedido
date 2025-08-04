package br.com.fiap.ms.pedidoservice.gateway.database.jpa;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.ItemPedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ItemPedidoJpaGatewayTest {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @InjectMocks
    private ItemPedidoJpaGateway itemPedidoJpaGateway;

    private ItemPedidoEntity entity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        entity = ItemPedidoEntity.builder()
                .numeroPedido(123)
                .sku("JEANS-42")
                .quantidade(2)
                .build();
    }

    @Test
    void salvarTodos_deveChamarRepositoryESalvarItens() {
        List<ItemPedidoEntity> itens = List.of(entity);
        when(itemPedidoRepository.saveAll(itens)).thenReturn(itens);

        List<ItemPedidoEntity> resultado = itemPedidoJpaGateway.salvarTodos(itens);

        verify(itemPedidoRepository, times(1)).saveAll(itens);
        assertEquals(1, resultado.size());
        assertEquals("JEANS-42", resultado.get(0).getSku());
    }

    @Test
    void buscarItensPorPedidoId_deveChamarRepositoryEBuscarItens() {
        when(itemPedidoRepository.findByNumeroPedido(123)).thenReturn(List.of(entity));

        List<ItemPedidoEntity> resultado = itemPedidoJpaGateway.buscarItensPorPedidoId(123);

        verify(itemPedidoRepository, times(1)).findByNumeroPedido(123);
        assertEquals(1, resultado.size());
        assertEquals("JEANS-42", resultado.get(0).getSku());
    }
}
