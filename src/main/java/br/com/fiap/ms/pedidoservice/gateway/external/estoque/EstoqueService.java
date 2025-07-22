package br.com.fiap.ms.pedidoservice.gateway.external.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueService {

    @Autowired
    EstoqueClient estoqueClient;

    public String baixarEstoque(String sku, Long quantidade) {
        return estoqueClient.baixarEstoque(sku, quantidade);
    }

    public String reporEstoque(String sku, Long quantidade) {
        return estoqueClient.reporEstoque(sku, quantidade);
    }
}
