package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.client.PagamentoFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.entity.PagamentoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService implements IPagamentoService {

    private final PagamentoFeignClient pagamentoFeignClient;

    @Override
    public void criarPagamento(PagamentoRequest pagamentoRequest) {
        pagamentoFeignClient.criarPagamento(pagamentoRequest);
    }
}
