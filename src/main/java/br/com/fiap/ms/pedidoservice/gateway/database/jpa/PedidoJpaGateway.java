package br.com.fiap.ms.pedidoservice.gateway.database.jpa;

import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PedidoJpaGateway implements PedidoGateway {

    private final PedidoRepository pedidoRepository;

    @Override
    public PedidoEntity salvar(PedidoEntity pedidoEntity) {
        return pedidoRepository.save(pedidoEntity);
    }

    @Override
    public PedidoEntity buscarPorId(UUID id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public PedidoEntity buscarPorNumeroPedido(Integer numeroPedido) {
        return pedidoRepository.findByNumeroPedido(numeroPedido).orElse(null);
    }
}