package br.com.fiap.ms.pedidoservice.gateway.external.cliente.service;

import br.com.fiap.ms.pedidoservice.gateway.external.cliente.ClienteFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.EnderecoJsonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteFeignClient clienteFeignClient;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteJsonResponse clienteResponse;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        EnderecoJsonResponse endereco = new EnderecoJsonResponse(
                UUID.randomUUID(),
                "Rua das Flores",
                123,
                "12345-678",
                "Apto 1017",
                "Centro",
                "São Paulo",
                LocalDateTime.now(),
                null
        );

        clienteResponse = new ClienteJsonResponse(
                "12345678900",
                "João da Silva",
                LocalDate.of(2000, 5, 20),
                LocalDateTime.now(),
                null,
                endereco
        );
    }

    @Test
    void buscarClientesPorCpf_deveDelegarParaFeignClientERetornarResponse() {
        String cpf = "12345678900";
        when(clienteFeignClient.buscarClientesPorCpf(cpf)).thenReturn(clienteResponse);

        ClienteJsonResponse resultado = clienteService.buscarClientesPorCpf(cpf);

        verify(clienteFeignClient, times(1)).buscarClientesPorCpf(cpf);
        assertEquals(clienteResponse, resultado);
        assertEquals("João da Silva", resultado.nome());
        assertEquals("São Paulo", resultado.endereco().cidade());
    }
}
