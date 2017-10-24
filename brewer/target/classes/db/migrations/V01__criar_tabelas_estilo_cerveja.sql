DROP TABLE IF EXISTS "public"."estilo";
CREATE TABLE "public"."estilo" (
"codigo" int4 DEFAULT nextval('estilo_codigo_seq'::regclass) NOT NULL,
"nome" varchar(50) COLLATE "default" DEFAULT NULL::character varying NOT NULL
)
WITH (OIDS=FALSE);

ALTER TABLE "public"."estilo" ADD PRIMARY KEY ("codigo");

DROP TABLE IF EXISTS "public"."cerveja";
CREATE TABLE "public"."cerveja" (
"codigo" int4 DEFAULT nextval('cerveja_codigo_seq'::regclass) NOT NULL,
"sku" varchar(50) COLLATE "default" NOT NULL,
"nome" varchar(80) COLLATE "default" NOT NULL,
"descricao" text COLLATE "default" NOT NULL,
"valor" numeric(10,2) NOT NULL,
"teor_alcoolico" numeric(10,2) NOT NULL,
"comissao" numeric(10,2) NOT NULL,
"sabor" varchar(50) COLLATE "default" NOT NULL,
"origem" varchar(50) COLLATE "default" NOT NULL,
"codigo_estilo" int4 DEFAULT nextval('cerveja_codigo_estilo_seq'::regclass) NOT NULL
)
WITH (OIDS=FALSE);

ALTER TABLE "public"."cerveja" ADD PRIMARY KEY ("codigo");

ALTER TABLE "public"."cerveja" ADD FOREIGN KEY ("codigo_estilo") REFERENCES "public"."estilo" ("codigo") ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO estilo VALUES (0, 'Amber Lager');
INSERT INTO estilo VALUES (0, 'Dark Lager');
INSERT INTO estilo VALUES (0, 'Pale Lager');
INSERT INTO estilo VALUES (0, 'Pilsner');