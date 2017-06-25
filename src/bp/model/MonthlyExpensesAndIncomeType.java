package bp.model;

import java.time.LocalDate;

public class MonthlyExpensesAndIncomeType extends MonthlyExpensesType {
    private double income;

    public MonthlyExpensesAndIncomeType(LocalDate date) {
        super(date);
    }

    public MonthlyExpensesAndIncomeType(LocalDate date, double expenses, double income) {
        super(date.withDayOfMonth(1), expenses);
        this.income = income;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
