package br.com.fiap.ms.pedidoservice.gateway.external.cliente;

import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "clienteClient", url = "${CLIENTE_URL}", path = "/api/v1/clientes")
public interface ClienteFeignClient {

    @GetMapping("/{cpf}")
    public ClienteJsonResponse buscarClientesPorCpf(@PathVariable("cpf") String cpf);
}

