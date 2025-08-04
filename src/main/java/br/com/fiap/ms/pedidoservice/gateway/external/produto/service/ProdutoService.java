package br.com.fiap.ms.pedidoservice.gateway.external.produto.service;

import br.com.fiap.ms.pedidoservice.gateway.external.produto.ProdutoFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.response.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final ProdutoFeignClient produtoFeignClient;

    @Override
    public ProdutoResponse buscarPorSku(String sku) {
        return produtoFeignClient.buscarPorSku(sku);
    }

}
