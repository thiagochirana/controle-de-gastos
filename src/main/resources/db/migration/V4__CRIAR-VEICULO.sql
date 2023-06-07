CREATE TABLE Veiculo (
  id_veiculo SERIAL PRIMARY KEY UNIQUE,
  placa VARCHAR(10) NOT NULL,
  foto BYTEA,
  tipo_combustivel VARCHAR(30),
  quilometragem FLOAT,
  categoria_veiculo VARCHAR(60),
  modelo_id BIGINT REFERENCES Modelo(id_modelo),
  proprietario_id BIGINT REFERENCES Proprietario(id_proprietario)
);