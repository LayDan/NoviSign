CREATE SEQUENCE IF NOT EXISTS public.image_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.image
(
    id bigint NOT NULL DEFAULT nextval('image_id_seq'::regclass),
    addition_date timestamp(6) without time zone NOT NULL,
    duration double precision NOT NULL,
    url character varying(255) NOT NULL,
    CONSTRAINT image_pkey PRIMARY KEY (id),
    CONSTRAINT unique_url_image UNIQUE (url)
);

CREATE SEQUENCE IF NOT EXISTS public.slideshow_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.slideshow
(
    id bigint NOT NULL DEFAULT nextval('slideshow_id_seq'::regclass),
    creation_date timestamp(6) without time zone NOT NULL,
    CONSTRAINT slideshow_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.slideshow_image_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.slideshow_image
(
    id bigint NOT NULL DEFAULT nextval('slideshow_image_id_seq'::regclass),
    duration double precision NOT NULL,
    order_index integer NOT NULL,
    image_id bigint NOT NULL,
    slideshow_id bigint NOT NULL,
    CONSTRAINT slideshow_image_pkey PRIMARY KEY (id),
    CONSTRAINT slideshow_id_pkey FOREIGN KEY (slideshow_id)
        REFERENCES public.slideshow (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT image_id_pkey FOREIGN KEY (image_id)
        REFERENCES public.image (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);