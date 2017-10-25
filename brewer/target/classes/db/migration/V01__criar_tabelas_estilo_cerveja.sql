DROP SEQUENCE IF EXISTS "public"."cerveja_codigo_estilo_seq";
CREATE SEQUENCE "public"."cerveja_codigo_estilo_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;


DROP SEQUENCE IF EXISTS "public"."cerveja_codigo_seq";
CREATE SEQUENCE "public"."cerveja_codigo_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;


DROP SEQUENCE IF EXISTS "public"."estilo_codigo_seq";
CREATE SEQUENCE "public"."estilo_codigo_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;


DROP TABLE IF EXISTS "public"."cerveja";
CREATE TABLE "public"."cerveja" (
"codigo" int4 DEFAULT nextval('cerveja_codigo_seq'::regclass) NOT NULL,
"sku" varchar(50) COLLATE "default" NOT NULL,
"nome" varchar(50) COLLATE "default" NOT NULL,
"descricao" varchar(50) COLLATE "default" NOT NULL,
"valor" numeric(10,2) NOT NULL,
"teor_alcoolico" numeric(10,2) NOT NULL,
"comissao" numeric(10,2) NOT NULL,
"sabor" varchar(50) COLLATE "default" NOT NULL,
"origem" varchar(50) COLLATE "default" NOT NULL,
"codigo_estilo" int4 DEFAULT nextval('cerveja_codigo_estilo_seq'::regclass) NOT NULL
)
WITH (OIDS=FALSE)

;


DROP TABLE IF EXISTS "public"."estilo";
CREATE TABLE "public"."estilo" (
"codigo" int4 DEFAULT nextval('estilo_codigo_seq'::regclass) NOT NULL,
"nome" varchar(50) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;


ALTER SEQUENCE "public"."cerveja_codigo_estilo_seq" OWNED BY "cerveja"."codigo_estilo";
ALTER SEQUENCE "public"."cerveja_codigo_seq" OWNED BY "cerveja"."codigo";
ALTER SEQUENCE "public"."estilo_codigo_seq" OWNED BY "estilo"."codigo";


ALTER TABLE "public"."cerveja" ADD PRIMARY KEY ("codigo");


ALTER TABLE "public"."estilo" ADD PRIMARY KEY ("codigo");


ALTER TABLE "public"."cerveja" ADD FOREIGN KEY ("codigo_estilo") REFERENCES "public"."estilo" ("codigo") ON DELETE NO ACTION ON UPDATE NO ACTION;
