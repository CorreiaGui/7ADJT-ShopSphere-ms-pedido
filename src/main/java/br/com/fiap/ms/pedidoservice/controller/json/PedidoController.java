package br.com.fiap.ms.pedidoservice.controller.json;

import br.com.fiap.ms.pedidoservice.domain.PedidoEntity;
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

    public PedidoController(PedidoPersistenceService service) {
        this.service = service;
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
