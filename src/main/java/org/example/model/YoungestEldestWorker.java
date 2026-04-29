package org.example.model;

import java.time.LocalDate;

public class YoungestEldestWorker {

    private String name;
    private LocalDate birthday;

    public YoungestEldestWorker(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "YoungestEldestWorker{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}