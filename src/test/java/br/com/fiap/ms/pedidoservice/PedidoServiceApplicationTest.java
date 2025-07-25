package br.com.fiap.ms.pedidoservice;

import br.com.fiap.ms.pedidoservice.gateway.external.cliente.ClienteFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.estoque.EstoqueFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.PagamentoFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.ProdutoFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoServiceApplicationTest {

    @MockBean
    private ClienteFeignClient clienteFeignClient;

    @MockBean
    private EstoqueFeignClient estoqueFeignClient;

    @MockBean
    private PagamentoFeignClient pagamentoFeignClient;

    @MockBean
    private ProdutoFeignClient produtoFeignClient;

    @Test
    void contextLoads() {
    }
}
