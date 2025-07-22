package br.com.fiap.ms.pedidoservice.gateway.external.estoque.service;

import br.com.fiap.ms.pedidoservice.gateway.external.estoque.client.EstoqueClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueService implements IEstoqueService {

    private final EstoqueClient estoqueClient;

    @Override
    public String baixarEstoque(String sku, Long quantidade) {
        return estoqueClient.baixarEstoque(sku, quantidade);
    }

    @Override
    public String reporEstoque(String sku, Long quantidade) {
        return estoqueClient.reporEstoque(sku, quantidade);
    }

}
