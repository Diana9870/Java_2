package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try {
            String sql = Files.readString(Paths.get("sql/populate_db.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql)) {
                ps.execute();
            }

            System.out.println("DB populated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}