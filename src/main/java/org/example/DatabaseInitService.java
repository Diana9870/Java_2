package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {

    private static final String INIT_DB_FILE = "sql/init_db.sql";

    public static void main(String[] args) {
        new DatabaseInitService().initDb();
    }

    public void initDb() {
        String sql = readSqlFile(INIT_DB_FILE);

        Connection conn = Database.getInstance().getConnection();

        try (Statement statement = conn.createStatement()) {

            statement.execute(sql);

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            System.err.println("Error during database initialization:");
            e.printStackTrace();
        }
    }

    private String readSqlFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot read SQL file: " + filePath,
                    e
            );
        }
    }
}