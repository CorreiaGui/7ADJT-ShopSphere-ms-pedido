package br.com.fiap.ms.pedidoservice.gateway.external.cliente.service;

import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;

public interface IClienteService {

    ClienteJsonResponse buscarClientesPorCpf(String cpf);
}
