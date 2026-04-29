package org.example.model;

public class ProjectPrice {

    private int id;
    private long price;

    public ProjectPrice(int id, long price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProjectPrice{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}