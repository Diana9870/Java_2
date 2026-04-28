package org.example;

public class Main {
    public static void main(String[] args) {
        var service = new DatabaseQueryService();

        System.out.println("MAX PROJECT CLIENT:");
        service.findMaxProjectsClient().forEach(System.out::println);

        System.out.println("\nALL CLIENTS:");
        service.findAllClients().forEach(System.out::println);

        System.out.println("\nCOUNT:");
        System.out.println(service.getClientCount());

        System.out.println("\nLONGEST PROJECT:");
        service.findLongestProjects().forEach(System.out::println);

        System.out.println("\nMAX SALARY CLIENT:");
        service.findMaxSalaryClient().forEach(System.out::println);

        System.out.println("\nYOUNGEST & ELDEST:");
        service.findYoungestAndEldestWorkers().forEach(System.out::println);

        System.out.println("\nAVG SALARY:");
        service.findAvgSalaryByLevel().forEach(System.out::println);
    }
}