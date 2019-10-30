-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id integer NOT NULL DEFAULT nextval('"User_id_seq"'::regclass),
    login text COLLATE pg_catalog."default",
    password text COLLATE pg_catalog."default",
    role role,
    name text COLLATE pg_catalog."default",
    surname text COLLATE pg_catalog."default",
    CONSTRAINT user_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;