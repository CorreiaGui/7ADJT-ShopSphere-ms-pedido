package br.com.fiap.ms.pedidoservice.controller;

import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.entity.ProdutoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.service.ProdutoService;
import br.com.fiap.ms.pedidoservice.usecase.PedidoPersistenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gerenciar pedidos.
 * Permite buscar pedidos por ID e testar a conectividade com o serviço.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoPersistenceService service;
    private final ProdutoService produtoService;

    public PedidoController(PedidoPersistenceService service, ProdutoService produtoService) {
        this.service = service;
        this.produtoService = produtoService;
    }

    @GetMapping("/produto/{sku}")
    public ResponseEntity<ProdutoResponse> buscarProduto(@PathVariable String sku) {
        ProdutoResponse produto = produtoService.buscarPorSku(sku);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> buscarPorId(@PathVariable Long id) {
        PedidoEntity pedido = service.buscarPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    // Teste
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
