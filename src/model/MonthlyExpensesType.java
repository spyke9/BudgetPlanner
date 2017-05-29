package model;

import java.io.Serializable;
import java.time.LocalDate;

public class MonthlyExpensesType implements Serializable {

    private LocalDate date;
    private double expenses;

    public MonthlyExpensesType() {
    }

    public MonthlyExpensesType(LocalDate date, double expenses) {
        this.date = date;
        this.expenses = expenses;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }
}
