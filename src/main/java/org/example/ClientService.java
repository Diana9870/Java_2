package org.example;

import org.example.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 1000;

    private final Connection connection = Database
            .getInstance()
            .getConnection();

    public long create(String name) {
        validateName(name);

        String sql = "INSERT INTO client(name) VALUES(?)";

        try (PreparedStatement statement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
        )) {

            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                return keys.getLong(1);
            }

            throw new RuntimeException("Cannot get client id");

        } catch (SQLException e) {
            throw new RuntimeException("Create client error", e);
        }
    }

    public String getById(long id) {

        String sql = "SELECT name FROM client WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }

            throw new RuntimeException("Client not found");

        } catch (SQLException e) {
            throw new RuntimeException("Get client error", e);
        }
    }

    public void setName(long id, String name) {
        validateName(name);

        String sql = "UPDATE client SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setLong(2, id);

            int updated = statement.executeUpdate();

            if (updated == 0) {
                throw new RuntimeException("Client not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Update client error", e);
        }
    }

    public void deleteById(long id) {

        String sql = "DELETE FROM client WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int deleted = statement.executeUpdate();

            if (deleted == 0) {
                throw new RuntimeException("Client not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Delete client error", e);
        }
    }

    public List<Client> listAll() {

        List<Client> clients = new ArrayList<>();

        String sql = "SELECT id, name FROM client";

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));

                clients.add(client);
            }

            return clients;

        } catch (SQLException e) {
            throw new RuntimeException("List clients error", e);
        }
    }

    private void validateName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        String trimmed = name.trim();

        if (trimmed.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("Name too short");
        }

        if (trimmed.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name too long");
        }
    }
}