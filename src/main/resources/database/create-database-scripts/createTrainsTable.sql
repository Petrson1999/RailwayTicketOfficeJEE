-- Table: public.trains

-- DROP TABLE public.trains;

CREATE TABLE public.trains
(
    id integer NOT NULL DEFAULT nextval('trains_id_seq'::regclass),
    name text COLLATE pg_catalog."default",
    locomotive_id integer,
    CONSTRAINT trains_pkey PRIMARY KEY (id),
    CONSTRAINT trains_locomotives__fk FOREIGN KEY (locomotive_id)
        REFERENCES public.locomotives (id) MATCH SIMPLE
        ON UPDATE SET NULL
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.trains
    OWNER to postgres;