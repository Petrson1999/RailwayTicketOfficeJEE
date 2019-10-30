-- Table: public.tickets

-- DROP TABLE public.tickets;

CREATE TABLE public.tickets
(
    id integer NOT NULL DEFAULT nextval('tickets_id_seq'::regclass),
    flight_id integer,
    user_id integer,
    cost double precision,
    seat_id integer,
    status status,
    CONSTRAINT tickets_pk PRIMARY KEY (id),
    CONSTRAINT tickets_flights__fk FOREIGN KEY (flight_id)
        REFERENCES public.flights (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tickets_seats__fk FOREIGN KEY (seat_id)
        REFERENCES public.seats (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tickets_users__fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tickets
    OWNER to postgres;