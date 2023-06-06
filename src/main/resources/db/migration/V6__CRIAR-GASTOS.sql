CREATE TABLE Gastos (
  id_gastos BIGINT PRIMARY KEY,
  descricao VARCHAR(240) NOT NULL,
  data DATE NOT NULL,
  valor NUMERIC(10, 2) NOT NULL,
  veiculo_id BIGINT REFERENCES Veiculo(id_veiculo),
  categoria_id BIGINT REFERENCES Categoria_Gasto(id_categoria)
);