package br.com.fiap.ms.pedidoservice.gateway.database.jpa.repository;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, UUID> {

    List<ItemPedidoEntity> findByNumeroPedido(Integer numeroPedido);
}