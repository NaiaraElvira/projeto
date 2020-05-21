CREATE TABLE operador (id SERIAL PRIMARY KEY, nome varchar(100) NOT NULL,login varchar(15) NOT NULL, perfil varchar(100) NOT NULL, senha varchar(255) NOT NULL, data_cadastro date NOT NULL);
CREATE TABLE pessoa (id SERIAL PRIMARY KEY,nome varchar(100) NOT NULL,documento varchar(14) NOT NULL,data_nascimento  date NOT NULL,data_cadastro date NOT NULL,tipo_pessoa char(2) NOT NULL,nome_mae varchar(100),nome_pai varchar(100), login VARCHAR(15));
CREATE TABLE telefone (id SERIAL PRIMARY KEY,ddd INTEGER NOT NULL,numero INTEGER NOT NULL,tipo char(3) NOT NULL,data_cadastro date NOT NULL, login VARCHAR(15), id_pessoa INTEGER REFERENCES pessoa(id));

INSERT INTO operador(id, data_cadastro, login, nome, perfil, senha)values(1, '2020-02-02', 'admin', 'admin', 'ADMIN', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');
