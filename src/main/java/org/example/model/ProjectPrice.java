package org.example.model;

public class ProjectPrice {

    private long projectId;
    private long price;

    public ProjectPrice() {
    }

    public ProjectPrice(long projectId, long price) {
        this.projectId = projectId;
        this.price = price;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProjectPrice{" +
                "projectId=" + projectId +
                ", price=" + price +
                '}';
    }
}