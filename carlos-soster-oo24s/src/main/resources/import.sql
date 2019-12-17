
INSERT INTO usuario (ativo, cpf, datanascimento, email, foto, nome, senha) VALUES ('T', '00100200345', '1990-10-10', 'admin@admin.com', null, 'Administrador', 'MTIz');
INSERT INTO usuario (ativo, cpf, datanascimento, email, foto, nome, senha) VALUES ('T', '00600700890', '1990-11-11', 'teste@teste.com', null, 'Teste', '123');

INSERT INTO estado (nome,UF) VALUES ('São Paulo', 'SP');
INSERT INTO estado (nome,UF) VALUES ('Paraná', 'PR');
INSERT INTO estado (nome,UF) VALUES ('Santa Catarina', 'SC');

INSERT INTO cidade (nome,estado_id) VALUES ('São Paulo', 1);
INSERT INTO cidade (nome,estado_id) VALUES ('Pato Branco', 2);
INSERT INTO cidade (nome,estado_id) VALUES ('São Lourenço', 3);

INSERT INTO cliente (nome,cpf,rg,numero_passaporte, cidade_id) VALUES ('Alberto','897654231','46949494','898798798',1);
INSERT INTO cliente (nome,cpf,rg,numero_passaporte, cidade_id) VALUES ('Kauan','987879879','464919199','9898798798',2);
INSERT INTO cliente (nome,cpf,rg,numero_passaporte, cidade_id) VALUES ('Maicon','46546465','469119191','8978798798',3);
INSERT INTO cliente (nome,cpf,rg,numero_passaporte, cidade_id) VALUES ('Cleber','852852285','8465654874','315213561',2);
INSERT INTO cliente (nome,cpf,rg,numero_passaporte, cidade_id) VALUES ('Roberta','231549651','652135616','5416341631',3);

INSERT INTO categoria (descricao) VALUES ('Alimentação');
INSERT INTO categoria (descricao) VALUES ('Lazer');

INSERT INTO produto (nome, descricao, valor, categoria_id, tipo_produto) VALUES ('Refrigerante', 'Refrigerante', 5.0, 1, 1);
INSERT INTO produto (nome, descricao, valor, categoria_id, tipo_produto) VALUES ('Energético', 'Energético', 5.0, 1, 1);
INSERT INTO produto (nome, descricao, valor, categoria_id, tipo_produto) VALUES ('Chocolate', 'Chocolate', 5.0, 1, 1);
INSERT INTO produto (nome, descricao, valor, categoria_id, tipo_produto) values ('Sauna', 'Sauna', 200.0, 2, 2);
INSERT INTO produto (nome, descricao, valor, categoria_id, tipo_produto) values ('Serviço de quarto', 'Serviço de quarto', 200.0, 2, 2);

INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (100, 1, 2, 4, 100);
INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (101, 1, 2, 4, 100);
INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (102, 1, 2, 4, 100);
INSERT INTO quarto (numero, tipo_quarto, quantidade_camas, quantidade_pessoas, valor_diaria) VALUES (103, 1, 2, 4, 100);