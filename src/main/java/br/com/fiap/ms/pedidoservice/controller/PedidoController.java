package br.com.fiap.ms.pedidoservice.controller;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.usecase.BuscarPedidoUseCase;
import br.com.fiap.ms.pedidoservice.usecase.BuscarPedidosUseCase;
import br.com.fiap.ms.pedidoservice.utils.PedidoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.PRODUCES;
import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.V1_PEDIDOS;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping(value = V1_PEDIDOS, produces = PRODUCES)
@RequiredArgsConstructor
public class PedidoController {

    @Autowired
    private BuscarPedidosUseCase buscar;

    @Autowired
    private BuscarPedidoUseCase buscarPedido;

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
    public ResponseEntity<String> atualizarPedido(@PathVariable("id") UUID id, @RequestBody PedidoRequestJson request) {
        logger.info("PUT | {} | Iniciado updateMenu | id: {}", V1_MENU, id);
        menuService.updateMenu(convertToMenu(menuBodyRequest), id);
        logger.info("PUT | {} | Finalizado updateMenu", V1_MENU);
        return ok("Menu atualizado com sucesso");
    }
}
