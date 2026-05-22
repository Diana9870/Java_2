package org.example.model;

public class AvgSalaryByLevel {

    private String level;
    private double avgSalary;

    public AvgSalaryByLevel() {
    }

    public AvgSalaryByLevel(String level, double avgSalary) {
        this.level = level;
        this.avgSalary = avgSalary;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(double avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "AvgSalaryByLevel{" +
                "level='" + level + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }
}