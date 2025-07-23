package br.com.fiap.ms.pedidoservice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemPedido {

    private String sku;
    private Integer quantidade;

}
