public class AvgSalary {
    private String level;
    private double avgSalary;

    public AvgSalary(String level, double avgSalary) {
        this.level = level;
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return level + " -> " + avgSalary;
    }
}