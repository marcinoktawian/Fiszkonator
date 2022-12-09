PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Kategoria" (
	"IdKategorii"	INTEGER NOT NULL UNIQUE,
	"NazwaKategorii"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("IdKategorii" AUTOINCREMENT)
);
INSERT INTO Kategoria VALUES(1,'Kim jesteś');
CREATE TABLE IF NOT EXISTS "Statystyki" (
	"IdFiszki"	INTEGER NOT NULL,
	"IloscBlednychOdp"	REAL,
	"IloscPrawidlowychOdp"	REAL,
	"PoziomNauczenia"	INTEGER,
	FOREIGN KEY("IdFiszki") REFERENCES "Fiszka"("IdFiszki")
);
INSERT INTO Statystyki VALUES(1,0.0,0.0,1);
INSERT INTO Statystyki VALUES(2,0.0,0.0,1);
INSERT INTO Statystyki VALUES(3,0.0,0.0,1);
INSERT INTO Statystyki VALUES(4,0.0,0.0,1);
INSERT INTO Statystyki VALUES(5,0.0,0.0,1);
INSERT INTO Statystyki VALUES(6,0.0,0.0,1);
INSERT INTO Statystyki VALUES(7,0.0,0.0,1);
INSERT INTO Statystyki VALUES(8,0.0,0.0,1);
INSERT INTO Statystyki VALUES(9,0.0,0.0,1);
CREATE TABLE IF NOT EXISTS "android_metadata" (
	"locale"	TEXT
);
INSERT INTO android_metadata VALUES('pl_PL');
CREATE TABLE IF NOT EXISTS "Zestaw" (
	"IdZestawu"	INTEGER NOT NULL UNIQUE,
	"NazwaZestawu"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("IdZestawu" AUTOINCREMENT)
);
INSERT INTO Zestaw VALUES(1,'Starter');
CREATE TABLE IF NOT EXISTS "Fiszka" (
	"IdFiszki"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"IdKategorii"	INTEGER NOT NULL,
	"IdZestawu"	INTEGER NOT NULL,
	"Polski"	TEXT NOT NULL,
	"KomentarzPolski"	TEXT,
	"UzyciePolski"	TEXT,
	"Obcy"	TEXT NOT NULL,
	"KomentarzObcy"	TEXT,
	"UzycieObcy"	TEXT,
	"Poziom"	TEXT,
	"Nagranie"	TEXT,
	FOREIGN KEY("IdZestawu") REFERENCES "Zestaw"("IdZestawu"),
	FOREIGN KEY("IdKategorii") REFERENCES "Kategoria"("IdKategorii")
);
INSERT INTO Fiszka VALUES(1,1,1,'być','(dla określenia tożsamości lub pochodzenia, narodowości, zawodu)',replace('ja						jestem\nty						jesteś\non/-a; pan/-i		jest','\n',char(10)),'ser', NULL,replace('yo					soy\ntú						eres\nél/ella; usted	es','\n',char(10)),'A1','starter_001');
INSERT INTO Fiszka VALUES(2,1,1,'ja',NULL,'(Ja) jestem Paweł','yo', NULL,'(Yo) soy Pablo','A1','starter_002');
INSERT INTO Fiszka VALUES(3,1,1,'kto?',NULL,'Kim jesteś?','¿quién?', NULL,'¿Quién eres? ','A1','starter_003');
INSERT INTO Fiszka VALUES(4,1,1,replace('Polak\nPolka','\n',char(10)),NULL,'(Ja) jestem Polakiem.',replace('el polaco\nla polaca','\n',char(10)), NULL,'(Yo) soy polaco.','A1','starter_004');
INSERT INTO Fiszka VALUES(5,1,1,'ty',NULL,'(Ty) jesteś Agnieszka.','tú', NULL,'(Tú) eres Inés.','A1','starter_005');
INSERT INTO Fiszka VALUES(6,1,1,replace('Hiszpan\nHiszpanka','\n',char(10)),NULL,'(Ty) jesteś Hiszpanką',replace('el español\nla española','\n',char(10)), NULL,'(Tú) eres española.','A1','starter_006');
INSERT INTO Fiszka VALUES(7,1,1,'on',NULL,'On jest Hiszpanem','él', NULL,'Él es español.','A1','starter_007');
INSERT INTO Fiszka VALUES(8,1,1,'ona',NULL,'Ona jest Polką.','ella', NULL,'Ella es polaca.','A1','starter_008');
INSERT INTO Fiszka VALUES(9,1,1,'pan /pani',NULL,'Czy pan jest Polakiem?','usted', NULL,'¿Es usted polaca? ','A1','starter_009');
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('Zestaw',1);
INSERT INTO sqlite_sequence VALUES('Kategoria',1);
INSERT INTO sqlite_sequence VALUES('Fiszka',9);
COMMIT;
