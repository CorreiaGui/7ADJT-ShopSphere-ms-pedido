package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;

public interface IPagamentoService {
    String criarPagamento(PagamentoRequest pagamentoRequest);
}
