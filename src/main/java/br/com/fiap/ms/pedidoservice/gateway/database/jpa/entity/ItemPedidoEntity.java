package br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    @Column(name = "numero_pedido", nullable = false)
    private Integer numeroPedido;

    @Column(name = "sku", nullable = false, length = 255)
    private String sku;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @ManyToOne
    @JoinColumn(name = "numero_pedido", referencedColumnName = "numero_pedido", insertable = false, updatable = false)
    private PedidoEntity pedido;
}
