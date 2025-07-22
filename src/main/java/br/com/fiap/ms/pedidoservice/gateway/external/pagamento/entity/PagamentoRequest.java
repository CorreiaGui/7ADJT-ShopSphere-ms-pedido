package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoRequest {
    private UUID pedidoId;
    private Integer formaPagamento;
    private String numeroCartaoCredito;
    private BigDecimal valor;
}