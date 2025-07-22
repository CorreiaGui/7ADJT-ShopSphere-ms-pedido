package br.com.fiap.ms.pedidoservice.gateway.external.estoque.service;

public interface IEstoqueService {

    String baixarEstoque(String sku, Long quantidade);

    String reporEstoque(String sku, Long quantidade);
}
