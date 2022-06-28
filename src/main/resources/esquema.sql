CREATE TABLE cliente (
    id INTEGER GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR(50),
    saldo INTEGER,
    PRIMARY KEY(id)
);

CREATE TABLE transacao (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    valor INTEGER,
    padagor_id INTEGER FOREIGN KEY(),
    recebedor_id INTEGER FOREIGN KEY(),
    data_hora TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(pagador_id) REFERENCES cliente(id),
    FOREIGN KEY(recebedor_id) REFERENCES cliente(id)
);

CREATE TABLE usuario (
    id INTEGER GENERATED ALWAYS AS IDENTITY,
    nome_usuario VARCHAR(50),
    senha VARCHAR(255),
    PRIMARY KEY(id)
);