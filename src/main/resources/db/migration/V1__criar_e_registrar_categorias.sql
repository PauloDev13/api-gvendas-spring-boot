CREATE TABLE IF NOT EXISTS categoria (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO categoria (nome) VALUES ('Tecnologia');
INSERT INTO categoria (nome) VALUES ('Acessórios para veículos');
INSERT INTO categoria (nome) VALUES ('Esporte e Lazer');
INSERT INTO categoria (nome) VALUES ('Casa e Eletrodomésticos');
INSERT INTO categoria (nome) VALUES ('Jóias e Relógios');