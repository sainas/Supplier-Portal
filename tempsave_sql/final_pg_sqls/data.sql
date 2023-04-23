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
SELECT pg_catalog.set_config('search_path', 'public', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: enterprise; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.enterprise VALUES (1, 'Enterprise B');
INSERT INTO public.enterprise VALUES (2, 'Enterprise A');


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: equipment; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.equipment VALUES (1, '剥线设备A');
INSERT INTO public.equipment VALUES (2, '剥线设备B');
INSERT INTO public.equipment VALUES (3, '引导板设备C');
INSERT INTO public.equipment VALUES (4, '引导板设备D');
INSERT INTO public.equipment VALUES (5, '回流焊设备H');
INSERT INTO public.equipment VALUES (6, '回流焊设备M');
INSERT INTO public.equipment VALUES (7, '回流焊设备N');
INSERT INTO public.equipment VALUES (8, '随机设备Z');


--
-- Data for Name: process; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.process VALUES (1, '剥线');
INSERT INTO public.process VALUES (2, '端子压接');
INSERT INTO public.process VALUES (3, '贴片');
INSERT INTO public.process VALUES (4, '回流焊');
INSERT INTO public.process VALUES (5, '老化测试');
INSERT INTO public.process VALUES (6, '引导板');


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product VALUES (1, '充电桩');


--
-- Data for Name: product_step; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product_step VALUES (1, 1, 1, '线束装配');
INSERT INTO public.product_step VALUES (2, 1, 2, 'PCBA');
INSERT INTO public.product_step VALUES (3, 1, 3, '总成装配');


--
-- Data for Name: product_step_keyprocess; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product_step_keyprocess VALUES (1, 1, 1, true);
INSERT INTO public.product_step_keyprocess VALUES (2, 1, 2, false);
INSERT INTO public.product_step_keyprocess VALUES (3, 2, 3, false);
INSERT INTO public.product_step_keyprocess VALUES (4, 2, 4, true);
INSERT INTO public.product_step_keyprocess VALUES (5, 3, 5, false);
INSERT INTO public.product_step_keyprocess VALUES (6, 3, 6, true);


--
-- Data for Name: enterprise_keyprocess_equipment; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.enterprise_keyprocess_equipment VALUES (1, 1, 1, 10, 5, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (1, 4, 3, 2, 3, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (1, 2, 8, 1000, 1000, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (2, 3, 8, 1000, 1000, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (2, 1, 1, 19, 6, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (2, 1, 2, 12, 1, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (1, 6, 5, 3, 4, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (1, 6, 6, 5, 4, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (2, 4, 4, 4, 4, 1);
INSERT INTO public.enterprise_keyprocess_equipment VALUES (2, 6, 7, 7, 10, 1);


--
-- Data for Name: enterprise_product_volume; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: factory; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.factory VALUES (1, 1, 'Test Factory 1');


--
-- Data for Name: factory_product; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: enterprise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.enterprise_id_seq', 2, true);


--
-- Name: equipment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.equipment_id_seq', 8, true);


--
-- Name: factory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.factory_id_seq', 1, true);


--
-- Name: process_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.process_id_seq', 6, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_id_seq', 1, true);


--
-- Name: product_step_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_step_id_seq', 3, true);


--
-- Name: product_step_keyprocess_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_step_keyprocess_id_seq', 6, true);


--
-- PostgreSQL database dump complete
--

