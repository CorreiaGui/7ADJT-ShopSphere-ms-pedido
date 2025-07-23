package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.PedidoNumeroGenerator;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.mapper.PedidoEntityMapper;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.PedidoRepository;
import org.springframework.stereotype.Service;

/**
 * Serviço de persistência de pedidos.
 * Responsável por salvar e buscar pedidos no repositório.
 */
@Service
public class PedidoPersistenceService {

    private final PedidoRepository pedidoRepository;
    private final PedidoNumeroGenerator pedidoNumeroGenerator;

    public PedidoPersistenceService(
            PedidoRepository pedidoRepository,
            PedidoNumeroGenerator pedidoNumeroGenerator
    ) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoNumeroGenerator = pedidoNumeroGenerator;
    }

    public void salvar(Pedido pedido) {
        Integer numeroPedido = pedidoNumeroGenerator.gerarNumeroPedido();
        PedidoEntity entity = PedidoEntityMapper.toEntity(pedido, numeroPedido);
        pedidoRepository.save(entity);
    }

    public PedidoEntity buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
