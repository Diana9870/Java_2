package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

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

            String sql = new String(is.readAllBytes());
            statement.execute(sql);

            System.out.println("Database populated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}