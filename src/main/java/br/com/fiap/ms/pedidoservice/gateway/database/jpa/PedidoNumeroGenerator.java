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
        Number result = (Number) entityManager
                .createQuery("SELECT MAX(p.numeroPedido) FROM PedidoEntity p")
                .getSingleResult();

        int proximoNumero = (result == null) ? 1 : result.intValue() + 1;

        System.out.println("Próximo número de pedido gerado: " + proximoNumero);
        return proximoNumero;
    }
}
