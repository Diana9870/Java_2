INSERT INTO client (NAME) VALUES
                              ('Google'),
                              ('Microsoft'),
                              ('Amazon'),
                              ('Meta'),
                              ('Netflix');

INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
                                                       ('John', '1990-05-10', 'Senior', 7000),
                                                       ('Anna', '1998-03-12', 'Middle', 4500),
                                                       ('Mike', '1985-11-20', 'Senior', 8000),
                                                       ('Sara', '2000-07-01', 'Junior', 2500),
                                                       ('David', '1995-01-17', 'Middle', 5000);

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
                                                             (1, '2022-01-01', '2022-06-01'),
                                                             (2, '2022-02-01', '2022-12-01'),
                                                             (1, '2023-01-01', '2023-08-01'),
                                                             (3, '2023-03-01', '2023-09-01');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
                                                       (1,1),
                                                       (1,2),
                                                       (2,3),
                                                       (2,4),
                                                       (3,1),
                                                       (3,5),
                                                       (4,2),
                                                       (4,3);