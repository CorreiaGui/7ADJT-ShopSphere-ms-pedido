package br.com.fiap.ms.pedidoservice.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    private UUID id;

    private Integer numeroPedido;

    private String sku;

    private Integer quantidade;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataUltimaAlteracao;

}
