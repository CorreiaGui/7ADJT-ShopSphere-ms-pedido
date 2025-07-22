package br.com.fiap.ms.pedidoservice.gateway.external.produto.service;

import br.com.fiap.ms.pedidoservice.gateway.external.produto.entity.ProdutoResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoClient produtoClient;

    public ProdutoResponse buscarPorSku(String sku) {
        return produtoClient.buscarPorSku(sku);
    }
}
