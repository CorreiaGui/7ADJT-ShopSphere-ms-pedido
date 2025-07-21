package br.com.fiap.ms.pedidoservice.gateway.external.cliente.service;

import br.com.fiap.ms.pedidoservice.domain.Cliente;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.entity.ClienteJsonResponse;

import java.util.List;

public interface IClienteService {
    public List<ClienteJsonResponse> buscarClientes();
    public ClienteJsonResponse buscarClientesPorCpf();
    public ClienteJsonResponse criarCliente(Cliente cliente);
    public ClienteJsonResponse alterarCliente(String cpf, Cliente cliente);
}
