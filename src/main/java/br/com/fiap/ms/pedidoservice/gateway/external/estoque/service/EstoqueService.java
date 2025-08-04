package br.com.fiap.ms.pedidoservice.gateway.external.estoque.service;

import br.com.fiap.ms.pedidoservice.gateway.external.estoque.EstoqueFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueService implements IEstoqueService {

    private final EstoqueFeignClient estoqueFeignClient;

    @Override
    public String baixarEstoque(String sku, Long quantidade) {
        return estoqueFeignClient.baixarEstoque(sku, quantidade);
    }

    @Override
    public String reporEstoque(String sku, Long quantidade) {
        return estoqueFeignClient.reporEstoque(sku, quantidade);
    }

}
