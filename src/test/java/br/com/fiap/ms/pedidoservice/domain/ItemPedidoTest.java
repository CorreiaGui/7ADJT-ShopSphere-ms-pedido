package br.com.fiap.ms.pedidoservice.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ItemPedidoTest {

    @Test
    void deveConstruirItemPedidoComBuilder() {
        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        ItemPedido item = ItemPedido.builder()
                .id(id)
                .numeroPedido(10)
                .sku("PROD-001")
                .quantidade(5)
                .dataCriacao(agora)
                .dataUltimaAlteracao(agora)
                .build();

        assertThat(item.getId()).isEqualTo(id);
        assertThat(item.getNumeroPedido()).isEqualTo(10);
        assertThat(item.getSku()).isEqualTo("PROD-001");
        assertThat(item.getQuantidade()).isEqualTo(5);
        assertThat(item.getDataCriacao()).isEqualTo(agora);
        assertThat(item.getDataUltimaAlteracao()).isEqualTo(agora);
    }

    @Test
    void deveConstruirItemPedidoComAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        ItemPedido item = new ItemPedido(
                id,
                20,
                "ITEM-XYZ",
                3,
                agora,
                agora
        );

        assertThat(item.getId()).isEqualTo(id);
        assertThat(item.getNumeroPedido()).isEqualTo(20);
        assertThat(item.getSku()).isEqualTo("ITEM-XYZ");
        assertThat(item.getQuantidade()).isEqualTo(3);
        assertThat(item.getDataCriacao()).isEqualTo(agora);
        assertThat(item.getDataUltimaAlteracao()).isEqualTo(agora);
    }

    @Test
    void deveSetarCamposComSetters() {
        ItemPedido item = new ItemPedido();

        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        item.setId(id);
        item.setNumeroPedido(30);
        item.setSku("SKU-SETTER");
        item.setQuantidade(9);
        item.setDataCriacao(agora);
        item.setDataUltimaAlteracao(agora);

        assertThat(item.getId()).isEqualTo(id);
        assertThat(item.getNumeroPedido()).isEqualTo(30);
        assertThat(item.getSku()).isEqualTo("SKU-SETTER");
        assertThat(item.getQuantidade()).isEqualTo(9);
        assertThat(item.getDataCriacao()).isEqualTo(agora);
        assertThat(item.getDataUltimaAlteracao()).isEqualTo(agora);
    }

    @Test
    void deveGerarToStringComConteudo() {
        ItemPedido item = ItemPedido.builder()
                .sku("TOSTRING-TESTE")
                .quantidade(42)
                .build();

        String texto = item.toString();

        assertThat(texto).contains("TOSTRING-TESTE");
        assertThat(texto).contains("42");
    }

    @Test
    void deveGerarToStringDoBuilderDeItemPedido() {
        ItemPedido.ItemPedidoBuilder builder = ItemPedido.builder()
                .numeroPedido(77)
                .sku("BUILDER-TESTE");

        String builderToString = builder.toString();

        assertThat(builderToString).contains("77");
        assertThat(builderToString).contains("BUILDER-TESTE");
    }

}
