package br.com.fiap.ms.pedidoservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.PRODUCES;
import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.V1_PEDIDOS_PAGAMENTOS;

@Slf4j
@RestController
@RequestMapping(value = V1_PEDIDOS_PAGAMENTOS, produces = PRODUCES)
@RequiredArgsConstructor
public class PedidoPagamentoController {

    @PostMapping("/{id}")
    public ResponseEntity<String> processarPagamento(@PathVariable UUID pagamentoRequestId) {
        return null;
    }
}
