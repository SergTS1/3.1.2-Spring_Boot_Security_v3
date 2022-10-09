USE users;

CREATE TABLE users (
                       id bigint NOT NULL AUTO_INCREMENT,
                       name varchar(15),
                       surname varchar(25),
                       age int,
                       username varchar(25),
                       password varchar(255),
                       PRIMARY KEY (id)
) ;

INSERT INTO users.users (name, surname, age, username, password)
VALUES
    ('Sergey', 'Zakirov', 34, 'root', '$2a$12$U3U.D4crbPVcziW1j7l7oOX81CP6fI2WYRvgGymG19T5KwJKR6MqC'),
    ('Oleg', 'Ivanov', 40, 'user', '$2a$12$p11wYQmduw71L4KPdmOYluMBjtI3VP5vDuy7jHusTeK2Fw.pWngxq');

CREATE TABLE roles (
                       id bigint NOT NULL AUTO_INCREMENT,
                       name varchar(15),
                       PRIMARY KEY (id)
) ;

INSERT INTO users.roles (name)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');


CREATE TABLE users_roles (
                             users_id bigint NOT NULL,
                             roles_id bigint NOT NULL,
                             PRIMARY KEY (users_id, roles_id),
                             FOREIGN KEY (users_id) REFERENCES users.users(id),
                             FOREIGN KEY (roles_id) REFERENCES users.roles(id));

INSERT INTO users_roles VALUES(1, 2);
INSERT INTO users_roles VALUES(2, 1);