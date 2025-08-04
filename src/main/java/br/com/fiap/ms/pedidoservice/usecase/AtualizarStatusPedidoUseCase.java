package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.AtualizarStatusRequestJson;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.fiap.ms.pedidoservice.utils.PedidoUtils.checkNotNull;
import static java.time.LocalDateTime.now;

@Service
public class AtualizarStatusPedidoUseCase {

    @Autowired
    private PedidoGateway gateway;

    public void atualizarPedido(AtualizarStatusRequestJson json, UUID id){
        PedidoEntity pedidoBusca = gateway.buscarPorId(id);
        checkNotNull(pedidoBusca, "Pedido não encontrado.");
        pedidoBusca.setStatus(json.status());
        pedidoBusca.setDataUltimaAlteracao(now());
        gateway.salvar(pedidoBusca);
    }
}
