package bp.model;

import java.time.LocalDate;

public class MonthlyExpensesAndIncomeType extends MonthlyExpensesType {
    private double income;

    public MonthlyExpensesAndIncomeType() {
    }

    public MonthlyExpensesAndIncomeType(LocalDate date, double expenses, double income) {
        super(date, expenses);
        this.income = income;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
