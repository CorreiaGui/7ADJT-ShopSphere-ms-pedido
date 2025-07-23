package br.com.fiap.ms.pedidoservice.gateway.database.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Classe responsável por gerar o próximo número de pedido.
 * Utiliza EntityManager para consultar o último número de pedido e incrementar.
 */
@Repository
public class PedidoNumeroGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer gerarNumeroPedido() {
        Integer ultimoNumero = (Integer) entityManager
                .createQuery("SELECT MAX(p.numeroPedido) FROM PedidoEntity p")
                .getSingleResult();

        return (ultimoNumero == null) ? 1 : ultimoNumero + 1;
    }
}
