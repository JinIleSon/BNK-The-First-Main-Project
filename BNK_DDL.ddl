-- 생성자 Oracle SQL Developer Data Modeler 24.3.1.347.1153
--   위치:        2025-11-08 18:42:22 KST
--   사이트:      Oracle Database 21c
--   유형:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE branch (
    brid   NUMBER NOT NULL,
    brname NVARCHAR2(500),
    braddr NVARCHAR2(500),
    brtel  VARCHAR2(100 BYTE),
    brfax  VARCHAR2(100 BYTE)
);

ALTER TABLE branch ADD CONSTRAINT branch_pk PRIMARY KEY ( brid );

CREATE TABLE company (
    cid      NUMBER NOT NULL,
    cname    NVARCHAR2(100),
    cemail   VARCHAR2(500 BYTE),
    cphone   VARCHAR2(100 BYTE),
    caddress NVARCHAR2(500)
);

ALTER TABLE company ADD CONSTRAINT company_pk PRIMARY KEY ( cid );

CREATE TABLE deal (
    did      NUMBER NOT NULL,
    mid      VARCHAR2(500 BYTE) NOT NULL,
    dbalance NUMBER,
    dwho     NVARCHAR2(100),
    ddate    DATE
);

ALTER TABLE deal ADD CONSTRAINT deal_pk PRIMARY KEY ( did );

CREATE TABLE document (
    docid     NUMBER NOT NULL,
    mid       VARCHAR2(500 BYTE),
    doctype   NVARCHAR2(500),
    doctitle  NVARCHAR2(500),
    docanswer NVARCHAR2(500),
    docfile   NVARCHAR2(500),
    docupdate DATE
);

ALTER TABLE document ADD CONSTRAINT document_pk PRIMARY KEY ( docid );

CREATE TABLE fund (
    fid    NUMBER NOT NULL,
    fname  NVARCHAR2(500),
    famc   NVARCHAR2(500),
    frlvl  NUMBER,
    ftype  NVARCHAR2(100),
    frefpr NUMBER,
    fsetdt DATE,
    ftc    NUMBER,
    fm1pr  NUMBER,
    fm3pr  NUMBER,
    fm6pr  NUMBER,
    fm12pr NUMBER,
    facmpr NUMBER
);

ALTER TABLE fund ADD CONSTRAINT fund_pk PRIMARY KEY ( fid );

CREATE TABLE logo (
    lid  NUMBER NOT NULL,
    lurl VARCHAR2(500 BYTE)
);

ALTER TABLE logo ADD CONSTRAINT logo_pk PRIMARY KEY ( lid );

CREATE TABLE pcontract (
    pcuid    VARCHAR2(500 BYTE) NOT NULL,
    pcpid    VARCHAR2(500 BYTE) NOT NULL,
    pccptp   NVARCHAR2(500),
    pccprd   VARCHAR2(500 BYTE),
    pcwcat   NVARCHAR2(500),
    pcwdac   VARCHAR2(200 BYTE),
    pcmdps   NUMBER,
    pcgamn   NUMBER,
    pcatapp  VARCHAR2(100 BYTE),
    pcatdt   NUMBER,
    pcatac   VARCHAR2(500 BYTE),
    pccns    NVARCHAR2(100),
    pcntcs   NVARCHAR2(100),
    pcnapw   VARCHAR2(500 BYTE),
    pacc     VARCHAR2(500 BYTE),
    pnew     DATE,
    pend     DATE,
    pbalance NUMBER
);

ALTER TABLE pcontract ADD CONSTRAINT pcontract_pk PRIMARY KEY ( pcuid,
                                                                pcpid );

CREATE TABLE policy (
    poid   NUMBER NOT NULL,
    poname NVARCHAR2(500),
    pocont CLOB
);

ALTER TABLE policy ADD CONSTRAINT policy_pk PRIMARY KEY ( poid );

CREATE TABLE product (
    id      NUMBER NOT NULL,
    pid     VARCHAR2(500 BYTE),
    ptype   NVARCHAR2(500),
    pname   NVARCHAR2(500),
    pbirate NUMBER,
    phirate NUMBER,
    pcprd   DATE,
    pelgbl  NVARCHAR2(500),
    prmthd  NVARCHAR2(500),
    pprfcrt NVARCHAR2(500),
    pirinfo CLOB,
    pttitle NVARCHAR2(500),
    ptlink  VARCHAR2(500 BYTE),
    pcond   NVARCHAR2(100),
    pupdate DATE
);

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( id );

