package org.example;

import org.example.model.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try {
            String sql = Files.readString(
                    Paths.get("sql/find_max_projects_client.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql);
                 var rs = ps.executeQuery()) {

                while (rs.next()) {
                    result.add(new MaxProjectCountClient(
                            rs.getString("name"),
                            rs.getInt("project_count")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Client> findAllClients() {
        List<Client> result = new ArrayList<>();

        try {
            String sql = Files.readString(
                    Paths.get("sql/find_all_clients.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql);
                 var rs = ps.executeQuery()) {

                while (rs.next()) {
                    result.add(new Client(
                            rs.getString("name")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ClientCount getClientCount() {
        try {
            String sql = Files.readString(
                    Paths.get("sql/count_clients.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql);
                 var rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new ClientCount(
                            rs.getInt("total")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<LongestProject> findLongestProjects() {
        List<LongestProject> result = new ArrayList<>();

        try {
            String sql = Files.readString(
                    Paths.get("sql/find_longest_project.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql);
                 var rs = ps.executeQuery()) {

                while (rs.next()) {
                    result.add(new LongestProject(
                            rs.getString("name"),
                            rs.getInt("max_duration")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<MaxSalaryClient> findMaxSalaryClient() {
        List<MaxSalaryClient> result = new ArrayList<>();

        try {
            String sql = Files.readString(
                    Paths.get("sql/find_max_salary_client.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql);
                 var rs = ps.executeQuery()) {

                while (rs.next()) {
                    result.add(new MaxSalaryClient(
                            rs.getString("name"),
                            rs.getInt("total_salary")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<WorkerAge> findYoungestAndEldestWorkers() {
        List<WorkerAge> result = new ArrayList<>();

        try {
            String sql = Files.readString(
                    Paths.get("sql/find_youngest_eldest_workers.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql);
                 var rs = ps.executeQuery()) {

                while (rs.next()) {
                    result.add(new WorkerAge(
                            rs.getString("type"),
                            rs.getString("name"),
                            rs.getDate("birthday").toLocalDate()
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<AvgSalary> findAvgSalaryByLevel() {
        List<AvgSalary> result = new ArrayList<>();

        try {
            String sql = Files.readString(
                    Paths.get("sql/find_avg_salary_by_level.sql"));

            var conn = Database.getInstance().getConnection();

            try (var ps = conn.prepareStatement(sql);
                 var rs = ps.executeQuery()) {

                while (rs.next()) {
                    result.add(new AvgSalary(
                            rs.getString("level"),
                            rs.getDouble("avg_salary")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}}