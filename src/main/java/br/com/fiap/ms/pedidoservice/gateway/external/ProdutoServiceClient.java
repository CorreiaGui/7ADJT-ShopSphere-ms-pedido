package br.com.fiap.ms.pedidoservice.gateway.external;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Cliente para interagir com o serviço de produtos.
 * Simula a validação dos SKUs dos produtos.
 */
@Component
public class ProdutoServiceClient {
    public boolean produtosValidos(List<String> skus) {
        return !skus.contains("INVALIDO");
    }
}
