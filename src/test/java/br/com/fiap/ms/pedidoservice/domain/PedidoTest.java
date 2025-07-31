package br.com.fiap.ms.pedidoservice.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PedidoTest {

    @Test
    void deveConstruirPedidoComBuilder() {
        UUID id = UUID.randomUUID();
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        Pedido pedido = Pedido.builder()
                .id(id)
                .numeroPedido(123)
                .cpf("12345678900")
                .valorTotal(new BigDecimal("150.00"))
                .dataCriacao(agora)
                .dataUltimaAlteracao(agora)
                .itens(Collections.emptyList())
                .pagamentoId(pagamentoId)
                .status(StatusPedido.ABERTO)
                .build();

        assertThat(pedido.getId()).isEqualTo(id);
        assertThat(pedido.getNumeroPedido()).isEqualTo(123);
        assertThat(pedido.getCpf()).isEqualTo("12345678900");
        assertThat(pedido.getValorTotal()).isEqualByComparingTo("150.00");
        assertThat(pedido.getDataCriacao()).isEqualTo(agora);
        assertThat(pedido.getDataUltimaAlteracao()).isEqualTo(agora);
        assertThat(pedido.getItens()).isEmpty();
        assertThat(pedido.getPagamentoId()).isEqualTo(pagamentoId);
        assertThat(pedido.getStatus()).isEqualTo(StatusPedido.ABERTO);
    }

    @Test
    void deveConstruirPedidoComAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        Pedido pedido = new Pedido(
                id,
                999,
                "98765432100",
                new BigDecimal("99.99"),
                agora,
                agora,
                Collections.emptyList(),
                pagamentoId,
                StatusPedido.FECHADO_COM_SUCESSO
        );

        assertThat(pedido.getId()).isEqualTo(id);
        assertThat(pedido.getNumeroPedido()).isEqualTo(999);
        assertThat(pedido.getCpf()).isEqualTo("98765432100");
        assertThat(pedido.getValorTotal()).isEqualTo(new BigDecimal("99.99"));
        assertThat(pedido.getDataCriacao()).isEqualTo(agora);
        assertThat(pedido.getDataUltimaAlteracao()).isEqualTo(agora);
        assertThat(pedido.getItens()).isEmpty();
        assertThat(pedido.getPagamentoId()).isEqualTo(pagamentoId);
        assertThat(pedido.getStatus()).isEqualTo(StatusPedido.FECHADO_COM_SUCESSO);
    }

    @Test
    void deveSetarCamposComSetters() {
        Pedido pedido = new Pedido();

        UUID id = UUID.randomUUID();
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        pedido.setId(id);
        pedido.setNumeroPedido(888);
        pedido.setCpf("00011122233");
        pedido.setValorTotal(new BigDecimal("42.42"));
        pedido.setDataCriacao(agora);
        pedido.setDataUltimaAlteracao(agora);
        pedido.setItens(Collections.emptyList());
        pedido.setPagamentoId(pagamentoId);
        pedido.setStatus(StatusPedido.FECHADO_SEM_ESTOQUE);

        assertThat(pedido.getId()).isEqualTo(id);
        assertThat(pedido.getNumeroPedido()).isEqualTo(888);
        assertThat(pedido.getCpf()).isEqualTo("00011122233");
        assertThat(pedido.getValorTotal()).isEqualByComparingTo("42.42");
        assertThat(pedido.getDataCriacao()).isEqualTo(agora);
        assertThat(pedido.getDataUltimaAlteracao()).isEqualTo(agora);
        assertThat(pedido.getItens()).isEmpty();
        assertThat(pedido.getPagamentoId()).isEqualTo(pagamentoId);
        assertThat(pedido.getStatus()).isEqualTo(StatusPedido.FECHADO_SEM_ESTOQUE);
    }

    @Test
    void deveGerarToStringComConteudo() {
        Pedido pedido = Pedido.builder()
                .numeroPedido(555)
                .cpf("99988877766")
                .valorTotal(new BigDecimal("123.45"))
                .build();

        String texto = pedido.toString();

        assertThat(texto).contains("555");
        assertThat(texto).contains("99988877766");
        assertThat(texto).contains("123.45");
    }

    @Test
    void deveGerarToStringDoBuilderDePedido() {
        Pedido.PedidoBuilder builder = Pedido.builder()
                .numeroPedido(999)
                .cpf("99988877700");

        String builderToString = builder.toString();

        assertThat(builderToString).contains("999");
        assertThat(builderToString).contains("99988877700");
    }

}
