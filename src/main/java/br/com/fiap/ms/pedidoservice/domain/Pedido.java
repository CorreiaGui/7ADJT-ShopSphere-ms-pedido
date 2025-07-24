package br.com.fiap.ms.pedidoservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private UUID id;

    private Integer numeroPedido;

    private String cpf;

    private BigDecimal valorTotal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataUltimaAlteracao;

    private List<ItemPedido> itens;

    private UUID pagamentoId;

}