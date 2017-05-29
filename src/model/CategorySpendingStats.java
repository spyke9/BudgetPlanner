package model;

import java.time.LocalDate;
import java.util.List;

public class CategorySpendingStats implements IStatistics {
    private CategoryType category;
    private LocalDate periodBegin;
    private LocalDate periodEnd;
    private List<MonthlyExpensesType> spending;

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setPeriodBegin(LocalDate periodBegin) {
        this.periodBegin = periodBegin;
    }

    public LocalDate getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public void setSpending(List<MonthlyExpensesType> spending) {
        this.spending = spending;
    }

    public List<MonthlyExpensesType> getSpending() {
        return spending;
    }
    

    @Override
    public void drawPlot() {

    }

    @Override
    public double calculate() {
        return 0;
    }
}
