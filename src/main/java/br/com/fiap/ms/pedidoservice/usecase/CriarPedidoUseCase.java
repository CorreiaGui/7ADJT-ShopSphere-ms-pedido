package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.ItemPedidoRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.ClienteFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.estoque.service.EstoqueService;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.entity.ProdutoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.service.ProdutoService;
import br.com.fiap.ms.pedidoservice.utils.PedidoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.ms.pedidoservice.utils.PedidoUtils.gerarNumeroPedidoAleatorio;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;


@Service
@RequiredArgsConstructor
public class CriarPedidoUseCase {

    @Autowired
    private final PedidoGateway pedidoGateway;

    @Autowired
    private final ProdutoService produtoService;

    @Autowired
    private final ClienteFeignClient clienteClient;

    @Autowired
    private final EstoqueService estoqueService;


    public PedidoEntity criarPedido(PedidoRequestJson pedidoRequestJson) {

        //cliente
        ClienteJsonResponse cliente = clienteClient.buscarClientesPorCpf(pedidoRequestJson.documentoCliente());

        List<ItemPedido> itens = new ArrayList<>();

        BigDecimal valorTotal = ZERO;
        int numeroPedido = gerarNumeroPedidoAleatorio();

        for (ItemPedidoRequestJson itemDTO : pedidoRequestJson.itens()) {

            //produto
            ProdutoResponse produto = produtoService.buscarPorSku(itemDTO.sku());

            //estoque
            estoqueService.baixarEstoque(itemDTO.sku(), itemDTO.quantidade());

            BigDecimal subtotal = produto.getPreco().multiply(valueOf(itemDTO.quantidade()));

            valorTotal = valorTotal.add(subtotal);

            ItemPedido item = ItemPedido.builder()
                    .sku(itemDTO.sku())
                    .quantidade(Integer.valueOf(itemDTO.quantidade().toString()))
                    .numeroPedido(numeroPedido)
                    .build();

            itens.add(item);
        }

        Pedido pedido = Pedido.builder()
                .itens(itens)
                .cpf(cliente.cpf())
                .dataCriacao(now())
                .valorTotal(valorTotal)
                .numeroPedido(numeroPedido)
                .build();

        return pedidoGateway.salvar(PedidoUtils.convertToPedidoEntity(pedido));
    }

}