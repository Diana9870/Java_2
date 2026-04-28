package org.example;

public class Main {
    public static void main(String[] args) {
        DatabaseQueryService service = new DatabaseQueryService();

        System.out.println("MAX PROJECT CLIENT:");
        service.findMaxProjectsClient().forEach(System.out::println);

        System.out.println("\nALL CLIENTS:");
        service.findAllClients().forEach(System.out::println);
    }
}