-- public.restaurante definition

-- Drop table

-- DROP TABLE restaurante;

CREATE TABLE tb_restaurante (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	cnpj varchar(255) NULL,
	dataatualizacao timestamp NULL,
	datacriacao timestamp NULL,
	nome varchar(255) NULL,
	proprietario varchar(255) NULL,
	localizacao_id int8 NULL,
	CONSTRAINT restaurante_pkey PRIMARY KEY (id)
);


-- public.restaurante foreign keys

ALTER TABLE tb_restaurante ADD CONSTRAINT fkdfbggt9ievc4ev74wl3tdnscl FOREIGN KEY (localizacao_id) REFERENCES tb_localizacao(id);

-- public.prato definition

-- Drop table

-- DROP TABLE public.tb_prato;

CREATE TABLE tb_prato (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	descricao varchar(255) NULL,
	nome varchar(255) NULL,
	preco numeric(19,2) NULL,
	restaurante_id int8 NULL,
	CONSTRAINT prato_pkey PRIMARY KEY (id)
);


-- public.prato foreign keys

ALTER TABLE tb_prato ADD CONSTRAINT fk43yo4ddilkvn6ebgfcx2vnqs7 FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante(id);