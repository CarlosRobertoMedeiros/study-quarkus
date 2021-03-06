CREATE TABLE TB_Localizacao (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	latitude float8 NULL,
	longitude float8 NULL,
	CONSTRAINT localizacao_pkey PRIMARY KEY (id)
);

CREATE TABLE TB_Restaurante (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	nome varchar(50) NULL,
	localizacao_id int8 NOT NULL,
	CONSTRAINT restaurante_pkey PRIMARY KEY (id)
);

ALTER TABLE tb_restaurante ADD CONSTRAINT res_loc FOREIGN KEY (localizacao_id) REFERENCES tb_localizacao(id);

CREATE TABLE TB_Prato (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	descricao varchar(200) NULL,
	nome varchar(50) NULL,
	preco numeric(19,2) NOT NULL,
	restaurante_id int8 NOT NULL
);

ALTER TABLE TB_Prato ADD CONSTRAINT prato_rest FOREIGN KEY (restaurante_id) REFERENCES TB_Restaurante(id);


CREATE TABLE TB_Prato_Cliente (
	prato int,
	cliente varchar(200)
);

INSERT INTO TB_Localizacao (id, latitude, longitude) VALUES(1000, -15.817759, -47.836959);

INSERT INTO TB_Restaurante (id, localizacao_id, nome) VALUES(999, 1000, 'Manguai');

INSERT INTO TB_Prato
(id, nome, descricao, restaurante_id, preco)
VALUES(9998, 'Cuscuz com Ovo', 'Bom demais no café da manhã', 999, 3.99);

INSERT INTO TB_Prato
(id, nome, descricao, restaurante_id, preco)
VALUES(9997, 'Peixe frito', 'Agulhinha frita, excelente com Cerveja', 999, 99.99);