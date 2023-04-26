--
-- PostgreSQL database dump
--

-- Dumped from database version 12.14 (Ubuntu 12.14-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.14 (Ubuntu 12.14-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: employee_type_enum; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.employee_type_enum AS ENUM (
    '产品研发',
    '工艺研发',
    '制造',
    '其他'
);


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.employee (
    enterprise_id integer NOT NULL,
    employee_type public.employee_type_enum NOT NULL,
    number integer
);


--
-- Name: enterprise; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.enterprise (
    id integer NOT NULL,
    name character varying NOT NULL
);


--
-- Name: enterprise_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.enterprise_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: enterprise_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.enterprise_id_seq OWNED BY public.enterprise.id;


--
-- Name: enterprise_keyprocess_equipment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.enterprise_keyprocess_equipment (
    enterprise_id integer NOT NULL,
    product_step_keyprocess_id integer NOT NULL,
    equipment_id integer NOT NULL,
    capacity_per_hour double precision,
    quantity integer,
    purchase_cycle integer
);


--
-- Name: enterprise_product_volume; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.enterprise_product_volume (
    enterprise_id integer NOT NULL,
    product_id integer NOT NULL,
    year integer NOT NULL,
    planned integer,
    occupied integer,
    last_update date
);


--
-- Name: equipment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.equipment (
    id integer NOT NULL,
    name character varying NOT NULL
);


--
-- Name: equipment_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.equipment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: equipment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.equipment_id_seq OWNED BY public.equipment.id;


--
-- Name: factory; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.factory (
    id integer NOT NULL,
    enterprise_id integer,
    name character varying NOT NULL
);


--
-- Name: factory_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.factory_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: factory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.factory_id_seq OWNED BY public.factory.id;


--
-- Name: factory_product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.factory_product (
    factory_id integer NOT NULL,
    product_id integer NOT NULL
);


--
-- Name: process; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.process (
    id integer NOT NULL,
    name character varying NOT NULL
);


--
-- Name: process_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.process_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: process_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.process_id_seq OWNED BY public.process.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying NOT NULL
);


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: product_step; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_step (
    id integer NOT NULL,
    product_id integer,
    step_number integer,
    name character varying NOT NULL
);


--
-- Name: product_step_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_step_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_step_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_step_id_seq OWNED BY public.product_step.id;


--
-- Name: product_step_keyprocess; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_step_keyprocess (
    id integer NOT NULL,
    product_step_id integer,
    keyprocess_id integer,
    is_bottleneck boolean
);


--
-- Name: product_step_keyprocess_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_step_keyprocess_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_step_keyprocess_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_step_keyprocess_id_seq OWNED BY public.product_step_keyprocess.id;


--
-- Name: enterprise id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise ALTER COLUMN id SET DEFAULT nextval('public.enterprise_id_seq'::regclass);


--
-- Name: equipment id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.equipment ALTER COLUMN id SET DEFAULT nextval('public.equipment_id_seq'::regclass);


--
-- Name: factory id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.factory ALTER COLUMN id SET DEFAULT nextval('public.factory_id_seq'::regclass);


--
-- Name: process id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.process ALTER COLUMN id SET DEFAULT nextval('public.process_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: product_step id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step ALTER COLUMN id SET DEFAULT nextval('public.product_step_id_seq'::regclass);


--
-- Name: product_step_keyprocess id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step_keyprocess ALTER COLUMN id SET DEFAULT nextval('public.product_step_keyprocess_id_seq'::regclass);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (enterprise_id, employee_type);


--
-- Name: enterprise_keyprocess_equipment enterprise_keyprocess_equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise_keyprocess_equipment
    ADD CONSTRAINT enterprise_keyprocess_equipment_pkey PRIMARY KEY (enterprise_id, product_step_keyprocess_id, equipment_id);


--
-- Name: enterprise enterprise_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise
    ADD CONSTRAINT enterprise_pkey PRIMARY KEY (id);


--
-- Name: enterprise_product_volume enterprise_product_volume_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise_product_volume
    ADD CONSTRAINT enterprise_product_volume_pkey PRIMARY KEY (enterprise_id, product_id, year);


--
-- Name: equipment equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (id);


--
-- Name: factory factory_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.factory
    ADD CONSTRAINT factory_pkey PRIMARY KEY (id);


--
-- Name: factory_product factory_product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.factory_product
    ADD CONSTRAINT factory_product_pkey PRIMARY KEY (factory_id, product_id);


--
-- Name: process process_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.process
    ADD CONSTRAINT process_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_step_keyprocess product_step_keyprocess_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step_keyprocess
    ADD CONSTRAINT product_step_keyprocess_pkey PRIMARY KEY (id);


--
-- Name: product_step_keyprocess product_step_keyprocess_product_step_id_keyprocess_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step_keyprocess
    ADD CONSTRAINT product_step_keyprocess_product_step_id_keyprocess_id_key UNIQUE (product_step_id, keyprocess_id);


--
-- Name: product_step product_step_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step
    ADD CONSTRAINT product_step_pkey PRIMARY KEY (id);


--
-- Name: product_step product_step_product_id_step_number_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step
    ADD CONSTRAINT product_step_product_id_step_number_key UNIQUE (product_id, step_number);


--
-- Name: employee employee_enterprise_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_enterprise_id_fkey FOREIGN KEY (enterprise_id) REFERENCES public.enterprise(id);


--
-- Name: enterprise_keyprocess_equipment enterprise_keyprocess_equipment_enterprise_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise_keyprocess_equipment
    ADD CONSTRAINT enterprise_keyprocess_equipment_enterprise_id_fkey FOREIGN KEY (enterprise_id) REFERENCES public.enterprise(id);


--
-- Name: enterprise_keyprocess_equipment enterprise_keyprocess_equipment_equipment_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise_keyprocess_equipment
    ADD CONSTRAINT enterprise_keyprocess_equipment_equipment_id_fkey FOREIGN KEY (equipment_id) REFERENCES public.equipment(id);


--
-- Name: enterprise_keyprocess_equipment enterprise_keyprocess_equipment_product_step_keyprocess_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise_keyprocess_equipment
    ADD CONSTRAINT enterprise_keyprocess_equipment_product_step_keyprocess_id_fkey FOREIGN KEY (product_step_keyprocess_id) REFERENCES public.product_step_keyprocess(id);


--
-- Name: enterprise_product_volume enterprise_product_volume_enterprise_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise_product_volume
    ADD CONSTRAINT enterprise_product_volume_enterprise_id_fkey FOREIGN KEY (enterprise_id) REFERENCES public.enterprise(id);


--
-- Name: enterprise_product_volume enterprise_product_volume_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.enterprise_product_volume
    ADD CONSTRAINT enterprise_product_volume_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: factory factory_enterprise_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.factory
    ADD CONSTRAINT factory_enterprise_id_fkey FOREIGN KEY (enterprise_id) REFERENCES public.enterprise(id);


--
-- Name: factory_product factory_product_factory_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.factory_product
    ADD CONSTRAINT factory_product_factory_id_fkey FOREIGN KEY (factory_id) REFERENCES public.factory(id);


--
-- Name: factory_product factory_product_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.factory_product
    ADD CONSTRAINT factory_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product_step_keyprocess product_step_keyprocess_keyprocess_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step_keyprocess
    ADD CONSTRAINT product_step_keyprocess_keyprocess_id_fkey FOREIGN KEY (keyprocess_id) REFERENCES public.process(id);


--
-- Name: product_step_keyprocess product_step_keyprocess_product_step_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step_keyprocess
    ADD CONSTRAINT product_step_keyprocess_product_step_id_fkey FOREIGN KEY (product_step_id) REFERENCES public.product_step(id);


--
-- Name: product_step product_step_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_step
    ADD CONSTRAINT product_step_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- PostgreSQL database dump complete
--

