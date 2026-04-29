package org.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitService {

    public static void main(String[] args) {
        try (
                InputStream is = DatabaseInitService.class
                        .getClassLoader()
                        .getResourceAsStream("sql/init_db.sql");

                Connection conn = Database.getInstance().getConnection();
                Statement statement = conn.createStatement();
        ) {

            if (is == null) {
                throw new RuntimeException("SQL file not found: sql/init_db.sql");
            }

            String sql = new String(is.readAllBytes());

            String[] queries = sql.split(";");

            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.execute(query);
                }
            }

            System.out.println("Database initialized successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}