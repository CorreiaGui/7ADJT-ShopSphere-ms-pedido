package br.com.fiap.ms.pedidoservice.gateway.external.produto.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {
    private String sku;

    private String nome;

    private BigDecimal preco;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataUltimaAlteracao;
}
