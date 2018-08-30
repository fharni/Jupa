CREATE TABLE license_type
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  type varchar(50) NOT NULL
);

alter table license_type add primary key (id);

INSERT INTO license_type (id, type) VALUES (1, 'Trainer');
INSERT INTO license_type (id, type) VALUES (2, 'Kampfrichter');
INSERT INTO license_type (id, type) VALUES (3, 'Prüfer');

CREATE TABLE license
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  level int(11) NOT NULL,
  type_id int(11) NOT NULL,
  foreign key (type_id) references license_type (id)
);

INSERT INTO license (id, name, level, type_id) VALUES (1, 'Trainerassistent', 0, 1);
INSERT INTO license (id, name, level, type_id) VALUES (2, 'Schülermentoren', 0, 1);
INSERT INTO license (id, name, level, type_id) VALUES (3, 'Trainer-C Ju-Jutsu/Jiu-Jitsu (Breitensport)', 1, 1);
INSERT INTO license (id, name, level, type_id) VALUES (4, 'Trainer-C Ju-Jutsu (Leistungssport)', 1, 1);
INSERT INTO license (id, name, level, type_id) VALUES (5, 'Trainer C Polizei - Einsatztraining', 1, 1);
INSERT INTO license (id, name, level, type_id) VALUES (6, 'Jugendleiter Ju-Jutsu/Jiu-Jitsu', 1, 1);
INSERT INTO license (id, name, level, type_id) VALUES (7, 'Trainer-B (Breitensport)', 2, 1);
INSERT INTO license (id, name, level, type_id) VALUES (8, 'Trainer-B Ju-Jutsu (Leistungssport)', 2, 1);
INSERT INTO license (id, name, level, type_id) VALUES (9, 'Trainer-B Polizei - Einsatztraining', 2, 1);
INSERT INTO license (id, name, level, type_id) VALUES (10, 'Trainer-A Ju-Jutsu (Leistungssport)', 3, 1);
INSERT INTO license (id, name, level, type_id) VALUES (11, 'Trainer-A Ju-Jutsu (Breitensport)', 3, 1);
INSERT INTO license (id, name, level, type_id) VALUES (12, 'Trainer-A Jiu-Jitsu (Breitensport)', 3, 1);
INSERT INTO license (id, name, level, type_id) VALUES (13, 'Diplom-Trainer Ju-Jutsu (Spitzensport)', 4, 1);
INSERT INTO license (id, name, level, type_id) VALUES (14, 'Bezirkskampfrichter', 0, 2);
INSERT INTO license (id, name, level, type_id) VALUES (15, 'Landeskampfrichter', 1, 2);
INSERT INTO license (id, name, level, type_id) VALUES (16, 'Gruppenkampfrichter', 2, 2);
INSERT INTO license (id, name, level, type_id) VALUES (17, 'Bundeskampfrichter', 3, 2);
INSERT INTO license (id, name, level, type_id) VALUES (18, 'Prüfer Ju-Jutsu', 0, 3);
INSERT INTO license (id, name, level, type_id) VALUES (19, 'Prüfer Jiu-Jitsu', 0, 3);
INSERT INTO license (id, name, level, type_id) VALUES (20, 'DAN-Prüfer Ju-Jutsu', 1, 3);
INSERT INTO license (id, name, level, type_id) VALUES (21, 'DAN-Prüfer Jiu-Jitsu', 1, 3);

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