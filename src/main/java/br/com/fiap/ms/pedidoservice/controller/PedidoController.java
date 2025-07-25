package br.com.fiap.ms.pedidoservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.PRODUCES;
import static br.com.fiap.ms.pedidoservice.utils.PedidoConstants.V1_PEDIDOS_PAGAMENTOS;

@Slf4j
@RestController
@RequestMapping(value = V1_PEDIDOS_PAGAMENTOS, produces = PRODUCES)
@RequiredArgsConstructor
public class PedidoController {


}
