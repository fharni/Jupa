CREATE TABLE license
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  type varchar(100),
  level int(11) NOT NULL
);

INSERT INTO license (id, name, type, level) VALUES (1, 'Trainerassistent', 'Trainer', 0);
INSERT INTO license (id, name, type, level) VALUES (2, 'Schülermentoren', 'Trainer', 0);
INSERT INTO license (id, name, type, level) VALUES (3, 'Trainer-C Ju-Jutsu/Jiu-Jitsu (Breitensport)', 'Trainer', 1);
INSERT INTO license (id, name, type, level) VALUES (4, 'Trainer-C Ju-Jutsu (Leistungssport)', 'Trainer', 1);
INSERT INTO license (id, name, type, level) VALUES (5, 'Trainer C Polizei - Einsatztraining', 'Trainer', 1);
INSERT INTO license (id, name, type, level) VALUES (6, 'Jugendleiter Ju-Jutsu/Jiu-Jitsu', 'Trainer', 1);
INSERT INTO license (id, name, type, level) VALUES (7, 'Trainer-B (Breitensport)', 'Trainer', 2);
INSERT INTO license (id, name, type, level) VALUES (8, 'Trainer-B Ju-Jutsu (Leistungssport)', 'Trainer', 2);
INSERT INTO license (id, name, type, level) VALUES (9, 'Trainer-B Polizei - Einsatztraining', 'Trainer', 2);
INSERT INTO license (id, name, type, level) VALUES (10, 'Trainer-A Ju-Jutsu (Leistungssport)', 'Trainer', 3);
INSERT INTO license (id, name, type, level) VALUES (11, 'Trainer-A Ju-Jutsu (Breitensport)', 'Trainer', 3);
INSERT INTO license (id, name, type, level) VALUES (12, 'Trainer-A Jiu-Jitsu (Breitensport)', 'Trainer', 3);
INSERT INTO license (id, name, type, level) VALUES (13, 'Diplom-Trainer Ju-Jutsu (Spitzensport)', 'Trainer', 4);
INSERT INTO license (id, name, type, level) VALUES (14, 'Bezirkskampfrichter', 'Kampfrichter', 0);
INSERT INTO license (id, name, type, level) VALUES (15, 'Landeskampfrichter', 'Kampfrichter', 1);
INSERT INTO license (id, name, type, level) VALUES (16, 'Gruppenkampfrichter', 'Kampfrichter', 2);
INSERT INTO license (id, name, type, level) VALUES (17, 'Bundeskampfrichter', 'Kampfrichter', 3);
INSERT INTO license (id, name, type, level) VALUES (18, 'Prüfer Ju-Jutsu', 'Prüfer', 0);
INSERT INTO license (id, name, type, level) VALUES (19, 'Prüfer Jiu-Jitsu', 'Prüfer', 0);
INSERT INTO license (id, name, type, level) VALUES (20, 'DAN-Prüfer Ju-Jutsu', 'Prüfer', 1);
INSERT INTO license (id, name, type, level) VALUES (21, 'DAN-Prüfer Jiu-Jitsu', 'Prüfer', 1);

CREATE TABLE user_license
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  license_id int(11) NOT NULL,
  begin_date date NOT NULL,
  end_date date NOT NULL,
  CONSTRAINT user_license_user_ID_fk FOREIGN KEY (user_id) REFERENCES user (ID),
  CONSTRAINT user_license_license_id_fk FOREIGN KEY (license_id) REFERENCES license (id)
);
CREATE INDEX user_license_user_ID_fk ON user_license (user_id);
CREATE INDEX user_license_license_id_fk ON user_license (license_id);