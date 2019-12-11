-- Table: public.SeatsCommand

-- DROP TABLE public.SeatsCommand;

CREATE TABLE public.seats
(
    id integer NOT NULL DEFAULT nextval('seats_id_seq'::regclass),
    wagon_id integer,
    place_number integer,
    CONSTRAINT seats_pk PRIMARY KEY (id),
    CONSTRAINT seats_wagons__fk FOREIGN KEY (wagon_id)
        REFERENCES public.wagons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.seats
    OWNER to postgres;