package org.example;

import org.example.model.Client;
import org.example.model.MaxProjectCountClient;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try {
            String sql = Files.readString(
                    Paths.get("sql/find_max_projects_client.sql"));

            try (Connection conn = Database.getInstance().getConnection();
                 var ps = conn.prepareStatement(sql);
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

            try (Connection conn = Database.getInstance().getConnection();
                 var ps = conn.prepareStatement(sql);
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
}