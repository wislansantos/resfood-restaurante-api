INSERT INTO cozinha (nome) VALUES ('Cozinha A');
INSERT INTO cozinha (nome) VALUES ('Cozinha B');
INSERT INTO cozinha (nome) VALUES ('Cozinha C');

INSERT INTO estado (nome) VALUES ('Estado A');
INSERT INTO estado (nome) VALUES ('Estado B');
INSERT INTO estado (nome) VALUES ('Estado C');

INSERT INTO cidade (nome, estado_id) VALUES ('Cidade A', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Cidade B', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Cidade C', 3);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES ('Restaurante A', 10, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES ('Restaurante B', 20, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES ('Restaurante C', 30, 2, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES ('Restaurante D', 40, 2, utc_timestamp, utc_timestamp, 'exemplo de cep', 'exemplo de logradouro', 'exemplo de numero', 'exemplo de complemento', 'exemplo de bairro', 2);

INSERT INTO forma_pagamento (descricao) VALUES ('Forma de pagamento A');
INSERT INTO forma_pagamento (descricao) VALUES ('Forma de pagamento B');
INSERT INTO forma_pagamento (descricao) VALUES ('Forma de pagamento C');

INSERT INTO permissao (nome, descricao) VALUES ('Permissao A', 'Descricao da permissao A');
INSERT INTO permissao (nome, descricao) VALUES ('Permissao B', 'Descricao da permissao B');
INSERT INTO permissao (nome, descricao) VALUES ('Permissao C', 'Descricao da permissao C');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Produto A', 'Descricao do produto A', 10.90, 1, 2);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Produto B', 'Descricao do produto B', 20.90, 1, 3);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Produto C', 'Descricao do produto C', 30.90, 1, 1);