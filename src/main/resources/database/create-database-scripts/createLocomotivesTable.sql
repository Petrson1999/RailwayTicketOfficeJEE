-- Table: public.locomotives

-- DROP TABLE public.locomotives;

CREATE TABLE public.locomotives
(
  id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
  name text COLLATE pg_catalog."default",
  CONSTRAINT locomotives_pkey PRIMARY KEY (id)
)
  WITH (
    OIDS = FALSE
  )
  TABLESPACE pg_default;

ALTER TABLE public.locomotives
  OWNER to postgres;