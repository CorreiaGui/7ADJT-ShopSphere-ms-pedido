package br.com.fiap.ms.pedidoservice.gateway.api.restclient.cliente.service;

import br.com.fiap.ms.pedidoservice.domain.Cliente;
import br.com.fiap.ms.pedidoservice.gateway.ClienteGateway;
import br.com.fiap.ms.pedidoservice.gateway.api.restclient.cliente.request.ClienteJsonRequest;
import br.com.fiap.ms.pedidoservice.gateway.api.restclient.cliente.response.ClienteJsonResponse;
import br.com.fiap.ms.pedidoservice.gateway.api.restclient.cliente.request.EnderecoJsonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteRestClientGateway implements ClienteGateway {

    @Override
    public List<ClienteJsonResponse> buscarClientes() {
        RestClient customClient = RestClient.builder()
                .baseUrl("http://java-app:8080")
                .defaultHeader("Content-Type", "application/json")
            .build();
        ArrayList<ClienteJsonResponse> body = customClient.get()
                .uri("/api/v1/clientes")
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<ArrayList<ClienteJsonResponse>>() {});
        return new ArrayList();
    }

    @Override
    public ClienteJsonResponse buscarClientesPorCpf() {
        RestClient customClient = RestClient.builder()
                .baseUrl("http://java-app:8080")
                .defaultHeader("Content-Type", "application/json")
                .build();
        ClienteJsonResponse body = customClient.get()
                .uri("/api/v1/clientes/{id}", "12345678902")
                .header("Content-Type", "application/json")
                .retrieve()
            .body(ClienteJsonResponse.class);
        return body;
    }

    @Override
    public ClienteJsonResponse criarCliente(Cliente cliente) {
        EnderecoJsonRequest endereco = new EnderecoJsonRequest(
            "Rua Exemplo",
            123,
            "02328010",
            "Apto 11",
            "Centro",
            "São Paulo"
        );

        ClienteJsonRequest cliente1 = new ClienteJsonRequest(
            "12345678907",
            "John Doe",
            LocalDate.of(1990, 1, 1),
            endereco
        );

        RestClient customClient = RestClient.builder()
                .baseUrl("http://java-app:8080")
                .defaultHeader("Content-Type", "application/json")
            .build();
        ClienteJsonResponse body = customClient.post()
                .uri("/api/v1/clientes")
                .header("Content-Type", "application/json")
                .body(cliente1)
                .retrieve()
                .body(ClienteJsonResponse.class);
        return body;
    }

    @Override
    public ClienteJsonResponse alterarCliente(String cpf, Cliente cliente) {
        return null;
    }
}
