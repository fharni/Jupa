CREATE TABLE audit_status
(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    status varchar(255) NOT NULL
);

CREATE TABLE audit
(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    club_id INTEGER NOT NULL,
    auditor_id INTEGER NOT NULL,
    startdate datetime NOT NULL,
    status_id INTEGER DEFAULT 1 NOT NULL,
    discipline_id INTEGER NOT NULL,
    CONSTRAINT audit_club_ID_fk FOREIGN KEY (club_id) REFERENCES club (ID),
    CONSTRAINT audit_user_ID_fk FOREIGN KEY (auditor_id) REFERENCES user (ID),
    CONSTRAINT audit_status_id_fk FOREIGN KEY (status_id) REFERENCES audit_status (id),
    CONSTRAINT audit_discipline_id_fk FOREIGN KEY (discipline_id) REFERENCES discipline (id)
);
CREATE INDEX audit_club_ID_fk ON audit (club_id);
CREATE INDEX audit_user_ID_fk ON audit (auditor_id);
CREATE INDEX audit_status_id_fk ON audit (status_id);
CREATE INDEX audit_discipline_id_fk ON audit (discipline_id);

CREATE TABLE audit_user
(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    audit_id INTEGER,
    user_id INTEGER NOT NULL,
    grading_id INTEGER NOT NULL,
    CONSTRAINT audit_user_audit_id_fk FOREIGN KEY (audit_id) REFERENCES audit (id),
    CONSTRAINT audit_user_user_ID_fk FOREIGN KEY (user_id) REFERENCES user (ID),
    CONSTRAINT audit_user_grading_ID_fk FOREIGN KEY (grading_id) REFERENCES grading (ID)
);
CREATE INDEX audit_user_audit_id_fk ON audit_user (audit_id);
CREATE INDEX audit_user_user_ID_fk ON audit_user (user_id);
CREATE INDEX audit_user_grading_ID_fk ON audit_user (grading_id);
