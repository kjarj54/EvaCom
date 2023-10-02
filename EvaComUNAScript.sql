/*
Created: 18/08/2023
Modified: 02/10/2023
Model: EvaCom
Database: Oracle 18c
*/




-- Create sequences section -------------------------------------------------

CREATE SEQUENCE TAR_USUARIO_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_PARAMETROS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_PUESTO_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_COMPETENCIA_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_CARACTERISTICA_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_COMPETENCIAEVALUAR_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_EVALUADOR_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_TRABAJOREVALUAR_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE TAR_PROCESOEVALUACION_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

-- Create tables section -------------------------------------------------

-- Table TAR_PARAMETROS

CREATE TABLE TAR_PARAMETROS(
  par_id Number NOT NULL,
  par_nombre Varchar2(20 ),
  par_email Varchar2(80 ),
  par_clave Varchar2(20 ),
  par_html Blob,
  par_logo Blob,
  par_descripcion Varchar2(30 ),
  par_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table TAR_PARAMETROS

ALTER TABLE TAR_PARAMETROS ADD CONSTRAINT TAR_PARAMETROS_PK PRIMARY KEY (par_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_PARAMETROS.par_id IS 'Id de la tabla de la empresa'
;
COMMENT ON COLUMN TAR_PARAMETROS.par_nombre IS 'Nombre de la empresa'
;
COMMENT ON COLUMN TAR_PARAMETROS.par_email IS 'Email del cual se enviaran los correos'
;
COMMENT ON COLUMN TAR_PARAMETROS.par_clave IS 'Clave del email'
;
COMMENT ON COLUMN TAR_PARAMETROS.par_html IS 'Plantilla de referencia para los emails'
;
COMMENT ON COLUMN TAR_PARAMETROS.par_logo IS 'Logo de la empresa'
;
COMMENT ON COLUMN TAR_PARAMETROS.par_descripcion IS 'Descripcion de la empresa'
;
COMMENT ON COLUMN TAR_PARAMETROS.par_version IS 'Version del registro'
;

-- Table TAR_USUARIO

CREATE TABLE TAR_USUARIO(
  usu_id Number NOT NULL,
  usu_nombre Varchar2(25 ) NOT NULL,
  usu_apellido Varchar2(25 ) NOT NULL,
  usu_cedula Varchar2(9 ) NOT NULL,
  usu_correo Varchar2(80 ) NOT NULL,
  usu_telefono Number,
  usu_celular Number,
  usu_foto Blob,
  usu_usu Varchar2(20 ) NOT NULL,
  usu_clave Varchar2(15 ) NOT NULL,
  usu_tempclave Varchar2(15 ),
  usu_activo Varchar2(1 ) DEFAULT 'I' NOT NULL,
  usu_admin Varchar2(1 ) DEFAULT 'N' NOT NULL,
  usu_version Number DEFAULT 1 NOT NULL,
  pue_id Number,
  CONSTRAINT TAR_USUARIO_CK01 CHECK (usu_activo in ('A','I')),
  CONSTRAINT TAR_USUARIO_CK02 CHECK (usu_admin in ('S','N'))
)
;

-- Create indexes for table TAR_USUARIO

CREATE INDEX TAR_PUESTOUSUARIO_UNQ01 ON TAR_USUARIO (pue_id)
;

CREATE UNIQUE INDEX TAR_USUARIO_UNQ01 ON TAR_USUARIO (usu_correo)
;

CREATE UNIQUE INDEX TAR_USUARIO_UNQ02 ON TAR_USUARIO (usu_cedula)
;

-- Add keys for table TAR_USUARIO

ALTER TABLE TAR_USUARIO ADD CONSTRAINT TAR_USUARIO_PK PRIMARY KEY (usu_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_USUARIO.usu_id IS 'Id del registro de usuario'
;
COMMENT ON COLUMN TAR_USUARIO.usu_nombre IS 'Nombre del usuario'
;
COMMENT ON COLUMN TAR_USUARIO.usu_apellido IS 'Apellido del usaurio'
;
COMMENT ON COLUMN TAR_USUARIO.usu_cedula IS 'Cedula del usuario'
;
COMMENT ON COLUMN TAR_USUARIO.usu_correo IS 'Correo del usuario'
;
COMMENT ON COLUMN TAR_USUARIO.usu_telefono IS 'Telefono del usuario'
;
COMMENT ON COLUMN TAR_USUARIO.usu_celular IS 'Celular del usaurio'
;
COMMENT ON COLUMN TAR_USUARIO.usu_foto IS 'Foto del usaurio'
;
COMMENT ON COLUMN TAR_USUARIO.usu_usu IS 'Usuario con el que accedera el usuario
'
;
COMMENT ON COLUMN TAR_USUARIO.usu_clave IS 'Clave del usuario'
;
COMMENT ON COLUMN TAR_USUARIO.usu_tempclave IS 'Clave temporal que se le asignara al usuario para recuperacion e inicio
'
;
COMMENT ON COLUMN TAR_USUARIO.usu_activo IS 'Estado del usuario (A:Activo, I:Inactivo)'
;
COMMENT ON COLUMN TAR_USUARIO.usu_admin IS 'Estado de administrador del usaurio (S:Si, N:No)'
;
COMMENT ON COLUMN TAR_USUARIO.usu_version IS 'Version del registro de usaurio'
;

-- Table TAR_PUESTO

CREATE TABLE TAR_PUESTO(
  pue_id Number NOT NULL,
  pue_nombre Varchar2(30 ) NOT NULL,
  pue_estado Varchar2(1 ) DEFAULT 'A' NOT NULL,
  pue_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT TAR_PUESTO_CK01 CHECK (pue_estado in ('A','I'))
)
;

-- Add keys for table TAR_PUESTO

ALTER TABLE TAR_PUESTO ADD CONSTRAINT TAR_PUESTO_PK PRIMARY KEY (pue_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_PUESTO.pue_id IS 'Id de la tabla de puesto'
;
COMMENT ON COLUMN TAR_PUESTO.pue_nombre IS 'Nombre del puesto'
;
COMMENT ON COLUMN TAR_PUESTO.pue_estado IS 'Estado del puesto(A:Activo, I:Inactivo)'
;
COMMENT ON COLUMN TAR_PUESTO.pue_version IS 'Version del registro de puesto'
;

-- Table TAR_COMPETENCIA

CREATE TABLE TAR_COMPETENCIA(
  com_id Number NOT NULL,
  com_nombre Varchar2(30 ) NOT NULL,
  com_estado Varchar2(1 ) DEFAULT 'A' NOT NULL,
  com_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT TAR_COMPETENCIA_CK01 CHECK (com_estado in ('A','I'))
)
;

-- Add keys for table TAR_COMPETENCIA

ALTER TABLE TAR_COMPETENCIA ADD CONSTRAINT TAR_COMPETENCIA_PK PRIMARY KEY (com_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_COMPETENCIA.com_id IS 'Id del registro de competencia'
;
COMMENT ON COLUMN TAR_COMPETENCIA.com_nombre IS 'Nombre de la competencia'
;
COMMENT ON COLUMN TAR_COMPETENCIA.com_estado IS 'Estado de la competencia(A:Activa, I:Inactiva)'
;
COMMENT ON COLUMN TAR_COMPETENCIA.com_version IS 'Version del registro de la competencia'
;

-- Table TAR_CARACTERISTICA

CREATE TABLE TAR_CARACTERISTICA(
  car_id Number NOT NULL,
  car_descripcion Varchar2(150 ) NOT NULL,
  car_version Number DEFAULT 1 NOT NULL,
  com_id Number
)
;

-- Create indexes for table TAR_CARACTERISTICA

CREATE INDEX TAR_COMPETENCIACARACTERISTICA_UNQ01 ON TAR_CARACTERISTICA (com_id)
;

-- Add keys for table TAR_CARACTERISTICA

ALTER TABLE TAR_CARACTERISTICA ADD CONSTRAINT TAR_CARACTERISTICA_PK PRIMARY KEY (car_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_CARACTERISTICA.car_id IS 'Id del registro de las caracteristicas'
;
COMMENT ON COLUMN TAR_CARACTERISTICA.car_descripcion IS 'Descripcion de la competencia'
;
COMMENT ON COLUMN TAR_CARACTERISTICA.car_version IS 'Version del registro de la caracteristica'
;

-- Table TAR_PROCESOEVALUACION

CREATE TABLE TAR_PROCESOEVALUACION(
  pro_id Number NOT NULL,
  pro_fini Date NOT NULL,
  pro_ffin Date NOT NULL,
  pro_titulo Varchar2(100 ) NOT NULL,
  pro_estado Varchar2(1 ) NOT NULL,
  pro_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT TAR_PROCESOEVALUACION_CK01 CHECK (pro_estado in ('C','A','R','F'))
)
;

-- Add keys for table TAR_PROCESOEVALUACION

ALTER TABLE TAR_PROCESOEVALUACION ADD CONSTRAINT TAR_PROCESOEVALUACION_PK PRIMARY KEY (pro_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_PROCESOEVALUACION.pro_id IS 'Id de la tabla de evaluacion'
;
COMMENT ON COLUMN TAR_PROCESOEVALUACION.pro_fini IS 'Fecha de inicio de la evaluacion'
;
COMMENT ON COLUMN TAR_PROCESOEVALUACION.pro_ffin IS 'Fecha limite definalizacilon de la evaluacion'
;
COMMENT ON COLUMN TAR_PROCESOEVALUACION.pro_titulo IS 'Titulo de la evaluacion'
;
COMMENT ON COLUMN TAR_PROCESOEVALUACION.pro_estado IS 'Estado de la evaluaciion(C:Construccion, A:Aplicacion, R:Revision, F:Finalizada)'
;
COMMENT ON COLUMN TAR_PROCESOEVALUACION.pro_version IS 'Version el registro de la evaluacion'
;

-- Table TAR_PUESTOCOMPETENCIA

CREATE TABLE TAR_PUESTOCOMPETENCIA(
  pue_id Number NOT NULL,
  com_id Number NOT NULL
)
;

-- Add keys for table TAR_PUESTOCOMPETENCIA

ALTER TABLE TAR_PUESTOCOMPETENCIA ADD CONSTRAINT PK_TAR_PUESTOCOMPETENCIA PRIMARY KEY (pue_id,com_id)
;

-- Table TAR_EVALUADOR

CREATE TABLE TAR_EVALUADOR(
  evalu_id Number NOT NULL,
  evalu_retroalimentacion Char(200 ),
  evalu_version Number NOT NULL,
  evalu_calificacion Varchar2(15 ),
  usu_id Number,
  tra_id Number
)
;

-- Create indexes for table TAR_EVALUADOR

CREATE INDEX TAR_EVALUADORUSUARIO_UNQ01 ON TAR_EVALUADOR (usu_id)
;

CREATE INDEX TAR_EVALUADOR_TRABAJADOREVA_UNQ01 ON TAR_EVALUADOR (tra_id)
;

-- Add keys for table TAR_EVALUADOR

ALTER TABLE TAR_EVALUADOR ADD CONSTRAINT TAR_EVALUADOR_PK PRIMARY KEY (evalu_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_EVALUADOR.evalu_id IS 'id del evaluador'
;
COMMENT ON COLUMN TAR_EVALUADOR.evalu_retroalimentacion IS 'retroalimentacion realizada por el evaluador'
;
COMMENT ON COLUMN TAR_EVALUADOR.evalu_version IS 'version del registro del evaluador'
;
COMMENT ON COLUMN TAR_EVALUADOR.evalu_calificacion IS 'Calificacion asignada por evaluador'
;

-- Table TAR_TRABAJADOREVALUAR

CREATE TABLE TAR_TRABAJADOREVALUAR(
  tra_id Number NOT NULL,
  tra_resultado Varchar2(5 ),
  tra_version Number NOT NULL,
  usu_id Number,
  pro_id Number
)
;

-- Create indexes for table TAR_TRABAJADOREVALUAR

CREATE INDEX TAR_TRABAJADOREVA_USUARIO_UNQ01 ON TAR_TRABAJADOREVALUAR (usu_id)
;

CREATE INDEX TAR_TRABAJADOREVA_PROCESOEVA_UNQ01 ON TAR_TRABAJADOREVALUAR (pro_id)
;

-- Add keys for table TAR_TRABAJADOREVALUAR

ALTER TABLE TAR_TRABAJADOREVALUAR ADD CONSTRAINT TAR_TRABAJADOREVALUAR_PK PRIMARY KEY (tra_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_TRABAJADOREVALUAR.tra_id IS 'id del trabajador a evaluar'
;
COMMENT ON COLUMN TAR_TRABAJADOREVALUAR.tra_resultado IS 'resultado final de la evaluacion del trabajador'
;
COMMENT ON COLUMN TAR_TRABAJADOREVALUAR.tra_version IS 'version del registro del trabajador a evaluar'
;

-- Table TAR_COMPETENCIAEVALUAR

CREATE TABLE TAR_COMPETENCIAEVALUAR(
  coe_id Number NOT NULL,
  coe_calificacion Varchar2(5 ),
  coe_version Number DEFAULT 1 NOT NULL,
  evalu_id Number,
  com_id Number
)
;

-- Create indexes for table TAR_COMPETENCIAEVALUAR

CREATE INDEX TAR_EVALUADOR_COMPETENCIAEVA_UNQ01 ON TAR_COMPETENCIAEVALUAR (evalu_id)
;

CREATE INDEX TAR_COMPETENCIAEVA_COMPETENCIA_UNQ01 ON TAR_COMPETENCIAEVALUAR (com_id)
;

-- Add keys for table TAR_COMPETENCIAEVALUAR

ALTER TABLE TAR_COMPETENCIAEVALUAR ADD CONSTRAINT TAR_COMPETENCIAEVALUAR_PK PRIMARY KEY (coe_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN TAR_COMPETENCIAEVALUAR.coe_id IS 'id de la competencia a evaluar'
;
COMMENT ON COLUMN TAR_COMPETENCIAEVALUAR.coe_calificacion IS 'calificacion de la competencia a evaluar'
;
COMMENT ON COLUMN TAR_COMPETENCIAEVALUAR.coe_version IS 'Version del registro de la competencia a evaluar'
;


-- Trigger for sequence TAR_PARAMETROS_SEQ01 for column par_id in table TAR_PARAMETROS ---------
CREATE OR REPLACE TRIGGER TAR_PARAMETROS_TGR01 BEFORE INSERT
ON TAR_PARAMETROS FOR EACH ROW
BEGIN
    if:new.par_id is null or :new.par_id <=0 then
        :new.par_id := TAR_PARAMETROS_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_PARAMETROS_TGR02 AFTER UPDATE OF par_id
ON TAR_PARAMETROS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column par_id in table TAR_PARAMETROS as it uses sequence.');
END;
/

-- Trigger for sequence TAR_USUARIO_SEQ01 for column usu_id in table TAR_USUARIO ---------
CREATE OR REPLACE TRIGGER TAR_USUARIO_TGR01 BEFORE INSERT
ON TAR_USUARIO FOR EACH ROW
BEGIN
    if:new.usu_id is null or :new.usu_id <=0 then
        :new.usu_id := TAR_USUARIO_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_USUARIO_TGR02 AFTER UPDATE OF usu_id
ON TAR_USUARIO FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column usu_id in table TAR_USUARIO as it uses sequence.');
END;
/

-- Trigger for sequence TAR_PUESTO_SEQ01 for column pue_id in table TAR_PUESTO ---------
CREATE OR REPLACE TRIGGER TAR_PUESTO_TGR01 BEFORE INSERT
ON TAR_PUESTO FOR EACH ROW
BEGIN
    if:new.pue_id is null or :new.pue_id <=0 then
        :new.pue_id := TAR_PUESTO_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_PUESTO_TGR02 AFTER UPDATE OF pue_id
ON TAR_PUESTO FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pue_id in table TAR_PUESTO as it uses sequence.');
END;
/

-- Trigger for sequence TAR_COMPETENCIA_SEQ01 for column com_id in table TAR_COMPETENCIA ---------
CREATE OR REPLACE TRIGGER TAR_COMPETENCIA_TGR01 BEFORE INSERT
ON TAR_COMPETENCIA FOR EACH ROW
BEGIN
    if:new.com_id is null or :new.com_id <=0 then
      :new.com_id := TAR_COMPETENCIA_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_COMPETENCIA_TGR02 AFTER UPDATE OF com_id
ON TAR_COMPETENCIA FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column com_id in table TAR_COMPETENCIA as it uses sequence.');
END;
/

-- Trigger for sequence TAR_CARACTERISTICA_SEQ01 for column car_id in table TAR_CARACTERISTICA ---------
CREATE OR REPLACE TRIGGER TAR_CARACTERISTICA_TGR01 BEFORE INSERT
ON TAR_CARACTERISTICA FOR EACH ROW
BEGIN
    if:new.car_id is null or :new.car_id <=0 then
        :new.car_id := TAR_CARACTERISTICA_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_CARACTERISTICA_TGR02 AFTER UPDATE OF car_id
ON TAR_CARACTERISTICA FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column car_id in table TAR_CARACTERISTICA as it uses sequence.');
END;
/

-- Trigger for sequence TAR_PROCESOEVALUACION_SEQ01 for column pro_id in table TAR_PROCESOEVALUACION ---------
CREATE OR REPLACE TRIGGER TAR_PROCESOEVALUACION_TGR01 BEFORE INSERT
ON TAR_PROCESOEVALUACION FOR EACH ROW
BEGIN
    if:new.pro_id is null or :new.pro_id <=0 then
        :new.pro_id := TAR_PROCESOEVALUACION_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_PROCESOEVALUACION_TGR02 AFTER UPDATE OF pro_id
ON TAR_PROCESOEVALUACION FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pro_id in table TAR_PROCESOEVALUACION as it uses sequence.');
END;
/

-- Trigger for sequence TAR_EVALUADOR_SEQ01 for column evalu_id in table TAR_EVALUADOR ---------
CREATE OR REPLACE TRIGGER TAR_EVALUADOR_TGR01 BEFORE INSERT
ON TAR_EVALUADOR FOR EACH ROW
BEGIN
    if:new.evalu_id is null or :new.evalu_id <=0 then
        :new.evalu_id := TAR_EVALUADOR_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_EVALUADOR_TGR02 AFTER UPDATE OF evalu_id
ON TAR_EVALUADOR FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column evalu_id in table TAR_EVALUADOR as it uses sequence.');
END;
/

-- Trigger for sequence TAR_TRABAJOREVALUAR_SEQ01 for column tra_id in table TAR_TRABAJADOREVALUAR ---------
CREATE OR REPLACE TRIGGER TAR_TRABAJOREVALUAR_TGR01 BEFORE INSERT
ON TAR_TRABAJADOREVALUAR FOR EACH ROW
BEGIN
    if:new.tra_id is null or :new.tra_id <=0 then
        :new.tra_id := TAR_TRABAJOREVALUAR_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_TRABAJOREVALUAR_TGR02 AFTER UPDATE OF tra_id
ON TAR_TRABAJADOREVALUAR FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column tra_id in table TAR_TRABAJADOREVALUAR as it uses sequence.');
END;
/

-- Trigger for sequence TAR_COMPETENCIAEVALUAR_SEQ01 for column coe_id in table TAR_COMPETENCIAEVALUAR ---------
CREATE OR REPLACE TRIGGER TAR_COMPETENCIAEVALUAR_TGR01 BEFORE INSERT
ON TAR_COMPETENCIAEVALUAR FOR EACH ROW
BEGIN
    if:new.coe_id is null or :new.coe_id <=0 then
      :new.coe_id := TAR_COMPETENCIAEVALUAR_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER TAR_COMPETENCIAEVALUAR_TGR02 AFTER UPDATE OF coe_id
ON TAR_COMPETENCIAEVALUAR FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column coe_id in table TAR_COMPETENCIAEVALUAR as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE TAR_CARACTERISTICA ADD CONSTRAINT TAR_COMPETENCIACARACTERISTICA_FK01 FOREIGN KEY (com_id) REFERENCES TAR_COMPETENCIA (com_id)
;


ALTER TABLE TAR_USUARIO ADD CONSTRAINT TAR_PUESTOUSUARIO_FK01 FOREIGN KEY (pue_id) REFERENCES TAR_PUESTO (pue_id)
;


ALTER TABLE TAR_PUESTOCOMPETENCIA ADD CONSTRAINT TAR_PUESTOCOMPETENCIA_FK01 FOREIGN KEY (pue_id) REFERENCES TAR_PUESTO (pue_id)
;


ALTER TABLE TAR_PUESTOCOMPETENCIA ADD CONSTRAINT TAR_PUESTOCOMPETENCIA_FK02 FOREIGN KEY (com_id) REFERENCES TAR_COMPETENCIA (com_id)
;


ALTER TABLE TAR_EVALUADOR ADD CONSTRAINT TAR_EVALUADORUSUARIO_FK01 FOREIGN KEY (usu_id) REFERENCES TAR_USUARIO (usu_id)
;


ALTER TABLE TAR_TRABAJADOREVALUAR ADD CONSTRAINT TAR_TRABAJADOREVA_USUARIO_FK01 FOREIGN KEY (usu_id) REFERENCES TAR_USUARIO (usu_id)
;


ALTER TABLE TAR_TRABAJADOREVALUAR ADD CONSTRAINT TAR_TRABAJADOREVA_PROCESOEVA_FK01 FOREIGN KEY (pro_id) REFERENCES TAR_PROCESOEVALUACION (pro_id)
;


ALTER TABLE TAR_EVALUADOR ADD CONSTRAINT TAR_EVALUADOR_TRABAJADOREVA_FK01 FOREIGN KEY (tra_id) REFERENCES TAR_TRABAJADOREVALUAR (tra_id)
;


ALTER TABLE TAR_COMPETENCIAEVALUAR ADD CONSTRAINT TAR_EVALUADOR_COMPETENCIAEVA_FK01 FOREIGN KEY (evalu_id) REFERENCES TAR_EVALUADOR (evalu_id)
;


ALTER TABLE TAR_COMPETENCIAEVALUAR ADD CONSTRAINT TAR_COMPETENCIAEVA_COMPETENCIA_FK01 FOREIGN KEY (com_id) REFERENCES TAR_COMPETENCIA (com_id)
;







