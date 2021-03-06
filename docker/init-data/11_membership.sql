CREATE TABLE MEMBERSHIP (
  ID        INTEGER              NOT NULL PRIMARY KEY AUTO_INCREMENT,
  BEGINDATE DATE                 NOT NULL,
  ENDDATE   DATE,
  MAINCLUB  BOOLEAN DEFAULT TRUE NOT NULL,
  USER_ID   INTEGER              NOT NULL,
  CLUB_ID   INTEGER              NOT NULL,
  CONSTRAINT MEMBERSHIP_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES USER (ID),
  CONSTRAINT MEMBERSHIP_CLUB_ID_FK FOREIGN KEY (CLUB_ID) REFERENCES CLUB (ID)
);
