package br.com.fiap.ms.pedidoservice.gateway.external.produto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record ProdutoResponse(String sku,
                              String nome,
                              BigDecimal preco,
                              LocalDateTime dataCriacao,
                              LocalDateTime dataUltimaAlteracao) {
}
