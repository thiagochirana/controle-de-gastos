CREATE TABLE Categoria_Gasto (
  id_categoria SERIAL PRIMARY KEY UNIQUE,
  nome VARCHAR(100) NOT NULL,
  descricao_categoria TEXT
);