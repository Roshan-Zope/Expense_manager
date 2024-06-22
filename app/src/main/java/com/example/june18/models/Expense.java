package com.example.june18.models;

public class Expense {
    private int expenseId;
    private String category;
    private double amount;
    private String note;
    private String date;

    public Expense() {
    }

    public Expense(int expenseId, int categoryId, String category, long amount, String note, String date) {
        this.expenseId = expenseId;
        this.category = category;
        this.amount = amount;
        this.note = note;
        this.date = date;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
