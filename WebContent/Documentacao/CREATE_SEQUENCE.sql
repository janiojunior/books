-- SEQUENCE: public.livro_id_seq

-- DROP SEQUENCE public.livro_id_seq;

CREATE SEQUENCE public.livro_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.livro_id_seq
    OWNER TO topicos1;

-- SEQUENCE: public.usuario_id_seq

-- DROP SEQUENCE public.usuario_id_seq;

CREATE SEQUENCE public.usuario_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.usuario_id_seq
    OWNER TO topicos1;    

-- SEQUENCE: public.venda_id_seq

-- DROP SEQUENCE public.venda_id_seq;

CREATE SEQUENCE public.venda_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.venda_id_seq
    OWNER TO topicos1;

-- SEQUENCE: public.itemvenda_id_seq

-- DROP SEQUENCE public.itemvenda_id_seq;

CREATE SEQUENCE public.itemvenda_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.itemvenda_id_seq
    OWNER TO topicos1;    