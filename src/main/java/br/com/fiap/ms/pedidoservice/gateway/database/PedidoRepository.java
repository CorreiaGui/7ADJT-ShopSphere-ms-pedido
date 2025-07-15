package br.com.fiap.ms.pedidoservice.gateway.database;

import br.com.fiap.ms.pedidoservice.domain.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para gerenciar entidades de Pedido.
 * Extende JpaRepository para operações CRUD e consultas personalizadas.
 */
@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {}
