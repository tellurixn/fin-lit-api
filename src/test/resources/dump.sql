--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-07-09 21:43:18

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4970 (class 1262 OID 5)
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4971 (class 0 OID 0)
-- Dependencies: 4970
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 7 (class 2615 OID 16423)
-- Name: home; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA home;


ALTER SCHEMA home OWNER TO postgres;

--
-- TOC entry 6 (class 2615 OID 16388)
-- Name: wishes; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA wishes;


ALTER SCHEMA wishes OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 16425)
-- Name: ad; Type: TABLE; Schema: home; Owner: postgres
--

CREATE TABLE home.ad (
    id_ad integer NOT NULL,
    price bigint,
    date_created timestamp with time zone DEFAULT now() NOT NULL,
    date_updated timestamp with time zone DEFAULT now() NOT NULL,
    floor smallint NOT NULL,
    area integer NOT NULL,
    rooms_number smallint NOT NULL,
    comment character varying(1000),
    address character varying(255),
    id_seller bigint NOT NULL
);


ALTER TABLE home.ad OWNER TO postgres;

--
-- TOC entry 4972 (class 0 OID 0)
-- Dependencies: 224
-- Name: TABLE ad; Type: COMMENT; Schema: home; Owner: postgres
--

COMMENT ON TABLE home.ad IS 'Объявление';


--
-- TOC entry 4973 (class 0 OID 0)
-- Dependencies: 224
-- Name: COLUMN ad.price; Type: COMMENT; Schema: home; Owner: postgres
--

COMMENT ON COLUMN home.ad.price IS 'Цена в минорных единицах';


--
-- TOC entry 223 (class 1259 OID 16424)
-- Name: ad_id_ad_seq; Type: SEQUENCE; Schema: home; Owner: postgres
--

CREATE SEQUENCE home.ad_id_ad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE home.ad_id_ad_seq OWNER TO postgres;

--
-- TOC entry 4974 (class 0 OID 0)
-- Dependencies: 223
-- Name: ad_id_ad_seq; Type: SEQUENCE OWNED BY; Schema: home; Owner: postgres
--

ALTER SEQUENCE home.ad_id_ad_seq OWNED BY home.ad.id_ad;


--
-- TOC entry 226 (class 1259 OID 16436)
-- Name: seller; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seller (
    id_seller bigint NOT NULL,
    phone character varying(11) NOT NULL
);


ALTER TABLE public.seller OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16435)
-- Name: seller_id_seller_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seller_id_seller_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seller_id_seller_seq OWNER TO postgres;

--
-- TOC entry 4975 (class 0 OID 0)
-- Dependencies: 225
-- Name: seller_id_seller_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.seller_id_seller_seq OWNED BY public.seller.id_seller;


--
-- TOC entry 232 (class 1259 OID 24651)
-- Name: subscription_id_subscription_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subscription_id_subscription_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.subscription_id_subscription_seq OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16472)
-- Name: subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription (
    id_subscription integer DEFAULT nextval('public.subscription_id_subscription_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    cost bigint DEFAULT 0 NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.subscription OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16452)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    password character varying(100) NOT NULL,
    login character varying(100) NOT NULL,
    comment character varying(100),
    id_user integer NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16460)
-- Name: user_monthly_budget; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_monthly_budget (
    id_user_budget integer NOT NULL,
    monthly_income bigint DEFAULT 0 NOT NULL,
    apartment_rent bigint,
    utility_fees bigint,
    internet bigint,
    health bigint,
    id_user integer NOT NULL
);


ALTER TABLE public.user_monthly_budget OWNER TO postgres;

--
-- TOC entry 4976 (class 0 OID 0)
-- Dependencies: 229
-- Name: COLUMN user_monthly_budget.utility_fees; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.user_monthly_budget.utility_fees IS 'Коммунальные платежи';


--
-- TOC entry 228 (class 1259 OID 16459)
-- Name: user_budget_id_user_budget_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_budget_id_user_budget_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_budget_id_user_budget_seq OWNER TO postgres;

--
-- TOC entry 4977 (class 0 OID 0)
-- Dependencies: 228
-- Name: user_budget_id_user_budget_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_budget_id_user_budget_seq OWNED BY public.user_monthly_budget.id_user_budget;


--
-- TOC entry 231 (class 1259 OID 16489)
-- Name: user_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_id_user_seq OWNER TO postgres;

--
-- TOC entry 4978 (class 0 OID 0)
-- Dependencies: 231
-- Name: user_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_user_seq OWNED BY public."user".id_user;


--
-- TOC entry 221 (class 1259 OID 16407)
-- Name: chat; Type: TABLE; Schema: wishes; Owner: postgres
--

CREATE TABLE wishes.chat (
    id_chat bigint NOT NULL,
    id_chat_telegram bigint NOT NULL,
    date_last_sent timestamp with time zone DEFAULT now(),
    date_registered timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE wishes.chat OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16389)
-- Name: chat_id_chat_seq; Type: SEQUENCE; Schema: wishes; Owner: postgres
--

CREATE SEQUENCE wishes.chat_id_chat_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE wishes.chat_id_chat_seq OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16390)
-- Name: wish_id_wish_seq; Type: SEQUENCE; Schema: wishes; Owner: postgres
--

