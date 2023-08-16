INSERT INTO "user" (username, email, password)
VALUES ('tanjona', 'tanjona@tanjona', '1234'),
       ('john5', 'john@doe', '1234'),
       ('mirado', 'mirado@gmail.com', 'azerty'),
       ('jane', 'jane@doe', 'qsdf'),
       ('kiky', 'kiky@yahoo', '0000');

INSERT INTO "project" (title, id_user)
VALUES ('Tanjona''s project', 1),
        ('Create Jillo-API', 3);

INSERT INTO "status" (name)
VALUES ('Draft'),
       ('Planned'),
       ('In progress'),
       ('Done'),
       ('Paused');

INSERT INTO "task" (title, deadline, id_user, id_project, id_status)
VALUES ('Create user CRUD', '2023-08-15 00:00:00', 3, 2, 4),
       ('Create project CRUD', '2023-08-16 00:00:00', 3, 2, 4),
       ('Create status CRUD', '2023-08-17 00:00:00', 3, 2, 3),
       ('Create task CRUD', null, 3, 2, 2),
       ('Create authentication with Spring Security', null, 3, 2, 1),
       ('Install Ubuntu', '2023-08-16 12:00:00', 1, 1, 4),
       ('Install BBB on the Ubuntu', '2023-08-17 00:00:00', 1, 1, 2),
       ('Test the chat', null, 1, 1, 1);

INSERT INTO "to_be_in" (id_user, id_project)
VALUES (1, 1),
       (3, 2),
       (1, 2),
       (2, 2),
       (5, 2),
       (4, 1),
       (3, 1);

INSERT INTO "to_be_assigned_to" (id_user, id_task)
VALUES (1,1),
       (1,2),
       (1,3),
       (1,4),
       (5,1),
       (5,2),
       (3,4),
       (3,5),
       (2,5),
       (1,6),
       (1,7),
       (1,8),
       (3,7),
       (3,8),
       (4,6);