INSERT INTO cozinha (nome) VALUES ('Cozinha A');
INSERT INTO cozinha (nome) VALUES ('Cozinha B');
INSERT INTO cozinha (nome) VALUES ('Cozinha C');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Restaurante A', 10, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Restaurante B', 20, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Restaurante C', 30, 2);

INSERT INTO forma_pagamento (descricao) VALUES ('Forma de pagamento A');
INSERT INTO forma_pagamento (descricao) VALUES ('Forma de pagamento B');
INSERT INTO forma_pagamento (descricao) VALUES ('Forma de pagamento C');

INSERT INTO estado (nome) VALUES ('Estado A');
INSERT INTO estado (nome) VALUES ('Estado B');
INSERT INTO estado (nome) VALUES ('Estado C');

INSERT INTO cidade (nome, estado_id) VALUES ('Cidade A', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Cidade B', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Cidade C', 3);

INSERT INTO permissao (nome, descricao) VALUES ('Permissao A', 'Descricao da permissao A');
INSERT INTO permissao (nome, descricao) VALUES ('Permissao B', 'Descricao da permissao B');
INSERT INTO permissao (nome, descricao) VALUES ('Permissao C', 'Descricao da permissao C');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
