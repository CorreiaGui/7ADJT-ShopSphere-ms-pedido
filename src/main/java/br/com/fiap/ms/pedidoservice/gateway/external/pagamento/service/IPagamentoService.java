package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response.PagamentoResponse;

import java.util.UUID;

public interface IPagamentoService {
    String criarPagamento(PagamentoRequest pagamentoRequest);

    PagamentoResponse buscarPorIdExterno(UUID id);
}
