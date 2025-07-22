package br.com.fiap.ms.pedidoservice.gateway.external.produto.service;

import br.com.fiap.ms.pedidoservice.gateway.external.produto.entity.ProdutoResponse;

public interface IProdutoService {
    ProdutoResponse buscarPorSku(String sku);
}
