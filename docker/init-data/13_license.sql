CREATE TABLE license_type
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  type varchar(50) NOT NULL
);

CREATE TABLE license
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  level int(11) NOT NULL,
  type_id int(11) NOT NULL,
  foreign key (type_id) references license_type (id)
);

CREATE TABLE user_license
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  license_id int(11) NOT NULL,
  begindate date NOT NULL,
  enddate date NOT NULL,
  CONSTRAINT user_license_user_ID_fk FOREIGN KEY (user_id) REFERENCES user (ID),
  CONSTRAINT user_license_license_id_fk FOREIGN KEY (license_id) REFERENCES license (id)
);
CREATE INDEX user_license_user_ID_fk ON user_license (user_id);
CREATE INDEX user_license_license_id_fk ON user_license (license_id);