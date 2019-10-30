-- Table: public.luggage

-- DROP TABLE public.luggage;

CREATE TABLE public.luggage
(
    id integer NOT NULL DEFAULT nextval('luggage_id_seq'::regclass),
    ticket_id integer,
    CONSTRAINT luggage_pk PRIMARY KEY (id),
    CONSTRAINT luggage_tickets__fk FOREIGN KEY (ticket_id)
        REFERENCES public.tickets (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.luggage
    OWNER to postgres;