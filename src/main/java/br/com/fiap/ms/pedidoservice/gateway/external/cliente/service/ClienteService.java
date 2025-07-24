package br.com.fiap.ms.pedidoservice.gateway.external.cliente.service;

import br.com.fiap.ms.pedidoservice.gateway.external.cliente.ClienteFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService{

    private final ClienteFeignClient clienteFeignClient;

    @Override
    public ClienteJsonResponse buscarClientesPorCpf(String cpf) {
        return clienteFeignClient.buscarClientesPorCpf(cpf);
    }

}
