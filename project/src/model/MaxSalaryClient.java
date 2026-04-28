public class MaxSalaryClient {
    private String name;
    private int totalSalary;

    public MaxSalaryClient(String name, int totalSalary) {
        this.name = name;
        this.totalSalary = totalSalary;
    }

    @Override
    public String toString() {
        return name + " -> $" + totalSalary;
    }
}