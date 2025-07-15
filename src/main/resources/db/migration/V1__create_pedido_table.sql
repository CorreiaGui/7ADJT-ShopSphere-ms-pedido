CREATE TABLE pedido (
    id UUID PRIMARY KEY,
    id_cliente VARCHAR(255) NOT NULL,
    skus TEXT[] NOT NULL,
    quantidades INTEGER[] NOT NULL,
    numero_cartao VARCHAR(20) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL
);

