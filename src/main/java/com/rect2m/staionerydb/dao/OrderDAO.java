package com.rect2m.staionerydb.dao;

import com.rect2m.staionerydb.entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Order order) throws SQLException {
        String sql = "INSERT INTO orders (date, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(order.getDate().getTime()));
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.executeUpdate();
        }
    }

    public Order findById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setDate(resultSet.getDate("date"));
                    order.setProductId(resultSet.getInt("product_id"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    return order;
                }
            }
        }
        return null;
    }

    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("date"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                orders.add(order);
            }
        }
        return orders;
    }

    public void update(Order order) throws SQLException {
        String sql = "UPDATE orders SET date = ?, product_id = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(order.getDate().getTime()));
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.setInt(4, order.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
