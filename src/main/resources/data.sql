DROP TABLE IF EXISTS todo;

CREATE TABLE todo (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  completed boolean NOT NULL
);


insert into todo (id, title, completed) values (10, 'title1', false);
insert into todo (id, title, completed) values (11, 'title2', false);
insert into todo (id, title, completed) values (12, 'title3', false);
