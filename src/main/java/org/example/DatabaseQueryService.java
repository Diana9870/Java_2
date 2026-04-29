package org.example;

import org.example.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sql = """
                SELECT c.NAME, COUNT(p.ID) AS project_count
                FROM client c
                JOIN project p ON c.ID = p.CLIENT_ID
                GROUP BY c.NAME
                ORDER BY project_count DESC
                LIMIT 1
                """;

        List<MaxProjectCountClient> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new MaxProjectCountClient(
                        rs.getString("NAME"),
                        rs.getInt("project_count")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        String sql = """
                SELECT NAME, BIRTHDAY
                FROM worker
                WHERE BIRTHDAY = (SELECT MIN(BIRTHDAY) FROM worker)
                   OR BIRTHDAY = (SELECT MAX(BIRTHDAY) FROM worker)
                """;

        List<YoungestEldestWorker> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new YoungestEldestWorker(
                        rs.getString("NAME"),
                        rs.getDate("BIRTHDAY").toLocalDate()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        String sql = """
                SELECT NAME, SALARY
                FROM worker
                WHERE SALARY = (SELECT MAX(SALARY) FROM worker)
                """;

        List<MaxSalaryWorker> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new MaxSalaryWorker(
                        rs.getString("NAME"),
                        rs.getInt("SALARY")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<ProjectPrice> findProjectPrices() {
        String sql = """
                SELECT p.ID,
                DATEDIFF('MONTH', p.START_DATE, p.FINISH_DATE) * SUM(w.SALARY) AS PRICE
                FROM project p
                JOIN project_worker pw ON p.ID = pw.PROJECT_ID
                JOIN worker w ON pw.WORKER_ID = w.ID
                GROUP BY p.ID, p.START_DATE, p.FINISH_DATE
                """;

        List<ProjectPrice> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new ProjectPrice(
                        rs.getInt("ID"),
                        rs.getLong("PRICE")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}