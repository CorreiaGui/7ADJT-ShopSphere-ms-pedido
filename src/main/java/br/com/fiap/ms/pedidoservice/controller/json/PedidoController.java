package br.com.fiap.ms.pedidoservice.controller.json;

import br.com.fiap.ms.pedidoservice.domain.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.entity.ClienteJsonResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.service.ClienteService;
import br.com.fiap.ms.pedidoservice.usecase.PedidoPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciar pedidos.
 * Permite buscar pedidos por ID e testar a conectividade com o serviço.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoPersistenceService service;
    private final ClienteService clienteService;

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

    @GetMapping("/ping2")
    public ResponseEntity<String> ping2() {
        return ResponseEntity.ok("pong2");
    }

    @GetMapping("/clientes")
    public ResponseEntity<String> clientes() {
        clienteService.buscarClientes();
        return ResponseEntity.ok("clientes");
    }

    @GetMapping("/clientesPorCpf")
    public ResponseEntity<String> clientesPorCpf() {
        clienteService.buscarClientesPorCpf();
        return ResponseEntity.ok("clientes por cpf");
    }

    @GetMapping("/novoClientePorCpf")
    public ResponseEntity<String> novoClientePorCpf() {
        clienteService.criarCliente(null);
        return ResponseEntity.ok("novo clientes por cpf");
    }

    @GetMapping("/alterarClientePorCpf")
    public ResponseEntity<String> alterarClientePorCpf() {
        clienteService.buscarClientesPorCpf();
        return ResponseEntity.ok("novo clientes por cpf");
    }
}
