package bp.services;

import bp.model.CategoryExpensesType;
import bp.model.CategoryType;
import bp.model.MonthlyExpensesAndIncomeType;
import bp.model.Summary;
import bp.repository.SummaryRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szkutek on 21.06.17.
 */
public class GraphService {
    private SummaryRepository summaryRepository;

    public GraphService(SummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    public Map<String, Double> pieChart(LocalDate date) {
        Summary summaryChart = summaryRepository.getById(date.withDayOfMonth(1));
        Map<String, Double> res = new HashMap<>();

        if (summaryChart != null) {
            for (CategoryType category : CategoryType.values()) {
                CategoryExpensesType categoryExpensesType = summaryChart.getCategoryExpensesMap().get(category);
                if (categoryExpensesType != null) {
                    double expensePerCategory = categoryExpensesType.getExpenses();
                    res.put(category.getName(), expensePerCategory);
                }
            }
        }
        return res;
    }

    public List<MonthlyExpensesAndIncomeType> barGraph(int year) {
        List<MonthlyExpensesAndIncomeType> monthlyExpensesAndIncomeList = new ArrayList<>();
        for (Month month : Month.values()) {
            Summary summary = summaryRepository.getById(LocalDate.of(year, month, 1));
            if (summary != null) {
                monthlyExpensesAndIncomeList.add(summary.getExpensesAndIncome());
            }
        }
        return monthlyExpensesAndIncomeList;
    }
}