CREATE SEQUENCE wishes.wish_id_wish_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE wishes.wish_id_wish_seq OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16414)
-- Name: wish; Type: TABLE; Schema: wishes; Owner: postgres
--

CREATE TABLE wishes.wish (
    id_wish integer DEFAULT nextval('wishes.wish_id_wish_seq'::regclass) NOT NULL,
    text character varying(500) NOT NULL,
    used boolean DEFAULT false
);


ALTER TABLE wishes.wish OWNER TO postgres;

--
-- TOC entry 4778 (class 2604 OID 16428)
-- Name: ad id_ad; Type: DEFAULT; Schema: home; Owner: postgres
--

ALTER TABLE ONLY home.ad ALTER COLUMN id_ad SET DEFAULT nextval('home.ad_id_ad_seq'::regclass);


--
-- TOC entry 4781 (class 2604 OID 16439)
-- Name: seller id_seller; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seller ALTER COLUMN id_seller SET DEFAULT nextval('public.seller_id_seller_seq'::regclass);


--
-- TOC entry 4782 (class 2604 OID 16490)
-- Name: user id_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id_user SET DEFAULT nextval('public.user_id_user_seq'::regclass);


--
-- TOC entry 4783 (class 2604 OID 16463)
-- Name: user_monthly_budget id_user_budget; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_monthly_budget ALTER COLUMN id_user_budget SET DEFAULT nextval('public.user_budget_id_user_budget_seq'::regclass);


--
-- TOC entry 4956 (class 0 OID 16425)
-- Dependencies: 224
-- Data for Name: ad; Type: TABLE DATA; Schema: home; Owner: postgres
--



--
-- TOC entry 4958 (class 0 OID 16436)
-- Dependencies: 226
-- Data for Name: seller; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4962 (class 0 OID 16472)
-- Dependencies: 230
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.subscription VALUES (1, 'Яндекс.Плюс', 150000, 80);
INSERT INTO public.subscription VALUES (3, 'UNIT_TEST_SUBSCRIPTION', 15000, 82);
INSERT INTO public.subscription VALUES (5, 'UNIT_TEST_SUBSCRIPTION', 15000, 84);
INSERT INTO public.subscription VALUES (7, 'UNIT_TEST_SUBSCRIPTION', 15000, 86);
INSERT INTO public.subscription VALUES (9, 'UNIT_TEST_SUBSCRIPTION', 15000, 88);
INSERT INTO public.subscription VALUES (11, 'UNIT_TEST_SUBSCRIPTION', 15000, 90);


--
-- TOC entry 4959 (class 0 OID 16452)
-- Dependencies: 227
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" VALUES ('password', 'admin', NULL, 1);
INSERT INTO public."user" VALUES ('12344', 'user', NULL, 78);
INSERT INTO public."user" VALUES ('$2a$10$e6zMQbcVnJQEbJO/f2eDw.wYq2iXUL5hJEHEBSLtJh8wJ9ljbicYS', 'test1', NULL, 79);
INSERT INTO public."user" VALUES ('$2a$10$QHQxTGkcZASqe/1gwVK0Ge5Ec32OJYw.eHXXHdGsYhRICFSo7dING', 'testik', NULL, 80);
INSERT INTO public."user" VALUES ('UNIT_TEST_PASSWORD', 'UNIT_TEST_USER1751902784095', NULL, 82);
INSERT INTO public."user" VALUES ('UNIT_TEST_PASSWORD', 'UNIT_TEST_USER1751902871323', NULL, 84);
INSERT INTO public."user" VALUES ('UNIT_TEST_PASSWORD', 'UNIT_TEST_USER1751903010373', NULL, 86);
INSERT INTO public."user" VALUES ('UNIT_TEST_PASSWORD', 'UNIT_TEST_USER1751903090227', NULL, 88);
INSERT INTO public."user" VALUES ('UNIT_TEST_PASSWORD', 'UNIT_TEST_USER1751903171747', NULL, 90);
INSERT INTO public."user" VALUES ('UNIT_TEST_PASSWORD', 'UNIT_TEST_USER1751903287007', NULL, 93);


