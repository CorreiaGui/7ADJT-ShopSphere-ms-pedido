package br.com.fiap.ms.pedidoservice.gateway.external.pagamento;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response.PagamentoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.response.ProdutoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "pagamentoClient", url = "${PAGAMENTO_URL}", path = "/api/v1/pagamentos")
public interface PagamentoFeignClient {
    @PostMapping
    String criarPagamento(@RequestBody PagamentoRequest pagamentoRequest);

    @GetMapping("/pagamento-externo/{id}")
    PagamentoResponse buscarPorIdExterno(@PathVariable("id") UUID id);
}
