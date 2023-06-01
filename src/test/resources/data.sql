--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-06-01 16:23:36 CEST

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3635 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 29790)
-- Name: booking; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.booking (
    conference_room_id integer NOT NULL,
    end_time timestamp(6) without time zone,
    id integer NOT NULL,
    start_time timestamp(6) without time zone,
    unique_id character varying(255)
);


ALTER TABLE public.booking OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 29788)
-- Name: booking_conference_room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.booking_conference_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.booking_conference_room_id_seq OWNER TO postgres;

--
-- TOC entry 3636 (class 0 OID 0)
-- Dependencies: 220
-- Name: booking_conference_room_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.booking_conference_room_id_seq OWNED BY public.booking.conference_room_id;


--
-- TOC entry 221 (class 1259 OID 29789)
-- Name: booking_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.booking_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.booking_id_seq OWNER TO postgres;

--
-- TOC entry 3637 (class 0 OID 0)
-- Dependencies: 221
-- Name: booking_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.booking_id_seq OWNED BY public.booking.id;


--
-- TOC entry 217 (class 1259 OID 29785)
-- Name: booking_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.booking_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.booking_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 29798)
-- Name: conference_room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conference_room (
    corporation_id integer,
    id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.conference_room OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 29797)
-- Name: conference_room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conference_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conference_room_id_seq OWNER TO postgres;

--
-- TOC entry 3638 (class 0 OID 0)
-- Dependencies: 223
-- Name: conference_room_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.conference_room_id_seq OWNED BY public.conference_room.id;


--
-- TOC entry 218 (class 1259 OID 29786)
-- Name: conference_room_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conference_room_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conference_room_seq OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 29805)
-- Name: corporation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.corporation (
    id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.corporation OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 28365)
-- Name: corporation_conference_room_mapping; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.corporation_conference_room_mapping (
    conference_room_id integer NOT NULL,
    corporation_id integer NOT NULL
);


ALTER TABLE public.corporation_conference_room_mapping OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 28363)
-- Name: corporation_conference_room_mapping_conference_room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.corporation_conference_room_mapping_conference_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.corporation_conference_room_mapping_conference_room_id_seq OWNER TO postgres;

--
-- TOC entry 3639 (class 0 OID 0)
-- Dependencies: 214
-- Name: corporation_conference_room_mapping_conference_room_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.corporation_conference_room_mapping_conference_room_id_seq OWNED BY public.corporation_conference_room_mapping.conference_room_id;


--
-- TOC entry 215 (class 1259 OID 28364)
-- Name: corporation_conference_room_mapping_corporation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.corporation_conference_room_mapping_corporation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.corporation_conference_room_mapping_corporation_id_seq OWNER TO postgres;

--
-- TOC entry 3640 (class 0 OID 0)
-- Dependencies: 215
-- Name: corporation_conference_room_mapping_corporation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.corporation_conference_room_mapping_corporation_id_seq OWNED BY public.corporation_conference_room_mapping.corporation_id;


--
-- TOC entry 225 (class 1259 OID 29804)
-- Name: corporation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.corporation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.corporation_id_seq OWNER TO postgres;

--
-- TOC entry 3641 (class 0 OID 0)
-- Dependencies: 225
-- Name: corporation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.corporation_id_seq OWNED BY public.corporation.id;


--
-- TOC entry 219 (class 1259 OID 29787)
-- Name: corporation_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.corporation_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.corporation_seq OWNER TO postgres;

--
-- TOC entry 3461 (class 2604 OID 29793)
-- Name: booking conference_room_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.booking ALTER COLUMN conference_room_id SET DEFAULT nextval('public.booking_conference_room_id_seq'::regclass);


--
-- TOC entry 3462 (class 2604 OID 29794)
-- Name: booking id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.booking ALTER COLUMN id SET DEFAULT nextval('public.booking_id_seq'::regclass);


--
-- TOC entry 3463 (class 2604 OID 29801)
-- Name: conference_room id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conference_room ALTER COLUMN id SET DEFAULT nextval('public.conference_room_id_seq'::regclass);


--
-- TOC entry 3464 (class 2604 OID 29808)
-- Name: corporation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.corporation ALTER COLUMN id SET DEFAULT nextval('public.corporation_id_seq'::regclass);


--
-- TOC entry 3459 (class 2604 OID 28368)
-- Name: corporation_conference_room_mapping conference_room_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.corporation_conference_room_mapping ALTER COLUMN conference_room_id SET DEFAULT nextval('public.corporation_conference_room_mapping_conference_room_id_seq'::regclass);


