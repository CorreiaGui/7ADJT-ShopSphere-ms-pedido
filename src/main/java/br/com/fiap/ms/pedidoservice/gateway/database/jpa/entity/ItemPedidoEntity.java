package br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_pedido", referencedColumnName = "numero_pedido", nullable = false)
    private PedidoEntity pedido;
}

