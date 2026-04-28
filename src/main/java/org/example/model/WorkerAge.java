import java.time.LocalDate;

package org.example.model;

public class WorkerAge {
    private String type;
    private String name;
    private LocalDate birthday;

    public WorkerAge(String type, String name, LocalDate birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return type + ": " + name + " (" + birthday + ")";
    }
}