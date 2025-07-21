package br.com.fiap.ms.pedidoservice.gateway.external.cliente.entity;

import java.time.LocalDate;

public record ClienteJsonRequest(
        /*
        @NotNull(message = "CPF é obrigatório")
        @NotEmpty(message = "CPF não pode ser vazio")
        */
        String cpf,
        /*
        @NotNull(message = "Nome é obrigatório")
        @NotEmpty(message = "Nome não pode ser vazio")
        */
        String nome,
        /*
        @NotNull(message = "Data de nascimento é obrigatório")
        */
        LocalDate dataNascimento,
        EnderecoJsonRequest enderecoJsonRequest
) {}