package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExcluirPedidoUseCase {

    @Autowired
    private PedidoGateway gateway;

    public void excluirPedido(UUID id) {
        gateway.excluirPedido(id);
    }
}
