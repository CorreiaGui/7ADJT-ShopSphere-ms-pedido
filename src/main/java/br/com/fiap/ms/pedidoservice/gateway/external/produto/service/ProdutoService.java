package br.com.fiap.ms.pedidoservice.gateway.external.produto.service;

import br.com.fiap.ms.pedidoservice.gateway.external.produto.client.ProdutoClient;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.entity.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final ProdutoClient produtoClient;

    @Override
    public ProdutoResponse buscarPorSku(String sku) {
        return produtoClient.buscarPorSku(sku);
    }

}
