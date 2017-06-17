package bp.model;

import java.time.LocalDate;

public class CategoryExpensesType extends MonthlyExpensesType {
    private CategoryType category;

    public CategoryExpensesType(LocalDate date, CategoryType category, double expenses) {
        this.setDate(date);
        this.category = category;
        this.setExpenses(expenses);
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
