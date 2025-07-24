package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;

public interface IPagamentoService {
    void criarPagamento(PagamentoRequest pagamentoRequest);
}
