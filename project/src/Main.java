package org.example;

public class Main {
    public static void main(String[] args) {
        DatabaseQueryService service = new DatabaseQueryService();

        System.out.println(service.findMaxProjectsClient());
    }
}