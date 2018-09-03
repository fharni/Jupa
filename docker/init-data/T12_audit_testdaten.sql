INSERT INTO audit (id, club_id, auditor_id, startdate, status_id, discipline_id) VALUES (1, 1, 1, '2018-07-04 20:30:00', 1, 1);
INSERT INTO audit (id, club_id, auditor_id, startdate, status_id, discipline_id) VALUES (2, 1, 4, '2018-07-07 08:30:00', 2, 1);
INSERT INTO audit (id, club_id, auditor_id, startdate, status_id, discipline_id) VALUES (3, 2, 2, '2018-03-10 12:30:00', 1, 1);
INSERT INTO audit (id, club_id, auditor_id, startdate, status_id, discipline_id) VALUES (4, 2, 5, '2018-07-07 09:00:00', 2, 1);
INSERT INTO audit (id, club_id, auditor_id, startdate, status_id, discipline_id) VALUES (5, 3, 4, '2018-06-30 10:00:00', 1, 1);
INSERT INTO audit (id, club_id, auditor_id, startdate, status_id, discipline_id) VALUES (6, 1, 1, '2018-09-29 08:00:00', 3, 1);

INSERT INTO audit_user (id, audit_id, user_id, grading_id) VALUES (1, 6, 16, 3);
INSERT INTO audit_user (id, audit_id, user_id, grading_id) VALUES (2, 6, 15, 3);
INSERT INTO audit_user (id, audit_id, user_id, grading_id) VALUES (3, 6, 11, 8);
INSERT INTO audit_user (id, audit_id, user_id, grading_id) VALUES (4, 6, 8, 3);
