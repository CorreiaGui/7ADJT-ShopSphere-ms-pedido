package br.com.fiap.ms.pedidoservice.gateway.external.estoque.service;

import br.com.fiap.ms.pedidoservice.gateway.external.estoque.EstoqueFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EstoqueServiceTest {

    @Mock
    private EstoqueFeignClient estoqueFeignClient;

    @InjectMocks
    private EstoqueService estoqueService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void baixarEstoque_deveDelegarParaFeignClientERetornarString() {
        String sku = "JAQUETA-ROSA-M";
        Long quantidade = 10L;
        String retornoEsperado = "Estoque baixado com sucesso";

        when(estoqueFeignClient.baixarEstoque(sku, quantidade)).thenReturn(retornoEsperado);

        String resultado = estoqueService.baixarEstoque(sku, quantidade);

        verify(estoqueFeignClient, times(1)).baixarEstoque(sku, quantidade);
        assertEquals(retornoEsperado, resultado);
    }

    @Test
    void reporEstoque_deveDelegarParaFeignClientERetornarString() {
        String sku = "JAQUETA-ROSA-M";
        Long quantidade = 5L;
        String retornoEsperado = "Estoque reposto com sucesso";

        when(estoqueFeignClient.reporEstoque(sku, quantidade)).thenReturn(retornoEsperado);

        String resultado = estoqueService.reporEstoque(sku, quantidade);

        verify(estoqueFeignClient, times(1)).reporEstoque(sku, quantidade);
        assertEquals(retornoEsperado, resultado);
    }
}
