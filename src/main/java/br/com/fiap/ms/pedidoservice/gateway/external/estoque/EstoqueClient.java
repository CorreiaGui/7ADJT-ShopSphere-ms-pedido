package br.com.fiap.ms.pedidoservice.gateway.external.estoque;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-estoque", url = "${ESTOQUE_URL}")
public interface EstoqueClient {

    @PostMapping("/api/v1/estoques/baixa")
    String baixarEstoque(@RequestParam("sku") String sku,
                         @RequestParam("quantidade") Long quantidade);

    @PostMapping("/api/v1/estoques/repor")
    String reporEstoque(@RequestParam("sku") String sku,
                        @RequestParam("quantidade") Long quantidade);
}
