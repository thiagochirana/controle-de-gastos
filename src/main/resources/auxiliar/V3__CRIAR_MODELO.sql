CREATE TABLE Modelo (
  id_modelo SERIAL PRIMARY KEY UNIQUE,
  nome VARCHAR(100) NOT NULL,
  imagem BYTEA,
  marca_id BIGINT REFERENCES Marca(id_marca)
);