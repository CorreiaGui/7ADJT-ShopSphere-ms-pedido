package br.com.fiap.ms.pedidoservice.gateway.external.cliente.request;

public record EnderecoJsonRequest(
    /*
    @NotNull(message = "Rua é obrigatório")
    @NotEmpty(message = "Rua não pode ser vazio")
    */
    String rua,
    /*
    @NotNull(message = "Número é obrigatório")
    @NotEmpty(message = "Número não pode ser vazio")
    */
    Integer numero,
    /*
    @NotNull(message = "CEP é obrigatório")
    @NotEmpty(message = "CEP não pode ser vazio")
    */
    String cep,
    String complemento,
    /*
    @NotNull(message = "Bairro é obrigatório")
    @NotEmpty(message = "Bairro não pode ser vazio")
    */
    String bairro,
    /*
    @NotNull(message = "Cidade é obrigatório")
    @NotEmpty(message = "Cidade não pode ser vazio")
    */
    String cidade
) {}