package org.example;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final Database INSTANCE = new Database();
    private static final String URL = "jdbc:h2:./test";

    private Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL);

            Flyway flyway = Flyway.configure()
                    .dataSource(URL, null, null)
                    .load();

            flyway.migrate();

        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}