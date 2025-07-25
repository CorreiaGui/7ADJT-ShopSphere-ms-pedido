package br.com.fiap.ms.pedidoservice.controller.json;

import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.usecase.PedidoPersistenceService;
import br.com.fiap.ms.pedidoservice.usecase.ProcessarPedidoUseCase;
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
    private final ProcessarPedidoUseCase processarPedidoUseCase;

    public PedidoController(PedidoPersistenceService service,
                            ProcessarPedidoUseCase processarPedidoUseCase) {
        this.service = service;
        this.processarPedidoUseCase = processarPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> receberPedido(@RequestBody Pedido pedido) {
        processarPedidoUseCase.processar(pedido);
        return ResponseEntity.ok().build();
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
