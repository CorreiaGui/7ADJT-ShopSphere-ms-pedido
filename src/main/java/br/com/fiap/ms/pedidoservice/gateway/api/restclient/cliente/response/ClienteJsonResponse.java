package br.com.fiap.ms.pedidoservice.gateway.api.restclient.cliente.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClienteJsonResponse(
        String cpf,
        String nome,
        LocalDate dataNascimento,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAlteracao,
        EnderecoJsonResponse endereco
) {}
