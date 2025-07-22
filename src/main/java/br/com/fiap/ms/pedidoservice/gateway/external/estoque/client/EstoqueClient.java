package br.com.fiap.ms.pedidoservice.gateway.external.estoque.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoqueClient", url = "${ESTOQUE_URL}", path = "/api/v1/estoques")
public interface EstoqueClient {

    @PostMapping("/baixa")
    String baixarEstoque(@RequestParam("sku") String sku,
                         @RequestParam("quantidade") Long quantidade);

    @PostMapping("/repor")
    String reporEstoque(@RequestParam("sku") String sku,
                        @RequestParam("quantidade") Long quantidade);
}
