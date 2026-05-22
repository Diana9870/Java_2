package org.example.model;

public class MaxSalaryClient {

    private String name;
    private int salary;

    public MaxSalaryClient() {
    }

    public MaxSalaryClient(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "MaxSalaryClient{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}