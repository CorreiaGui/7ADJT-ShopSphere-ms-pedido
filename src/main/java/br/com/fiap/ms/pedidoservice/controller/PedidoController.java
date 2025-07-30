package br.com.fiap.ms.pedidoservice.controller;

import br.com.fiap.ms.pedidoservice.controller.json.AtualizarStatusRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.usecase.AtualizarStatusPedidoUseCase;
import br.com.fiap.ms.pedidoservice.usecase.BuscarPedidoUseCase;
import br.com.fiap.ms.pedidoservice.usecase.BuscarPedidosUseCase;
import br.com.fiap.ms.pedidoservice.usecase.ExcluirPedidoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.PRODUCES;
import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.V1_PEDIDOS;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.*;

@Slf4j
@RestController
@RequestMapping(value = V1_PEDIDOS, produces = PRODUCES)
@RequiredArgsConstructor
public class PedidoController {

    @Autowired
    private BuscarPedidosUseCase buscar;

    @Autowired
    private BuscarPedidoUseCase buscarPedido;

    @Autowired
    private AtualizarStatusPedidoUseCase atualizar;

    @Autowired
    private ExcluirPedidoUseCase excluir;

    @GetMapping
    public ResponseEntity<List<PedidoResponseJson>> buscarPedidos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("GET | {} | Iniciando busca de pedidos com paginação | page: {} size: {}", V1_PEDIDOS, page, size);
        List<PedidoResponseJson> pedidos = buscar.buscarPedidos(page, size);
        log.info("GET | {} | Finalizada busca de pedidos com paginação | pedidos encontrados: {}", V1_PEDIDOS, pedidos.size());
        return ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseJson> buscarPedido(@PathVariable("id") UUID id) {
        log.info("GET | {} | Iniciando busca de pedido | id: {}", V1_PEDIDOS, id);
        PedidoResponseJson pedido = buscarPedido.buscarPedidoById(id);
        log.info("GET | {} | Finalizada busca de pedido | Pedido número: {}", V1_PEDIDOS, pedido.numeroPedido());
        return ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPedido(@PathVariable("id") UUID id, @RequestBody AtualizarStatusRequestJson request) {
        log.info("PUT | {} | Iniciado atualização de status do Pedido | id: {}", V1_PEDIDOS, id);
        atualizar.atualizarPedido(request, id);
        log.info("PUT | {} | Status do Pedido atualizado com sucesso", V1_PEDIDOS);
        return ok("Status do Pedido atualizado com sucesso status: " + request.status());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPedido(@PathVariable("id") UUID id) {
        log.info("DELETE | {} | Iniciado exclusão de pedido | id: {}", V1_PEDIDOS, id);
        try {
            excluir.excluirPedido(id);
            log.info("DELETE | {} | Pedido deletado com sucesso | Id: {}", V1_PEDIDOS, id);
            return noContent().build();
        } catch (RuntimeException e) {
            log.error("DELETE | {} | Erro ao deletar Pedido | Id: {} | Erro: {}", V1_PEDIDOS, id, e.getMessage());
            return status(NOT_FOUND).body("Pedido não encontrado");
        }
    }
}
