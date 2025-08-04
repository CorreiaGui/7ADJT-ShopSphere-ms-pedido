package br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno;

import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoExternoClient", url = "${PAGAMENTO_EXTERNO_URL}", path = "/api/v1/pagamentos_externos")
public interface PagamentoExternoFeignClient {
    @PostMapping
    String obterStatusPagamento(@RequestBody PagamentoExternoRequest pagamentoExternoRequest);
}
