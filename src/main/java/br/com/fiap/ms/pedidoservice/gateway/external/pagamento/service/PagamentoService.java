package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.PagamentoFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService implements IPagamentoService {

    private final PagamentoFeignClient pagamentoFeignClient;

    @Override
    public String criarPagamento(PagamentoRequest pagamentoRequest) {
        return pagamentoFeignClient.criarPagamento(pagamentoRequest);
    }
}