--
-- TOC entry 4961 (class 0 OID 16460)
-- Dependencies: 229
-- Data for Name: user_monthly_budget; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_monthly_budget VALUES (1, 500000, 500000, 520000, 500030, 500000, 1);


--
-- TOC entry 4953 (class 0 OID 16407)
-- Dependencies: 221
-- Data for Name: chat; Type: TABLE DATA; Schema: wishes; Owner: postgres
--

INSERT INTO wishes.chat VALUES (1, 560230353, NULL, '2025-06-05 23:03:04.935+07');


--
-- TOC entry 4954 (class 0 OID 16414)
-- Dependencies: 222
-- Data for Name: wish; Type: TABLE DATA; Schema: wishes; Owner: postgres
--



--
-- TOC entry 4979 (class 0 OID 0)
-- Dependencies: 223
-- Name: ad_id_ad_seq; Type: SEQUENCE SET; Schema: home; Owner: postgres
--

SELECT pg_catalog.setval('home.ad_id_ad_seq', 1, false);


--
-- TOC entry 4980 (class 0 OID 0)
-- Dependencies: 225
-- Name: seller_id_seller_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seller_id_seller_seq', 1, false);


--
-- TOC entry 4981 (class 0 OID 0)
-- Dependencies: 232
-- Name: subscription_id_subscription_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_id_subscription_seq', 20, true);


--
-- TOC entry 4982 (class 0 OID 0)
-- Dependencies: 228
-- Name: user_budget_id_user_budget_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_budget_id_user_budget_seq', 13, true);


--
-- TOC entry 4983 (class 0 OID 0)
-- Dependencies: 231
-- Name: user_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_user_seq', 103, true);


--
-- TOC entry 4984 (class 0 OID 0)
-- Dependencies: 219
-- Name: chat_id_chat_seq; Type: SEQUENCE SET; Schema: wishes; Owner: postgres
--

SELECT pg_catalog.setval('wishes.chat_id_chat_seq', 1, true);


--
-- TOC entry 4985 (class 0 OID 0)
-- Dependencies: 220
-- Name: wish_id_wish_seq; Type: SEQUENCE SET; Schema: wishes; Owner: postgres
--

SELECT pg_catalog.setval('wishes.wish_id_wish_seq', 1, false);


--
-- TOC entry 4792 (class 2606 OID 16430)
-- Name: ad ad_pk; Type: CONSTRAINT; Schema: home; Owner: postgres
--

ALTER TABLE ONLY home.ad
    ADD CONSTRAINT ad_pk PRIMARY KEY (id_ad);


--
-- TOC entry 4794 (class 2606 OID 16441)
-- Name: seller seller_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT seller_pk PRIMARY KEY (id_seller);


--
-- TOC entry 4802 (class 2606 OID 16479)
-- Name: subscription subscription_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pk PRIMARY KEY (id_subscription);


--
-- TOC entry 4800 (class 2606 OID 16466)
-- Name: user_monthly_budget user_budget_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_monthly_budget
    ADD CONSTRAINT user_budget_pk PRIMARY KEY (id_user_budget);


--
-- TOC entry 4796 (class 2606 OID 16496)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id_user);


--
-- TOC entry 4798 (class 2606 OID 16458)
-- Name: user user_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_unique UNIQUE (login);


--
-- TOC entry 4788 (class 2606 OID 16413)
-- Name: chat pk_chat; Type: CONSTRAINT; Schema: wishes; Owner: postgres
--

ALTER TABLE ONLY wishes.chat
    ADD CONSTRAINT pk_chat PRIMARY KEY (id_chat);


--
-- TOC entry 4790 (class 2606 OID 16422)
-- Name: wish pk_wish; Type: CONSTRAINT; Schema: wishes; Owner: postgres
--

ALTER TABLE ONLY wishes.wish
    ADD CONSTRAINT pk_wish PRIMARY KEY (id_wish);


--
-- TOC entry 4803 (class 2606 OID 16447)
-- Name: ad ad_seller_fk; Type: FK CONSTRAINT; Schema: home; Owner: postgres
--

ALTER TABLE ONLY home.ad
    ADD CONSTRAINT ad_seller_fk FOREIGN KEY (id_seller) REFERENCES public.seller(id_seller) ON UPDATE RESTRICT;


--
-- TOC entry 4805 (class 2606 OID 16502)
-- Name: subscription subscription_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_user_fk FOREIGN KEY (id_user) REFERENCES public."user"(id_user);


--
-- TOC entry 4804 (class 2606 OID 16497)
-- Name: user_monthly_budget user_monthly_budget_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_monthly_budget
    ADD CONSTRAINT user_monthly_budget_user_fk FOREIGN KEY (id_user) REFERENCES public."user"(id_user);


-- Completed on 2025-07-09 21:43:18

--
-- PostgreSQL database dump complete
--

