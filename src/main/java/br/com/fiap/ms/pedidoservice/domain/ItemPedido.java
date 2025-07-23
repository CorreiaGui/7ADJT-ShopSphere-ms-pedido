package br.com.fiap.ms.pedidoservice.domain;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    private UUID id;

    private Integer numeroPedido;

    private String sku;

    private Integer quantidade;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataUltimaAlteracao;

    private PedidoEntity pedido;
}
