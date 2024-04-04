package com.rect2m.staionerydb.entity;

import java.util.Date;

public class Order {
    private int id;
    private Date date;
    private int productId;
    private int quantity;

    public Order() {
    }

    public Order(Date date, int productId, int quantity) {
        this.date = date;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

