package br.com.fiap.ms.pedidoservice.gateway.database.jpa;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.PedidoRepository;
import br.com.fiap.ms.pedidoservice.utils.PedidoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    public List<PedidoResponseJson> buscarTodos(int page, int size) {
        Page<PedidoEntity> pedidos = pedidoRepository.findAll(PageRequest.of(page, size));
        return pedidos.stream()
                .map(PedidoUtils::convertToPedidoResponseJson)
                .collect(toList());
    }

    @Override
    public Optional<PedidoEntity> buscarById(UUID id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public PedidoEntity buscarPorNumeroPedido(Integer numeroPedido) {
        return pedidoRepository.findByNumeroPedido(numeroPedido).orElse(null);
    }
}