ALTER TABLE product ADD CONSTRAINT product__un UNIQUE ( pid );

CREATE TABLE tbbranch (
    branchid   NUMBER NOT NULL,
    name       NVARCHAR2(500),
    address    NVARCHAR2(500),
    tel        VARCHAR2(500 BYTE),
    lat        VARCHAR2(500 BYTE),
    lng        VARCHAR2(500 BYTE),
    regioncode VARCHAR2(500 BYTE)
);

ALTER TABLE tbbranch ADD CONSTRAINT tbbranch_pk PRIMARY KEY ( branchid );

CREATE TABLE tbevent (
    eventid   NUMBER NOT NULL,
    title     NVARCHAR2(500),
    content   CLOB,
    startdate DATE,
    enddate   DATE,
    status    VARCHAR2(500 BYTE)
);

ALTER TABLE tbevent ADD CONSTRAINT tb_event_pk PRIMARY KEY ( eventid );

CREATE TABLE tbfile (
    fileid    NUMBER NOT NULL,
    boardtype NVARCHAR2(500),
    boardid   NUMBER,
    filename  NVARCHAR2(500),
    filepath  VARCHAR2(500 BYTE),
    filesize  NUMBER
);

ALTER TABLE tbfile ADD CONSTRAINT tbfile_pk PRIMARY KEY ( fileid );

CREATE TABLE tbhistory (
    year        NVARCHAR2(10) NOT NULL,
    title       NVARCHAR2(500),
    description CLOB,
    orderno     NUMBER
);

ALTER TABLE tbhistory ADD CONSTRAINT tbhistory_pk PRIMARY KEY ( year );

CREATE TABLE tbinvestinfo (
    infoid   NUMBER NOT NULL,
    category NVARCHAR2(500),
    title    NVARCHAR2(500),
    filepath VARCHAR2(500 BYTE),
    regdate  DATE
);

ALTER TABLE tbinvestinfo ADD CONSTRAINT tbinvestinfo_pk PRIMARY KEY ( infoid );

CREATE TABLE tbnews (
    newsid   NUMBER NOT NULL,
    category NVARCHAR2(500),
    title    NVARCHAR2(500),
    content  CLOB,
    filepath VARCHAR2(500 BYTE),
    regdate  DATE
);

ALTER TABLE tbnews ADD CONSTRAINT tb_news_pk PRIMARY KEY ( newsid );

CREATE TABLE tbnotice (
    noticeid NUMBER NOT NULL,
    title    NVARCHAR2(500),
    content  CLOB,
    writer   NVARCHAR2(500),
    regdate  DATE,
    viewcnt  NUMBER
);

ALTER TABLE tbnotice ADD CONSTRAINT tb_notice_pk PRIMARY KEY ( noticeid );

CREATE TABLE "user" (
    "uId"    NUMBER NOT NULL,
    mid      VARCHAR2(500 BYTE),
    mpw      VARCHAR2(500 BYTE),
    mname    NVARCHAR2(500),
    mbirth   DATE,
    mgender  VARCHAR2(500 BYTE),
    mcarrier NVARCHAR2(500),
    maddress NVARCHAR2(500),
    memail   VARCHAR2(500 BYTE),
    mphone   VARCHAR2(500 BYTE),
    mdate    DATE,
    mgrade   VARCHAR2(500 BYTE),
    mjumin   VARCHAR2(500 BYTE),
    mcond    NVARCHAR2(100),
    mnum     VARCHAR2(100 BYTE),
    maccess  DATE,
    mlimit   NUMBER,
    mtitle   CLOB,
    mcontent CLOB
);

ALTER TABLE "user" ADD CONSTRAINT user_pk PRIMARY KEY ( "uId" );

ALTER TABLE "user" ADD CONSTRAINT user__unv1 UNIQUE ( mid );

ALTER TABLE deal
    ADD CONSTRAINT deal_user_fk FOREIGN KEY ( mid )
        REFERENCES "user" ( mid );

ALTER TABLE pcontract
    ADD CONSTRAINT pcontract_product_fk FOREIGN KEY ( pcpid )
        REFERENCES product ( pid );

ALTER TABLE pcontract
    ADD CONSTRAINT pcontract_user_fk FOREIGN KEY ( pcuid )
        REFERENCES "user" ( mid );



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                            17
-- CREATE INDEX                             0
-- ALTER TABLE                             22
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
