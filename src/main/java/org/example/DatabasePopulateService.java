package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DatabasePopulateService {

    public static void main(String[] args) {
        try (
                InputStream is = DatabasePopulateService.class
                        .getClassLoader()
                        .getResourceAsStream("sql/populate_db.sql");

                Connection conn = Database.getInstance().getConnection();
                Statement statement = conn.createStatement();
        ) {
            if (is == null) {
                throw new RuntimeException("SQL file not found!");
            }

            String sql = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            String[] queries = sql.split(";");

            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.execute(query.trim());
                }
            }

            System.out.println("Database populated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}