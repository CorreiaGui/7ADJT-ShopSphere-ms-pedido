package br.com.fiap.ms.pedidoservice.gateway.external.cliente.request;

import java.time.LocalDate;

public record ClienteJsonRequest(
        String cpf,
        String nome,
        LocalDate dataNascimento,
        EnderecoJsonRequest enderecoJsonRequest
) {}