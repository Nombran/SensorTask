--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)

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
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: nombran
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO nombran;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: sensor_types; Type: TABLE; Schema: public; Owner: nombran
--

CREATE TABLE public.sensor_types (
    type character varying(255) NOT NULL
);


ALTER TABLE public.sensor_types OWNER TO nombran;

--
-- Name: sensors; Type: TABLE; Schema: public; Owner: nombran
--

CREATE TABLE public.sensors (
    id bigint NOT NULL,
    description character varying(255),
    location character varying(255),
    model character varying(255),
    name character varying(255),
    range_from integer,
    range_to integer,
    sensor_type character varying(255) NOT NULL,
    unit character varying(255) NOT NULL
);


ALTER TABLE public.sensors OWNER TO nombran;

--
-- Name: units; Type: TABLE; Schema: public; Owner: nombran
--

CREATE TABLE public.units (
    unit_name character varying(255) NOT NULL
);


ALTER TABLE public.units OWNER TO nombran;

--
-- Name: users; Type: TABLE; Schema: public; Owner: nombran
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO nombran;

--
-- Data for Name: sensor_types; Type: TABLE DATA; Schema: public; Owner: nombran
--

COPY public.sensor_types (type) FROM stdin;
Voltage
Pressure
Temperature
Humidity
\.


--
-- Data for Name: sensors; Type: TABLE DATA; Schema: public; Owner: nombran
--

COPY public.sensors (id, description, location, model, name, range_from, range_to, sensor_type, unit) FROM stdin;
5	some short description for sensor 2	location for sensor 2	PC33-56	sensor 8	66	21	Temperature	%
10	some short description for sensor 7	location for sensor 7	AFS4	sensor 19	25	23	Temperature	bar
3	some short model description	sensor location with updates	YHF-228	model name	25	12	Temperature	℃
7	some short description for sensor 4	location for sensor 4	AV-452	sensor 4	122	11	Humidity	voltage
8	some short description for sensor 5	location for sensor 5	D45-34	sensor 5	44	1	Pressure	%
6	some short description for sensor 3	location for sensor 3	H914-42	sensor 3	24	-45	Humidity	bar
9	some short description for sensor 6	location for sensor 6	GH44	sensor 6	99	-30	Temperature	%
\.


--
-- Data for Name: units; Type: TABLE DATA; Schema: public; Owner: nombran
--

COPY public.units (unit_name) FROM stdin;
bar
voltage
℃
%
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: nombran
--

COPY public.users (id, login, password, role) FROM stdin;
1	login	$2y$04$C4rymSxnWQ23G7B1Me6aX.XSMvswyxSuI/UgKyLEyQUlP8EmKfgjK	ROLE_USER
2	admin	$2y$04$C4rymSxnWQ23G7B1Me6aX.XSMvswyxSuI/UgKyLEyQUlP8EmKfgjK	ROLE_ADMIN
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: nombran
--

SELECT pg_catalog.setval('public.hibernate_sequence', 11, true);


--
-- Name: sensor_types sensor_types_pkey; Type: CONSTRAINT; Schema: public; Owner: nombran
--

ALTER TABLE ONLY public.sensor_types
    ADD CONSTRAINT sensor_types_pkey PRIMARY KEY (type);


--
-- Name: sensors sensors_pkey; Type: CONSTRAINT; Schema: public; Owner: nombran
--

ALTER TABLE ONLY public.sensors
    ADD CONSTRAINT sensors_pkey PRIMARY KEY (id);


--
-- Name: users uk_ow0gan20590jrb00upg3va2fn; Type: CONSTRAINT; Schema: public; Owner: nombran
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login);


--
-- Name: units units_pkey; Type: CONSTRAINT; Schema: public; Owner: nombran
--

ALTER TABLE ONLY public.units
    ADD CONSTRAINT units_pkey PRIMARY KEY (unit_name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: nombran
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: sensors fk684duacohyn7u2i39qllnb31g; Type: FK CONSTRAINT; Schema: public; Owner: nombran
--

ALTER TABLE ONLY public.sensors
    ADD CONSTRAINT fk684duacohyn7u2i39qllnb31g FOREIGN KEY (sensor_type) REFERENCES public.sensor_types(type);


--
-- Name: sensors fkbyxe8la5kjgxw4y251fibyxli; Type: FK CONSTRAINT; Schema: public; Owner: nombran
--

ALTER TABLE ONLY public.sensors
    ADD CONSTRAINT fkbyxe8la5kjgxw4y251fibyxli FOREIGN KEY (unit) REFERENCES public.units(unit_name);


--
-- PostgreSQL database dump complete
--

