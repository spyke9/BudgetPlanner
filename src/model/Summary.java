package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Summary implements Serializable {
    private LocalDate date;
    private Map<CategoryType, CategoryExpensesType> expenses;
    int income;

    public Map<CategoryType, CategoryExpensesType> getExpenses() {
        return expenses;
    }

    public void setExpenses(Map<CategoryType, CategoryExpensesType> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(CategoryExpensesType expense) {
        this.expenses.putIfAbsent(expense.getCategory(), expense);
    }
}
