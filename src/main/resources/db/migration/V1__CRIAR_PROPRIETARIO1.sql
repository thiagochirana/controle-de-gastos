CREATE TABLE Proprietario (
  id_proprietario SERIAL PRIMARY KEY UNIQUE,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  nome VARCHAR(100) NOT NULL,
  telefone VARCHAR(40),
  email VARCHAR(100),
  cnh VARCHAR(10) UNIQUE,
  categoria_cnh VARCHAR(5)
);