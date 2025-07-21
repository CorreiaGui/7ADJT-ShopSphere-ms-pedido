package br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idCliente;
    private String numeroCartao;
    private Double valorTotal;
    private String status;

    @ElementCollection
    private List<String> skus;

    @ElementCollection
    private List<Integer> quantidades;
}
