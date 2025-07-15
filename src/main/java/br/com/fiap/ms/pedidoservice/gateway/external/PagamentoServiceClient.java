package br.com.fiap.ms.pedidoservice.gateway.external;

import br.com.fiap.ms.pedidoservice.domain.Pedido;
import org.springframework.stereotype.Component;

/**
 * Simula a comunicação com o serviço de pagamento.
 * Neste exemplo, o pagamento é considerado processado se o valor total do pedido for menor ou igual a 1000.
 */
@Component
public class PagamentoServiceClient {
    public boolean processarPagamento(Pedido pedido) {
        return pedido.getValorTotal() <= 1000;
    }

    public void estornarPagamento(Pedido pedido) {
        System.out.println("Pagamento estornado para o cliente " + pedido.getIdCliente());
    }
}
