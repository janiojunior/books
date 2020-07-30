-- Table: public.livro

-- DROP TABLE public.livro;

CREATE TABLE public.livro
(
    id integer NOT NULL DEFAULT nextval('livro_id_seq'::regclass),
    descricao character varying(1000) COLLATE pg_catalog."default",
    isbn character varying(20) COLLATE pg_catalog."default",
    preco numeric(10,2),
    estoque integer,
    CONSTRAINT livro_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.livro
    OWNER to topicos1;

-- Table: public.usuario

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario
(
    id integer NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
    nome character varying(60) COLLATE pg_catalog."default",
    datanascimento date,
    login character varying(100) COLLATE pg_catalog."default",
    senha character varying(100) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default",
    tipousuario integer,
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to topicos1;

-- Table: public.venda

-- DROP TABLE public.venda;

CREATE TABLE public.venda
(
    id integer NOT NULL DEFAULT nextval('venda_id_seq'::regclass),
    data date,
    idusuario integer,
    CONSTRAINT venda_pkey PRIMARY KEY (id),
    CONSTRAINT venda_usuario FOREIGN KEY (idusuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.venda
    OWNER to topicos1;

-- Table: public.itemvenda

-- DROP TABLE public.itemvenda;

CREATE TABLE public.itemvenda
(
    id integer NOT NULL DEFAULT nextval('itemvenda_id_seq'::regclass),
    idlivro integer,
    valor numeric(10,2),
    idvenda integer,
    CONSTRAINT itemvenda_pkey PRIMARY KEY (id),
    CONSTRAINT itemvenda_livro_fk FOREIGN KEY (idlivro)
        REFERENCES public.livro (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT itemvenda_venda_fk FOREIGN KEY (idvenda)
        REFERENCES public.venda (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.itemvenda
    OWNER to topicos1;