CREATE TABLE Acesso(
    login varchar(220) UNIQUE NOT NULL,
    senha varchar(220)        NOT NULL,
    proprietario_id BIGINT,
);