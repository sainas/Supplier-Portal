SET schema 'public';

drop table if exists "factory" cascade;
drop table if exists "product" cascade;
drop table if exists "enterprise_product_volume" cascade;
drop table if exists "product_step" cascade;
drop table if exists "enterprise" cascade;
drop table if exists "equipment" cascade;
drop table if exists "employee" cascade;
drop table if exists "product_step_keyprocess" cascade;
drop table if exists "process" cascade;
drop table if exists "enterprise_keyprocess_equipment" cascade;

CREATE TABLE enterprise (
	id SERIAL PRIMARY KEY,
	name varchar NOT NULL
);

CREATE TABLE factory (
	id SERIAL PRIMARY KEY,
  enterprise_id INTEGER REFERENCES enterprise(id),
	name varchar NOT NULL
);

CREATE TABLE product (
	id SERIAL PRIMARY KEY,
	name varchar NOT NULL
);

CREATE TABLE enterprise_product_volume (
    enterprise_id INTEGER REFERENCES enterprise(id),
    product_id INTEGER REFERENCES product(id),
    year INTEGER,
    planned INTEGER,
    occupied INTEGER,
    last_update DATE,
    PRIMARY KEY (enterprise_id, product_id, year)
);

CREATE TABLE product_step (
    id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES product(id),
    step_number INTEGER,
    name varchar NOT NULL,
    UNIQUE(product_id, step_number)
);

CREATE TABLE process (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE product_step_keyprocess (
    id SERIAL PRIMARY KEY,
    product_step_id INTEGER REFERENCES product_step(id),
    keyprocess_id INTEGER REFERENCES process(id),
		is_bottleneck BOOL,
		UNIQUE(product_step_id, keyprocess_id)
);

CREATE TABLE equipment (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE enterprise_keyprocess_equipment (
    enterprise_id INTEGER REFERENCES enterprise(id),
    product_step_keyprocess_id INTEGER REFERENCES product_step_keyprocess(id),
    equipment_id INTEGER REFERENCES equipment(id),
    capacity_per_hour float,
    quantity int,
    purchase_cycle int,
    PRIMARY KEY (enterprise_id, product_step_keyprocess_id, equipment_id)
);


CREATE TABLE employee (
    enterprise_id INTEGER REFERENCES enterprise(id),
    employee_type varchar NOT NULL,
    number int,
    PRIMARY KEY (enterprise_id, employee_type)
);