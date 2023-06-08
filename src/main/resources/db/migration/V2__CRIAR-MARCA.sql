CREATE TABLE Marca (
  id_marca SERIAL PRIMARY KEY UNIQUE,
  nome VARCHAR(100) NOT NULL,
  logotipo_img BYTEA
);