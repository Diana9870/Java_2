package org.example.service;

import org.example.Database;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseQueryService {

    private String readSql(String fileName) {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("sql/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            return reader.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to read SQL file: " + fileName, e);
        }
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sql = readSql("find_max_projects_client.sql");
        List<MaxProjectCountClient> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    MaxProjectCountClient client = new MaxProjectCountClient();
                    client.setName(rs.getString("name"));
                    client.setProjectCount(rs.getInt("project_count"));
                    result.add(client);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<LongestProject> findLongestProject() {
        String sql = readSql("find_longest_project.sql");
        List<LongestProject> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    LongestProject project = new LongestProject();
                    project.setName(rs.getString("name"));
                    project.setMonthCount(rs.getInt("month_count"));
                    result.add(project);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<MaxSalaryClient> findMaxSalaryClient() {
        String sql = readSql("find_max_salary_client.sql");
        List<MaxSalaryClient> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    MaxSalaryClient client = new MaxSalaryClient();
                    client.setName(rs.getString("name"));
                    client.setSalary(rs.getInt("salary"));
                    result.add(client);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        String sql = readSql("find_youngest_eldest_workers.sql");
        List<YoungestEldestWorker> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    YoungestEldestWorker worker = new YoungestEldestWorker();
                    worker.setType(rs.getString("type"));
                    worker.setName(rs.getString("name"));
                    worker.setBirthday(rs.getDate("birthday").toLocalDate());
                    result.add(worker);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<ProjectPrice> printProjectPrices() {
        String sql = readSql("print_project_prices.sql");
        List<ProjectPrice> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    ProjectPrice price = new ProjectPrice();
                    price.setProjectId(rs.getLong("project_id"));
                    price.setPrice(rs.getBigDecimal("price"));
                    result.add(price);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<ClientCount> countClients() {
        String sql = readSql("count_clients.sql");
        List<ClientCount> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    ClientCount cc = new ClientCount();
                    cc.setName(rs.getString("name"));
                    cc.setClientCount(rs.getInt("client_count"));
                    result.add(cc);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<AvgSalaryByLevel> findAvgSalaryByLevel() {
        String sql = readSql("find_avg_salary_by_level.sql");
        List<AvgSalaryByLevel> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    AvgSalaryByLevel avg = new AvgSalaryByLevel();
                    avg.setLevel(rs.getString("level"));
                    avg.setAvgSalary(rs.getDouble("avg_salary"));
                    result.add(avg);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<Client> findAllClients() {
        String sql = readSql("find_all_clients.sql");
        List<Client> result = new ArrayList<>();

        try {
            Connection conn = Database.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getLong("id"));
                    client.setName(rs.getString("name"));
                    result.add(client);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}