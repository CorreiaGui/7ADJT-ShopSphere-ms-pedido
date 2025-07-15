package br.com.fiap.ms.pedidoservice.gateway.external;

import org.springframework.stereotype.Component;

/**
 * Cliente para interagir com o serviço de clientes.
 * Simula a verificação da existência de um cliente.
 */
@Component
public class ClienteServiceClient {
    public boolean clienteExiste(String idCliente) {
        return !"999".equals(idCliente); // simula que o cliente 999 não existe
    }
}
