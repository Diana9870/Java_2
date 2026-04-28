CREATE TABLE client (
                        id IDENTITY PRIMARY KEY,
                        name VARCHAR(100) NOT NULL
);

CREATE TABLE project (
                         id IDENTITY PRIMARY KEY,
                         client_id INT,
                         FOREIGN KEY (client_id) REFERENCES client(id)
);