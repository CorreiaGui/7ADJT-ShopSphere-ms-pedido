package br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {

    Optional<PedidoEntity> findByNumeroPedido(Integer numeroPedido);
}