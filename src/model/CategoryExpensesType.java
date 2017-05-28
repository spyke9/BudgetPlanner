package model;

public class CategoryExpensesType extends MonthlyExpensesType {
    private CategoryType category;

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
