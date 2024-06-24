package com.example.june18.models;

public class ExpenseReport {
    private String category;
    private double total;
    private double avg;
    private double min;
    private double max;

    public ExpenseReport(String category, double total, double avg, double min, double max) {
        this.category = category;
        this.total = total;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public ExpenseReport() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "ExpenseReport{" +
                "category='" + category + '\'' +
                ", total=" + total +
                ", avg=" + avg +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
