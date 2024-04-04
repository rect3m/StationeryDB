package com.rect2m.staionerydb.dao;

import com.rect2m.staionerydb.entity.Manufacturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAO {
    private Connection connection;

    public ManufacturerDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Manufacturer manufacturer) throws SQLException {
        String sql = "INSERT INTO manufacturers (name, country) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
        }
    }

    public Manufacturer findById(int id) throws SQLException {
        String sql = "SELECT * FROM manufacturers WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setId(resultSet.getInt("id"));
                    manufacturer.setName(resultSet.getString("name"));
                    manufacturer.setCountry(resultSet.getString("country"));
                    return manufacturer;
                }
            }
        }
        return null;
    }

    public List<Manufacturer> findAll() throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String sql = "SELECT * FROM manufacturers";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(resultSet.getInt("id"));
                manufacturer.setName(resultSet.getString("name"));
                manufacturer.setCountry(resultSet.getString("country"));
                manufacturers.add(manufacturer);
            }
        }
        return manufacturers;
    }

    public void update(Manufacturer manufacturer) throws SQLException {
        String sql = "UPDATE manufacturers SET name = ?, country = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setInt(3, manufacturer.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM manufacturers WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
