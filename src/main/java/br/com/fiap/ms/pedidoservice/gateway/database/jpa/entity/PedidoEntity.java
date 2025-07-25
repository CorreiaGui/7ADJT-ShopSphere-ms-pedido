package br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity;

import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    private UUID id;

    @Column(name = "numero_pedido", nullable = false, unique = true)
    private Integer numeroPedido;

    @Column(name = "pagamento_id", nullable = false)
    private UUID pagamentoId;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedido status;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @OneToMany(mappedBy = "pedido", cascade = ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> itens;
}
