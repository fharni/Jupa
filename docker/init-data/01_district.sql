CREATE TABLE DISTRICT (
  ID            INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  NAME          VARCHAR(100) NOT NULL,
  FEDERATION_ID INTEGER      NOT NULL,
  DISPLAYID     CHARACTER(4) NOT NULL,
  FOREIGN KEY (FEDERATION_ID) REFERENCES FEDERATION (ID)
);

INSERT INTO DISTRICT VALUES(1,'Bremen',1,'0101');
INSERT INTO DISTRICT VALUES(2,'Hamburg',2,'0201');
INSERT INTO DISTRICT VALUES(3,'Schleswig-Holstein',3,'0301');
INSERT INTO DISTRICT VALUES(4,'Mecklenburg-Vorpommern',4,'0401');
INSERT INTO DISTRICT VALUES(5,'Aachen',5,'0501');
INSERT INTO DISTRICT VALUES(6,'Bielefeld',5,'0502');
INSERT INTO DISTRICT VALUES(7,'Arnsberg',5,'0503');
INSERT INTO DISTRICT VALUES(8,'Köln',5,'0504');
INSERT INTO DISTRICT VALUES(9,'Düsseldorf',5,'0505');
INSERT INTO DISTRICT VALUES(10,'Mönchengladbach',5,'0506');
INSERT INTO DISTRICT VALUES(11,'Siegen',5,'0507');
INSERT INTO DISTRICT VALUES(12,'Polizei',5,'0508');
INSERT INTO DISTRICT VALUES(13,'Justiz',5,'0509');
INSERT INTO DISTRICT VALUES(14,'Jiu-Jitsu',5,'0510');
INSERT INTO DISTRICT VALUES(15,'Hessen',6,'0601');
INSERT INTO DISTRICT VALUES(16,'Sachsen-Anhalt',7,'0701');
INSERT INTO DISTRICT VALUES(17,'Thüringen',8,'0801');
INSERT INTO DISTRICT VALUES(18,'Brandenburg',9,'0901');
INSERT INTO DISTRICT VALUES(19,'Berlin',10,'1001');
INSERT INTO DISTRICT VALUES(20,'Sachsen',11,'1101');
INSERT INTO DISTRICT VALUES(21,'Rheinland-Pfalz',12,'1201');
INSERT INTO DISTRICT VALUES(22,'Saarland',13,'1301');
INSERT INTO DISTRICT VALUES(23,'Baden',14,'1401');
INSERT INTO DISTRICT VALUES(24,'Würtemberg',15,'1501');
INSERT INTO DISTRICT VALUES(25,'Oberbayern',16,'1601');
INSERT INTO DISTRICT VALUES(26,'Niederbayern',16,'1602');
INSERT INTO DISTRICT VALUES(27,'Oberfranken',16,'1603');
INSERT INTO DISTRICT VALUES(28,'Mittelfranken',16,'1604');
INSERT INTO DISTRICT VALUES(29,'Unterfranken',16,'1605');
INSERT INTO DISTRICT VALUES(30,'Oberpfalz',16,'1606');
INSERT INTO DISTRICT VALUES(31,'Schwaben',16,'1607');
INSERT INTO DISTRICT VALUES(32,'Braunschweig',17,'1701');
INSERT INTO DISTRICT VALUES(33,'Hannover',17,'1702');
INSERT INTO DISTRICT VALUES(34,'Lüneburg',17,'1703');
INSERT INTO DISTRICT VALUES(35,'Weser-Ems',17,'1704');
