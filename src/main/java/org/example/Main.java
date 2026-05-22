package org.example;

public class Main {

    public static void main(String[] args) {

        DatabaseQueryService service = new DatabaseQueryService();

        printSection("MAX PROJECT COUNT CLIENT");
        service.findMaxProjectsCountClient()
                .forEach(System.out::println);

        printSection("ALL CLIENTS");
        service.findAllClients()
                .forEach(System.out::println);

        printSection("CLIENT COUNT");
        System.out.println(service.getClientCount());

        printSection("LONGEST PROJECT");
        service.findLongestProjects()
                .forEach(System.out::println);

        printSection("MAX SALARY CLIENT");
        service.findMaxSalaryClient()
                .forEach(System.out::println);

        printSection("YOUNGEST & ELDEST WORKERS");
        service.findYoungestEldestWorkers()
                .forEach(System.out::println);

        printSection("AVG SALARY BY LEVEL");
        service.findAvgSalaryByLevel()
                .forEach(System.out::println);

        printSection("PROJECT PRICES");
        service.findProjectPrices()
                .forEach(System.out::println);
    }

    private static void printSection(String title) {
        System.out.println();
        System.out.println("======================================");
        System.out.println(title);
        System.out.println("======================================");
    }
}