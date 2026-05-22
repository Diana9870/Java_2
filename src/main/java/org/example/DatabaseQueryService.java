package org.example;

import org.example.model.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private static final String SQL_FOLDER = "sql/";

    private final Connection connection =
            Database.getInstance().getConnection();

    public List<Client> findAllClients() {

        List<Client> clients = new ArrayList<>();

        String sql = readSql("find_all_clients.sql");

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Client client = new Client();

                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));

                clients.add(client);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch clients",
                    e
            );
        }

        return clients;
    }

    public long getClientCount() {

        String sql = readSql("find_client_count.sql");

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getLong("client_count");
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch client count",
                    e
            );
        }

        return 0;
    }

    public List<LongestProject> findLongestProjects() {

        List<LongestProject> result = new ArrayList<>();

        String sql = readSql("find_longest_projects.sql");

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                LongestProject project = new LongestProject();

                project.setId(rs.getLong("id"));
                project.setMonthCount(rs.getInt("month_count"));

                result.add(project);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch longest projects",
                    e
            );
        }

        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsCountClient() {

        List<MaxProjectCountClient> result = new ArrayList<>();

        String sql = readSql("find_max_projects_client.sql");

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                MaxProjectCountClient client =
                        new MaxProjectCountClient();

                client.setName(rs.getString("name"));
                client.setProjectCount(
                        rs.getInt("project_count")
                );

                result.add(client);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch max projects client",
                    e
            );
        }

        return result;
    }

    public List<MaxSalaryClient> findMaxSalaryClient() {

        List<MaxSalaryClient> result = new ArrayList<>();

        String sql = readSql("find_max_salary_worker.sql");

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                MaxSalaryClient worker =
                        new MaxSalaryClient();

                worker.setName(rs.getString("name"));
                worker.setSalary(rs.getInt("salary"));

                result.add(worker);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch max salary worker",
                    e
            );
        }

        return result;
    }

    public List<WorkerAge> findYoungestEldestWorkers() {

        List<WorkerAge> result = new ArrayList<>();

        String sql = readSql(
                "find_youngest_eldest_workers.sql"
        );

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                WorkerAge worker = new WorkerAge();

                worker.setType(rs.getString("type"));
                worker.setName(rs.getString("name"));
                worker.setBirthday(
                        rs.getDate("birthday").toLocalDate()
                );

                result.add(worker);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch youngest/eldest workers",
                    e
            );
        }

        return result;
    }

    public List<AvgSalaryByLevel> findAvgSalaryByLevel() {

        List<AvgSalaryByLevel> result = new ArrayList<>();

        String sql = readSql("find_avg_salary_by_level.sql");

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                AvgSalaryByLevel avg =
                        new AvgSalaryByLevel();

                avg.setLevel(rs.getString("level"));
                avg.setAvgSalary(
                        rs.getDouble("avg_salary")
                );

                result.add(avg);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch average salaries",
                    e
            );
        }

        return result;
    }

    public List<ProjectPrice> findProjectPrices() {

        List<ProjectPrice> result = new ArrayList<>();

        String sql = readSql("print_project_prices.sql");

        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                ProjectPrice projectPrice =
                        new ProjectPrice();

                projectPrice.setProjectId(
                        rs.getLong("project_id")
                );

                projectPrice.setPrice(
                        rs.getLong("price")
                );

                result.add(projectPrice);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot fetch project prices",
                    e
            );
        }

        return result;
    }

    private String readSql(String fileName) {

        try (
                InputStream is = getClass()
                        .getClassLoader()
                        .getResourceAsStream(
                                SQL_FOLDER + fileName
                        )
        ) {

            if (is == null) {

                throw new RuntimeException(
                        "SQL file not found: " + fileName
                );
            }

            return new String(
                    is.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot read SQL file: " + fileName,
                    e
            );
        }
    }
}