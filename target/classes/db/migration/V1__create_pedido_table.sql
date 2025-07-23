-- Tabela de pedidos
CREATE TABLE pedido (
    id UUID PRIMARY KEY,
    numero_pedido INTEGER NOT NULL UNIQUE,
    pagamento_id UUID NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    status VARCHAR(255) NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_ultima_alteracao TIMESTAMP NULL
);

-- Tabela de itens do pedido
CREATE TABLE item_pedido (
    id UUID PRIMARY KEY,
    numero_pedido INTEGER NOT NULL,
    sku VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_ultima_alteracao TIMESTAMP NULL,

    CONSTRAINT fk_item_pedido_numero_pedido
        FOREIGN KEY (numero_pedido)
        REFERENCES pedido(numero_pedido)
        ON DELETE CASCADE
);
