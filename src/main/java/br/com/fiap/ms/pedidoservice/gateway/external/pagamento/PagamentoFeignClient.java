package br.com.fiap.ms.pedidoservice.gateway.external.pagamento;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoClient", url = "${PAGAMENTO_URL}", path = "/api/v1/pagamentos")
public interface PagamentoFeignClient {
    @PostMapping()
    String criarPagamento(@RequestBody PagamentoRequest pagamentoRequest);
}
