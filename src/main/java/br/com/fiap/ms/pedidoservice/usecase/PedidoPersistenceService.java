package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository.PedidoRepository;
import org.springframework.stereotype.Service;

/**
 * Serviço de persistência de pedidos.
 * Responsável por salvar e buscar pedidos no repositório.
 */
@Service
public class PedidoPersistenceService {

    private final PedidoRepository repository;

    public PedidoPersistenceService(PedidoRepository repository) {
        this.repository = repository;
    }

    /**
     * Salva um pedido no repositório.
     *
     * @param pedido O pedido a ser salvo.
     * @return O PedidoEntity salvo.
     */
    public PedidoEntity salvar(Pedido pedido) {
        PedidoEntity entity = PedidoEntity.builder()
                .idCliente(pedido.getIdCliente())
                .numeroCartao(pedido.getNumeroCartao())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus())
                .skus(pedido.getSkus())
                .quantidades(pedido.getQuantidades())
                .build();

        return repository.save(entity);
    }

    public PedidoEntity buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
