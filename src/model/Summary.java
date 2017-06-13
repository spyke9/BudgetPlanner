package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class Summary implements Serializable {
    private LocalDate date;
    private Map<CategoryType, CategoryExpensesType> expenses;
    private double income;

    public Summary(LocalDate date, double income) {
        this.date = date;
        this.income = income;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<CategoryType, CategoryExpensesType> getExpenses() {
        return expenses;
    }

    public void setExpenses(Map<CategoryType, CategoryExpensesType> expenses) {
        this.expenses = expenses;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void addExpense(CategoryExpensesType categoryExpensesType) {
        expenses.put(categoryExpensesType.getCategory(), categoryExpensesType);
    }
}
