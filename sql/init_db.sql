CREATE TABLE client (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(100) NOT NULL
);

CREATE TABLE worker (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(100) NOT NULL,
                        BIRTHDAY DATE NOT NULL,
                        LEVEL VARCHAR(20),
                        SALARY INT NOT NULL
);

CREATE TABLE project (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         CLIENT_ID INT NOT NULL,
                         START_DATE DATE NOT NULL,
                         FINISH_DATE DATE NOT NULL,
                         CONSTRAINT fk_project_client
                             FOREIGN KEY (CLIENT_ID) REFERENCES client(ID)
);

CREATE TABLE project_worker (
                                PROJECT_ID INT NOT NULL,
                                WORKER_ID INT NOT NULL,
                                PRIMARY KEY (PROJECT_ID, WORKER_ID),
                                CONSTRAINT fk_pw_project
                                    FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
                                CONSTRAINT fk_pw_worker
                                    FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);