--
-- TOC entry 3460 (class 2604 OID 28369)
-- Name: corporation_conference_room_mapping corporation_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.corporation_conference_room_mapping ALTER COLUMN corporation_id SET DEFAULT nextval('public.corporation_conference_room_mapping_corporation_id_seq'::regclass);


--
-- TOC entry 3625 (class 0 OID 29790)
-- Dependencies: 222
-- Data for Name: booking; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.booking (conference_room_id, end_time, id, start_time, unique_id) FROM stdin;
1	2023-07-20 12:33:00	1	2023-07-20 11:33:00	IWFYaFwHyF
2	2023-07-20 12:33:00	2	2023-07-20 11:33:00	LzGYsWXeNS
3	2023-07-20 12:33:00	3	2023-07-20 11:33:00	rnwsPwjuE7
4	2023-07-20 12:33:00	4	2023-07-20 11:33:00	c5GYgDA7kU
5	2023-07-20 12:33:00	5	2023-07-20 11:33:00	4VdPhbBNtH
\.


--
-- TOC entry 3627 (class 0 OID 29798)
-- Dependencies: 224
-- Data for Name: conference_room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.conference_room (corporation_id, id, name) FROM stdin;
1	1	conference room 1
1	2	conference room 2
2	3	conference room 2
2	4	conference room 1
3	5	conference room 1
\.


--
-- TOC entry 3629 (class 0 OID 29805)
-- Dependencies: 226
-- Data for Name: corporation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.corporation (id, name) FROM stdin;
1	corporation 1
2	corporation 2
3	corporation 3
\.


--
-- TOC entry 3619 (class 0 OID 28365)
-- Dependencies: 216
-- Data for Name: corporation_conference_room_mapping; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.corporation_conference_room_mapping (conference_room_id, corporation_id) FROM stdin;
\.


--
-- TOC entry 3642 (class 0 OID 0)
-- Dependencies: 220
-- Name: booking_conference_room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.booking_conference_room_id_seq', 1, false);


--
-- TOC entry 3643 (class 0 OID 0)
-- Dependencies: 221
-- Name: booking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.booking_id_seq', 1, false);


--
-- TOC entry 3644 (class 0 OID 0)
-- Dependencies: 217
-- Name: booking_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.booking_seq', 51, true);


--
-- TOC entry 3645 (class 0 OID 0)
-- Dependencies: 223
-- Name: conference_room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conference_room_id_seq', 1, false);


--
-- TOC entry 3646 (class 0 OID 0)
-- Dependencies: 218
-- Name: conference_room_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conference_room_seq', 51, true);


--
-- TOC entry 3647 (class 0 OID 0)
-- Dependencies: 214
-- Name: corporation_conference_room_mapping_conference_room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.corporation_conference_room_mapping_conference_room_id_seq', 1, false);


--
-- TOC entry 3648 (class 0 OID 0)
-- Dependencies: 215
-- Name: corporation_conference_room_mapping_corporation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.corporation_conference_room_mapping_corporation_id_seq', 1, false);


--
-- TOC entry 3649 (class 0 OID 0)
-- Dependencies: 225
-- Name: corporation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.corporation_id_seq', 1, false);


--
-- TOC entry 3650 (class 0 OID 0)
-- Dependencies: 219
-- Name: corporation_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.corporation_seq', 51, true);


--
-- TOC entry 3468 (class 2606 OID 29796)
-- Name: booking booking_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);


--
-- TOC entry 3470 (class 2606 OID 29803)
-- Name: conference_room conference_room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conference_room
    ADD CONSTRAINT conference_room_pkey PRIMARY KEY (id);


--
-- TOC entry 3466 (class 2606 OID 28371)
-- Name: corporation_conference_room_mapping corporation_conference_room_mapping_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.corporation_conference_room_mapping
    ADD CONSTRAINT corporation_conference_room_mapping_pkey PRIMARY KEY (conference_room_id);


--
-- TOC entry 3472 (class 2606 OID 29810)
-- Name: corporation corporation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.corporation
    ADD CONSTRAINT corporation_pkey PRIMARY KEY (id);


--
-- TOC entry 3473 (class 2606 OID 29811)
-- Name: booking fkd1g14hvidwhiuudxl4a67g12w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fkd1g14hvidwhiuudxl4a67g12w FOREIGN KEY (conference_room_id) REFERENCES public.conference_room(id);


--
-- TOC entry 3474 (class 2606 OID 29816)
-- Name: conference_room fknm6prf166s1e3dmw88t70hc0k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conference_room
    ADD CONSTRAINT fknm6prf166s1e3dmw88t70hc0k FOREIGN KEY (corporation_id) REFERENCES public.corporation(id);


-- Completed on 2023-06-01 16:23:36 CEST

--
-- PostgreSQL database dump complete
--

