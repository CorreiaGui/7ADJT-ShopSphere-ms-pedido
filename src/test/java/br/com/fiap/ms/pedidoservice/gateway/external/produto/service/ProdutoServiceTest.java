package br.com.fiap.ms.pedidoservice.gateway.external.produto.service;

import br.com.fiap.ms.pedidoservice.gateway.external.produto.ProdutoFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.response.ProdutoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoFeignClient produtoFeignClient;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPorSku_deveDelegarParaFeignClientERetornarProdutoResponse() {
        String sku = "SKU123";
        ProdutoResponse produtoEsperado = new ProdutoResponse(
                sku,
                "JAQUETA JEANS",
                new BigDecimal("100.50"),
                LocalDateTime.now(),
                null
        );

        when(produtoFeignClient.buscarPorSku(sku)).thenReturn(produtoEsperado);

        ProdutoResponse resultado = produtoService.buscarPorSku(sku);

        verify(produtoFeignClient, times(1)).buscarPorSku(sku);
        assertEquals(produtoEsperado, resultado);
    }
}
