package org.example;

import org.example.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<Client> findAllClients() {

        List<Client> clients = new ArrayList<>();

        String sql = """
                SELECT id, name
                FROM client
                ORDER BY id
                """;

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Client client = new Client();

                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));

                clients.add(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }

    public long getClientCount() {

        String sql = "SELECT COUNT(*) AS client_count FROM client";

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getLong("client_count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<LongestProject> findLongestProjects() {

        List<LongestProject> projects = new ArrayList<>();

        String sql = """
                SELECT p.id,
                       DATEDIFF('MONTH', p.start_date, p.finish_date) AS month_count
                FROM project p
                ORDER BY month_count DESC
                LIMIT 1
                """;

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                LongestProject project = new LongestProject();

                project.setId(rs.getLong("id"));
                project.setMonthCount(rs.getInt("month_count"));

                projects.add(project);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }

    public List<MaxProjectCountClient> findMaxProjectsCountClient() {

        List<MaxProjectCountClient> result = new ArrayList<>();

        String sql = """
                SELECT c.name,
                       COUNT(p.id) AS project_count
                FROM client c
                JOIN project p ON c.id = p.client_id
                GROUP BY c.name
                ORDER BY project_count DESC
                LIMIT 1
                """;

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                MaxProjectCountClient client = new MaxProjectCountClient();

                client.setName(rs.getString("name"));
                client.setProjectCount(rs.getInt("project_count"));

                result.add(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<MaxSalaryClient> findMaxSalaryClient() {

        List<MaxSalaryClient> result = new ArrayList<>();

        String sql = """
                SELECT name, salary
                FROM worker
                ORDER BY salary DESC
                LIMIT 1
                """;

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                MaxSalaryClient worker = new MaxSalaryClient();

                worker.setName(rs.getString("name"));
                worker.setSalary(rs.getInt("salary"));

                result.add(worker);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<WorkerAge> findYoungestEldestWorkers() {

        List<WorkerAge> result = new ArrayList<>();

        String sql = """
                SELECT 'YOUNGEST' AS type, name, birthday
                FROM worker
                WHERE birthday = (SELECT MAX(birthday) FROM worker)

                UNION

                SELECT 'ELDEST' AS type, name, birthday
                FROM worker
                WHERE birthday = (SELECT MIN(birthday) FROM worker)
                """;

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                WorkerAge worker = new WorkerAge();

                worker.setType(rs.getString("type"));
                worker.setName(rs.getString("name"));
                worker.setBirthday(rs.getDate("birthday").toLocalDate());

                result.add(worker);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<AvgSalaryByLevel> findAvgSalaryByLevel() {

        List<AvgSalaryByLevel> result = new ArrayList<>();

        String sql = """
                SELECT level,
                       AVG(salary) AS avg_salary
                FROM worker
                GROUP BY level
                ORDER BY avg_salary DESC
                """;

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                AvgSalaryByLevel avg = new AvgSalaryByLevel();

                avg.setLevel(rs.getString("level"));
                avg.setAvgSalary(rs.getDouble("avg_salary"));

                result.add(avg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<ProjectPrice> findProjectPrices() {

        List<ProjectPrice> result = new ArrayList<>();

        String sql = """
                SELECT p.id AS project_id,
                       SUM(w.salary) AS price
                FROM project p
                JOIN project_worker pw ON p.id = pw.project_id
                JOIN worker w ON pw.worker_id = w.id
                GROUP BY p.id
                ORDER BY price DESC
                """;

        try (
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                ProjectPrice projectPrice = new ProjectPrice();

                projectPrice.setProjectId(rs.getLong("project_id"));
                projectPrice.setPrice(rs.getLong("price"));

                result.add(projectPrice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}