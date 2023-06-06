CREATE TABLE Veiculo (
  id_veiculo BIGINT PRIMARY KEY,
  placa VARCHAR(10) NOT NULL,
  foto BYTEA,
  tipo_combustivel VARCHAR(30) NOT NULL,
  quilometragem FLOAT NOT NULL,
  categoria_veiculo VARCHAR(60) NOT NULL,
  modelo_id BIGINT REFERENCES Modelo(id_modelo),
  proprietario_id BIGINT REFERENCES Proprietario(id_proprietario)
);