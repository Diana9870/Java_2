package org.example.model;

public class ClientCount {

    private long clientCount;

    public ClientCount() {
    }

    public ClientCount(long clientCount) {
        this.clientCount = clientCount;
    }

    public long getClientCount() {
        return clientCount;
    }

    public void setClientCount(long clientCount) {
        this.clientCount = clientCount;
    }

    @Override
    public String toString() {
        return "ClientCount{" +
                "clientCount=" + clientCount +
                '}';
    }
}