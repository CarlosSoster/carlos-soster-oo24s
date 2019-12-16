
INSERT INTO usuario (ativo, cpf, datanascimento, email, foto, nome, senha) VALUES ('T', '00100200345', '1990-10-10', 'admin@admin.com', null, 'Administrador', 'MTIz');
INSERT INTO usuario (ativo, cpf, datanascimento, email, foto, nome, senha) VALUES ('T', '00600700890', '1990-11-11', 'teste@teste.com', null, 'Teste', '123');

INSERT INTO categoria (descricao) VALUES ('Alimentação');
INSERT INTO categoria (descricao) VALUES ('Lazer');

INSERT INTO produto (nome, descricao, valor, categoria_id, tipo_produto) VALUES ('Refrigerante', 'Refrigerante', 5.0, 1, 1);
INSERT INTO produto (nome, descricao, valor, categoria_id, tipo_produto) values ('Sauna', 'Sauna', 200.0, 2, 2);

INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (100, 1, 2, 4, 100);
INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (101, 1, 2, 4, 100);
INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (102, 1, 2, 4, 100);
INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (103, 1, 2, 4, 100);