package bp.services;

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

    public Map<CategoryType, Double> pieChart(LocalDate date) {
        Summary summaryChart = summaryRepository.getById(date);
        Map<CategoryType, Double> res = new HashMap<>();
        double sum = 0.0;
        for (CategoryType category : CategoryType.values()) {
            double expensePerCategory = summaryChart.getCategoryExpensesMap().get(category).getExpenses();
            sum += expensePerCategory;
        }
        for (CategoryType category : CategoryType.values()) {
            double expensePerCategory = summaryChart.getCategoryExpensesMap().get(category).getExpenses();
            res.put(category, expensePerCategory / sum);
        }
        return res;
    }

    public List<MonthlyExpensesAndIncomeType> barGraph(int year) {
        List<MonthlyExpensesAndIncomeType> monthlyExpensesAndIncomeList = new ArrayList<>();
        for (Month month : Month.values()) {
            Summary summary = summaryRepository.getById(LocalDate.of(year, month, 1));
            monthlyExpensesAndIncomeList.add(summary.getExpensesAndIncome());
        }
        return monthlyExpensesAndIncomeList;
    }
}
