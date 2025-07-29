package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.utils.PedidoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Service
public class BuscarPedidoUseCase {

    @Autowired
    private PedidoGateway pedidoGateway;

    public PedidoResponseJson buscarPedidoById(UUID id) {
        Optional<PedidoEntity> pedido = pedidoGateway.buscarById(id);
        return pedido.map(PedidoUtils::convertToPedidoResponseJson).orElseThrow(() -> new RuntimeException());
    }
}
