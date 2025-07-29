package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.PagamentoFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response.PagamentoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.response.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService implements IPagamentoService {

    private final PagamentoFeignClient pagamentoFeignClient;

    @Override
    public String criarPagamento(PagamentoRequest pagamentoRequest) {
        return pagamentoFeignClient.criarPagamento(pagamentoRequest);
    }

    @Override
    public PagamentoResponse buscarPorIdExterno(UUID id) {
        return pagamentoFeignClient.buscarPorIdExterno(id);
    }
}
