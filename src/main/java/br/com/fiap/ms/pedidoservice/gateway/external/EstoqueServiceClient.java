package br.com.fiap.ms.pedidoservice.gateway.external;

import br.com.fiap.ms.pedidoservice.domain.Pedido;
import org.springframework.stereotype.Component;

/**
 * Simula a comunicação com o serviço de estoque.
 * Neste exemplo, o estoque é considerado disponível se todas as quantidades dos produtos forem menores ou iguais a 10.
 */
@Component
public class EstoqueServiceClient {
    public boolean verificarEstoque(Pedido pedido) {
        return pedido.getQuantidades().stream().allMatch(q -> q <= 10);
    }
}
