package br.com.fiap.ms.pedidoservice.gateway.external.cliente.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record EnderecoJsonResponse(
    UUID id,
    String rua,
    Integer numero,
    String cep,
    String complemento,
    String bairro,
    String cidade,
    LocalDateTime dataCriacao,
    LocalDateTime dataUltimaAlteracao
) {}
