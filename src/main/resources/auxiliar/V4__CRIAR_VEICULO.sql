CREATE TABLE Veiculo (
  id_veiculo SERIAL PRIMARY KEY UNIQUE,
  tem_placa BOOLEAN NOT NULL DEFAULT TRUE,
  placa VARCHAR(10),
  descricao_veiculo TEXT,
  tipo_combustivel VARCHAR(30),
  quilometragem FLOAT,
  categoria_veiculo VARCHAR(60),
  ativo BOOLEAN NOT NULL DEFAULT TRUE,
  modelo_id BIGINT REFERENCES Modelo(id_modelo),
  proprietario_id BIGINT REFERENCES Proprietario(id_proprietario)
);