package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class BuscarPedidosUseCase {

    @Autowired
    private PedidoGateway pedidoGateway;

    public List<PedidoResponseJson> buscarPedidos(int page, int size) {
        List<PedidoResponseJson> pedidos = pedidoGateway.buscarTodos(page, size);
        if (pedidos == null){
            return emptyList();
        }
        return pedidos;
    }
}
