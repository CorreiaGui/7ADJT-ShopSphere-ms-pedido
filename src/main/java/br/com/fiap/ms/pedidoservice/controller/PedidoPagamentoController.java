package br.com.fiap.ms.pedidoservice.controller;

import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;
import br.com.fiap.ms.pedidoservice.usecase.ProcessarPagamentoExternoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.PRODUCES;
import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.V1_PEDIDOS_PAGAMENTOS;

@Slf4j
@RestController
@RequestMapping(value = V1_PEDIDOS_PAGAMENTOS, produces = PRODUCES)
@RequiredArgsConstructor
public class PedidoPagamentoController {

    private final ProcessarPagamentoExternoUseCase processarPagamentoExternoUseCase;

    @PostMapping
    public ResponseEntity<String> processarPagamentoExterno(@RequestBody PagamentoExternoRequest pagamentoExternoRequest) {
        log.info("POST | {} | Iniciada criação de pedido | request: {}", V1_PEDIDOS_PAGAMENTOS, pagamentoExternoRequest);

        String status = processarPagamentoExternoUseCase.executar(pagamentoExternoRequest);

        log.info("POST | {} | Notificação de pagamento recebida com sucesso | response: {}", V1_PEDIDOS_PAGAMENTOS, pagamentoExternoRequest.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }
}
