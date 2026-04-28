package org.example.model;

public class LongestProject {
    private String name;
    private int maxDuration;

    public LongestProject(String name, int maxDuration) {
        this.name = name;
        this.maxDuration = maxDuration;
    }

    @Override
    public String toString() {
        return name + " -> " + maxDuration + " months";
    }
}