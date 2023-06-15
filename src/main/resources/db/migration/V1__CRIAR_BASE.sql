CREATE TABLE Proprietario (
                              id_proprietario SERIAL PRIMARY KEY UNIQUE,
                              cpf VARCHAR(11) NOT NULL UNIQUE,
                              nome VARCHAR(100) NOT NULL,
                              telefone VARCHAR(40),
                              email VARCHAR(100),
                              cnh VARCHAR(10) UNIQUE,
                              categoria_cnh VARCHAR(5)
);
CREATE TABLE Marca (
                       id_marca SERIAL PRIMARY KEY UNIQUE,
                       nome VARCHAR(100) UNIQUE NOT NULL,
                       logotipo_img BYTEA
);
CREATE TABLE Modelo (
                        id_modelo SERIAL PRIMARY KEY UNIQUE,
                        nome VARCHAR(100) NOT NULL,
                        imagem BYTEA,
                        marca_id BIGINT REFERENCES Marca(id_marca)
);
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
CREATE TABLE Categoria_Gasto (
                                 id_categoria SERIAL PRIMARY KEY UNIQUE,
                                 nome VARCHAR(100) NOT NULL
);
CREATE TABLE Gastos (
                        id_gastos SERIAL PRIMARY KEY UNIQUE,
                        descricao VARCHAR(240) NOT NULL,
                        data DATE NOT NULL,
                        valor NUMERIC(10, 2) NOT NULL,
                        veiculo_id BIGINT REFERENCES Veiculo(id_veiculo),
                        categoria_id BIGINT REFERENCES Categoria_Gasto(id_categoria)
);
CREATE TABLE Acesso(
                       login varchar(220) UNIQUE NOT NULL,
                       senha varchar(220)        NOT NULL,
                       proprietario_id BIGINT
);