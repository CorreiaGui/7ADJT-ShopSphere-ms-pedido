package br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno.PagamentoExternoFeignClient;
import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PagamentoExternoService implements IPagamentoExternoService {

    private final PagamentoExternoFeignClient pagamentoExternoFeignClient;
    private final Random random = new Random();

    private static final String[] STATUS_PAGAMENTO = {
            "PAGAMENTO_CONFIRMADO",
            "PAGAMENTO_REJEITADO_FALTA_CREDITO"
    };

    @Override
    public String obterStatusPagamento(PagamentoExternoRequest pagamentoExternoRequest) {
        // Simulação de chamada REST para um serviço externo
        int index = random.nextInt(STATUS_PAGAMENTO.length);
        return STATUS_PAGAMENTO[index];

    }

}
