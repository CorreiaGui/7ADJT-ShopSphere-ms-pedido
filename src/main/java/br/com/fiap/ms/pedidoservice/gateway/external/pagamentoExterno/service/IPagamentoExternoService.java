package br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno.service;

import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;

public interface IPagamentoExternoService {
    String obterStatusPagamento(PagamentoExternoRequest pagamentoExternoRequest);
}
