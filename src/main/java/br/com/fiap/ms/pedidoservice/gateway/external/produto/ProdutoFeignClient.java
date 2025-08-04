package br.com.fiap.ms.pedidoservice.gateway.external.produto;

import br.com.fiap.ms.pedidoservice.gateway.external.produto.response.ProdutoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtoClient", url = "${PRODUTO_URL}", path = "/api/v1/produtos")
public interface ProdutoFeignClient {

    @GetMapping("/{sku}")
    ProdutoResponse buscarPorSku(@PathVariable("sku") String sku);
}
