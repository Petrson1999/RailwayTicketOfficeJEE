-- Table: public.wagon_types

-- DROP TABLE public.wagon_types;

CREATE TABLE public.wagon_types
(
    id integer NOT NULL DEFAULT nextval('wagon_types_id_seq'::regclass),
    number_of_seats integer,
    comfort integer,
    name text COLLATE pg_catalog."default",
    CONSTRAINT class_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.wagon_types
    OWNER to postgres;