CREATE SEQUENCE IF NOT EXISTS peliculas_id_seq;

CREATE TABLE IF NOT EXISTS public.peliculas
(
    id bigint NOT NULL DEFAULT nextval('peliculas_id_seq'::regclass),
    archivo_video bytea,
    descripcion character varying(255),
    nombre character varying(255),
    valoracion double precision NOT NULL,
    CONSTRAINT peliculas_pkey PRIMARY KEY (id)
);