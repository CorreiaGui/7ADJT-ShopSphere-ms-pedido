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
    private List<String> skus;
    private List<Integer> quantidades;
    private String numeroCartao;
    private Double valorTotal;
    private String status;

}
