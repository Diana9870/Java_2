package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitService {

    public static void main(String[] args) {
        try {
            String sql = Files.readString(Paths.get("src/main/resources/sql/init_db.sql"));

            Connection conn = Database.getInstance().getConnection();
            Statement statement = conn.createStatement();
            statement.execute(sql);

            System.out.println("Database initialized successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}