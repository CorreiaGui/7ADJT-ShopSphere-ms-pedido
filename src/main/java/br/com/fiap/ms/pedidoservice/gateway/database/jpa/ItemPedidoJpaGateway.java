package br.com.fiap.ms.pedidoservice.gateway.database.jpa;

import br.com.fiap.ms.pedidoservice.gateway.ItemPedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.ItemPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemPedidoJpaGateway implements ItemPedidoGateway {

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public List<ItemPedidoEntity> salvarTodos(List<ItemPedidoEntity> itens) {
        return itemPedidoRepository.saveAll(itens);
    }

    @Override
    public List<ItemPedidoEntity> buscarItensPorPedidoId(Integer pedidoId) {
        return itemPedidoRepository.findByNumeroPedido(pedidoId);
    }
}