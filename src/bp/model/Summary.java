package bp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Summary implements Serializable {
    private LocalDate date;
    private Map<CategoryType, CategoryExpensesType> categoryExpensesMap;
    private MonthlyExpensesAndIncomeType expensesAndIncome;

    public Summary(LocalDate date) {
        this.date = date;
        this.categoryExpensesMap = new HashMap<>();
        this.expensesAndIncome = new MonthlyExpensesAndIncomeType(date);
    }

    public Summary(LocalDate date, Map<CategoryType, CategoryExpensesType> expenses, MonthlyExpensesAndIncomeType expensesAndIncome) {
        this.date = date;
        this.categoryExpensesMap = expenses;
        this.expensesAndIncome = expensesAndIncome;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<CategoryType, CategoryExpensesType> getCategoryExpensesMap() {
        return categoryExpensesMap;
    }

    public void setCategoryExpensesMap(Map<CategoryType, CategoryExpensesType> categoryExpensesMap) {
        this.categoryExpensesMap = categoryExpensesMap;
    }

    public MonthlyExpensesAndIncomeType getExpensesAndIncome() {
        return expensesAndIncome;
    }

    public void setExpensesAndIncome(MonthlyExpensesAndIncomeType expensesAndIncome) {
        this.expensesAndIncome = expensesAndIncome;
    }

    public void addExpense(CategoryExpensesType categoryExpensesType) {
        categoryExpensesMap.put(categoryExpensesType.getCategory(), categoryExpensesType);
    }
}
