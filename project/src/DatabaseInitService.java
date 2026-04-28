package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        try {
            String sql = Files.readString(Paths.get("sql/init_db.sql"));

            try (Connection conn = Database.getInstance().getConnection();
                 Statement stmt = conn.createStatement()) {

                stmt.execute(sql);
            }

            System.out.println("DB initialized");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}