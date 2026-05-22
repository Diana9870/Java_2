package org.example;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class DatabasePopulateService {

    private static final String POPULATE_DB_FILE = "sql/populate_db.sql";

    public static void main(String[] args) {
        new DatabasePopulateService().populateDb();
    }

    public void populateDb() {

        String sql = readSqlFile();

        Connection conn = Database.getInstance().getConnection();

        try (Statement statement = conn.createStatement()) {

            String[] queries = sql.split(";");

            for (String query : queries) {

                if (!query.trim().isEmpty()) {
                    statement.execute(query.trim());
                }
            }

            System.out.println("Database populated successfully.");

        } catch (Exception e) {

            System.err.println("Error during database population:");
            e.printStackTrace();
        }
    }

    private String readSqlFile() {

        try (
                InputStream is = DatabasePopulateService.class
                        .getClassLoader()
                        .getResourceAsStream(POPULATE_DB_FILE)
        ) {

            if (is == null) {
                throw new RuntimeException("SQL file not found: " + POPULATE_DB_FILE);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot read SQL file: " + POPULATE_DB_FILE,
                    e
            );
        }
    }
}