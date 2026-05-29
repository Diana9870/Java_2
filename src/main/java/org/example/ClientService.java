package org.example;

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