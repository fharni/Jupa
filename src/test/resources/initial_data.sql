INSERT INTO FEDERATION (ID, NAME, DISPLAYID) VALUES (5, 'Nordrhein-Westfalen', 5);
INSERT INTO DISTRICT (ID, NAME, FEDERATION_ID, DISPLAYID) VALUES (6, 'Bielefeld', 5, 2);
INSERT INTO CLUB (ID, NAME, DISTRICT_ID, DISPLAYID) VALUES (1, 'TV 1875 Paderborn e.V.', 6, 1);
INSERT INTO USER (ID, USERNAME, PASSWORD, FORENAME, SURNAME, CLUB_ID) VALUES (1, '0502001000015', 'falk', 'Falk', 'Harnisch', 1);