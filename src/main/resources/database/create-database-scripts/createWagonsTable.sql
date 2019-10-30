CREATE TABLE public.wagons
(
    id integer NOT NULL DEFAULT nextval('wagons_id_seq'::regclass),
    train_id integer,
    type_id integer,
    name text COLLATE pg_catalog."default",
    CONSTRAINT wagons_pk PRIMARY KEY (id),
    CONSTRAINT wagons_class__fk FOREIGN KEY (type_id)
        REFERENCES public.wagon_types (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT wagons_trains__fk FOREIGN KEY (train_id)
        REFERENCES public.trains (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
