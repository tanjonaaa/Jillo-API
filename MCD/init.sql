DROP DATABASE IF EXISTS jillo;

CREATE DATABASE jillo;

\c jillo;

CREATE TABLE IF NOT EXISTS public.user(
                            id         SERIAL NOT NULL ,
                            username   VARCHAR (100) NOT NULL ,
                            email      VARCHAR (100) NOT NULL ,
                            password   VARCHAR (100) NOT NULL  ,
                            CONSTRAINT user_PK PRIMARY KEY (id)
)WITHOUT OIDS;


CREATE TABLE IF NOT EXISTS public.project(
                               id            SERIAL NOT NULL ,
                               title         VARCHAR (255) NOT NULL ,
                               description   VARCHAR (2000)   ,
                               id_user       INT  NOT NULL  ,
                               CONSTRAINT project_PK PRIMARY KEY (id)

    ,CONSTRAINT project_user_FK FOREIGN KEY (id_user) REFERENCES public.user(id) ON DELETE CASCADE
)WITHOUT OIDS;


CREATE TABLE IF NOT EXISTS public.status(
                              id     SERIAL NOT NULL ,
                              name   VARCHAR (255) NOT NULL  ,
                              CONSTRAINT status_PK PRIMARY KEY (id)
)WITHOUT OIDS;


CREATE TABLE IF NOT EXISTS public.task(
                            id            SERIAL NOT NULL ,
                            title         VARCHAR (255) NOT NULL ,
                            description   VARCHAR (2000)   ,
                            deadline      TIMESTAMP   ,
                            id_project    INT  NOT NULL ,
                            id_status     INT  NOT NULL ,
                            id_user       INT  NOT NULL  ,
                            CONSTRAINT task_PK PRIMARY KEY (id)

    ,CONSTRAINT task_project_FK FOREIGN KEY (id_project) REFERENCES public.project(id) ON DELETE CASCADE
    ,CONSTRAINT task_status0_FK FOREIGN KEY (id_status) REFERENCES public.status(id) ON DELETE CASCADE
    ,CONSTRAINT task_user1_FK FOREIGN KEY (id_user) REFERENCES public.user(id) ON DELETE CASCADE
)WITHOUT OIDS;


CREATE TABLE IF NOT EXISTS public.to_be_in(
                                id_user      INT  NOT NULL ,
                                id_project   INT  NOT NULL ,
                                id           SERIAL  NOT NULL ,
                                created_at   TIMESTAMP  NOT NULL  DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT to_be_in_PK PRIMARY KEY (id)

    ,CONSTRAINT to_be_in_user_FK FOREIGN KEY (id_user) REFERENCES public.user(id) ON DELETE CASCADE
    ,CONSTRAINT to_be_in_project0_FK FOREIGN KEY (id_project) REFERENCES public.project(id) ON DELETE CASCADE
)WITHOUT OIDS;


CREATE TABLE IF NOT EXISTS public.to_be_assigned_to(
                                         id_task      INT  NOT NULL ,
                                         id_user      INT  NOT NULL ,
                                         id           SERIAL  NOT NULL ,
                                         created_at   TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT to_be_assigned_to_PK PRIMARY KEY (id)

    ,CONSTRAINT to_be_assigned_to_task_FK FOREIGN KEY (id_task) REFERENCES public.task(id) ON DELETE CASCADE
    ,CONSTRAINT to_be_assigned_to_user0_FK FOREIGN KEY (id_user) REFERENCES public.user(id) ON DELETE CASCADE
)WITHOUT OIDS;