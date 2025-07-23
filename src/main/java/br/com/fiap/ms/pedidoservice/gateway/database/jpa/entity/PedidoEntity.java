package br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @Column(name = "numero_pedido", nullable = false, unique = true)
    private Integer numeroPedido;

    @Column(name = "pagamento_id", nullable = false)
    private String pagamentoId;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemPedidoEntity> itens;

}
