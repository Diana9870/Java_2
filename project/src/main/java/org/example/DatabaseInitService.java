package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {
    public static void main(String[] args) {
        try {
            String sql = Files.readString(Paths.get("sql/init_db.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql)) {
                ps.execute();
            }

            System.out.println("DB initialized");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}