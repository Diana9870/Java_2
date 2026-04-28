package org.example.model;

public class ClientCount {
    private int total;

    public ClientCount(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Total clients: " + total;
    }
}