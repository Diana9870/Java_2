package org.example.model;

public class LongestProject {

    private long id;
    private int monthCount;

    public LongestProject() {
    }

    public LongestProject(long id, int monthCount) {
        this.id = id;
        this.monthCount = monthCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    @Override
    public String toString() {
        return "LongestProject{" +
                "id=" + id +
                ", monthCount=" + monthCount +
                '}';
    }
}