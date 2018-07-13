--<ScriptOptions statementTerminator=";"/>

CREATE TABLE ARCO (
		ID INTEGER primary key not null generated always as identity (start with 1, increment by 1),
		NODOPARTENZAID INTEGER NOT NULL,
		NODOARRIVOID INTEGER NOT NULL,
		MAPPAID INTEGER NOT NULL
	);

CREATE TABLE MAPPA (
		PIANO INTEGER primary key NOT NULL,
		PIANTINA VARCHAR(20),
		STATOEMERGENZA INTEGER DEFAULT 0
	);

CREATE TABLE NODO (
		ID INTEGER primary key not null generated always as identity (start with 1, increment by 1),
		BEACONID VARCHAR(20) unique,
		X INTEGER NOT NULL,
		Y INTEGER NOT NULL,
		TIPO INTEGER NOT NULL,
		PIANO INTEGER NOT NULL
	);

CREATE TABLE PESOARCO (
		ID INTEGER primary key not null generated always as identity (start with 1, increment by 1),
		IDARCO INTEGER NOT NULL,
		IDPESO INTEGER NOT NULL,
		VALORE INTEGER NOT NULL
	);

CREATE TABLE PESO (
		ID INTEGER primary key not null generated always as identity (start with 1, increment by 1),
		DESCRIZIONE VARCHAR(30) unique,
		PESO INTEGER NOT NULL
	);
	
CREATE TABLE UTENTE (
		ID INTEGER primary key not null generated always as identity (start with 1, increment by 1),
		TOKEN VARCHAR(256) NOT NULL
	);

