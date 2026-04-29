package your.package.name;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private String readSql(String fileName) {
        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("sql/" + fileName);

            if (is == null) {
                throw new RuntimeException("SQL file not found: " + fileName);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Error reading SQL file", e);
        }
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sql = readSql("find_max_projects_client.sql");

        List<MaxProjectCountClient> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(rs.getString("name"));
                client.setProjectCount(rs.getInt("project_count"));
                result.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<LongestProject> findLongestProject() {
        String sql = readSql("find_longest_project.sql");

        List<LongestProject> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LongestProject project = new LongestProject();
                project.setName(rs.getString("name"));
                project.setMonthCount(rs.getInt("month_count"));
                result.add(project);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        String sql = readSql("find_max_salary_worker.sql");

        List<MaxSalaryWorker> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaxSalaryWorker worker = new MaxSalaryWorker();
                worker.setName(rs.getString("name"));
                worker.setSalary(rs.getInt("salary"));
                result.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        String sql = readSql("find_youngest_eldest_workers.sql");

        List<YoungestEldestWorker> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                YoungestEldestWorker worker = new YoungestEldestWorker();
                worker.setType(rs.getString("type"));
                worker.setName(rs.getString("name"));
                worker.setBirthday(rs.getDate("birthday").toLocalDate());
                result.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<ProjectPrice> printProjectPrices() {
        String sql = readSql("print_project_prices.sql");

        List<ProjectPrice> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProjectPrice price = new ProjectPrice();
                price.setProjectId(rs.getInt("project_id"));
                price.setPrice(rs.getInt("price"));
                result.add(price);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}