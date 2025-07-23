package br.com.fiap.ms.pedidoservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String idCliente;
    private List<ItemPedido> itens;
    private String numeroCartao;
    private Double valorTotal;
    private String status;

}
