package br.com.fiap.ms.pedidoservice.gateway.external.cliente.entity;

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
