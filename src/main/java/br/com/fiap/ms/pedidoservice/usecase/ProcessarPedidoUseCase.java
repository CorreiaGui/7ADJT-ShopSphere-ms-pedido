package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.domain.Pedido;

/**
 * Interface que define o caso de uso para processar pedidos.
 * Este caso de uso é responsável por processar um pedido recebido,
 * incluindo a validação e o envio para o serviço de pagamento.
 */
public interface ProcessarPedidoUseCase {
    void processar(Pedido pedido);
}
