DROP TABLE roles_function;

CREATE TABLE roles_function (
  id       INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  role     VARCHAR(255) NOT NULL,
  function VARCHAR(255) NOT NULL
);

INSERT INTO roles_function (role, function) VALUES ('club_chief', 'menu.club.members');
