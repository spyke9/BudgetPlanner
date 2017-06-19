package bp.model;

import java.time.LocalDate;
import java.util.List;

public class CategorySpendingStats {
    private CategoryType category;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<MonthlyExpensesType> spending;

    public CategorySpendingStats() {
    }

    public CategorySpendingStats(CategoryType category, LocalDate periodBegin, LocalDate periodEnd, List<MonthlyExpensesType> spending) {
        this.category = category;
        this.dateFrom = periodBegin;
        this.dateTo = periodEnd;
        this.spending = spending;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setSpending(List<MonthlyExpensesType> spending) {
        this.spending = spending;
    }

    public List<MonthlyExpensesType> getSpending() {
        return spending;
    }

}
