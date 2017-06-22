package bp.model;

import java.time.LocalDate;

public class CategoryExpensesType extends MonthlyExpensesType {
    private CategoryType category;

    public CategoryExpensesType(LocalDate date, double expenses) {
        super(date, expenses);
    }

    public CategoryExpensesType(LocalDate date, CategoryType category, double expenses) {
        super(date, expenses);
        this.category = category;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
