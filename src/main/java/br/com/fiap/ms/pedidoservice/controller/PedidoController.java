package br.com.fiap.ms.pedidoservice.controller;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.PedidoResponseJson;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.usecase.CriarPedidoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.PRODUCES;
import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.V1_PEDIDOS;
import static br.com.fiap.ms.pedidoservice.utils.PedidoUtils.convertToPedidoResponseJson;

@Slf4j
@RestController
@RequestMapping(value = V1_PEDIDOS, produces = PRODUCES)
@RequiredArgsConstructor
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;

    @PostMapping
    public ResponseEntity<PedidoResponseJson> criarPedido(@RequestBody PedidoRequestJson pedidoRequestJson) {
        log.info("POST | {} | Iniciada criação de pedido | request: {}", V1_PEDIDOS, pedidoRequestJson);

        PedidoEntity pedidoCriado = criarPedidoUseCase.criarPedido(pedidoRequestJson);
        PedidoResponseJson pedidoResponseJson = convertToPedidoResponseJson(pedidoCriado);

        log.info("POST | {} | Pedido criado com sucesso | response: {}", V1_PEDIDOS, pedidoResponseJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponseJson);
    }
}
