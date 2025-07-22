package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.client;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.entity.PagamentoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoClient", url = "${PAGAMENTO_URL}")
public interface PagamentoFeignClient {
    @PostMapping("/api/v1/pagamentos")
    void criarPagamento(@RequestBody PagamentoRequest pagamentoRequest);
}
