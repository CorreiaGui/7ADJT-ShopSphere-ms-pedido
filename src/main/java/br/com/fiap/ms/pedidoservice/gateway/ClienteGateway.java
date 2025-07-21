package br.com.fiap.ms.pedidoservice.gateway;

import br.com.fiap.ms.pedidoservice.domain.Cliente;
import br.com.fiap.ms.pedidoservice.gateway.api.restclient.cliente.response.ClienteJsonResponse;

import java.util.List;

public interface ClienteGateway {
    public List<ClienteJsonResponse> buscarClientes();
    public ClienteJsonResponse buscarClientesPorCpf();
    public ClienteJsonResponse criarCliente(Cliente cliente);
    public ClienteJsonResponse alterarCliente(String cpf, Cliente cliente);
}
