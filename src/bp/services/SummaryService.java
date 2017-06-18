package bp.services;

import bp.model.*;
import bp.repository.SummaryRepository;
import com.sun.deploy.net.BasicHttpRequest;
import com.sun.deploy.net.HttpRequest;

import java.net.URI;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Created by szkutek on 17.06.17.
 */
public class SummaryService {
    SummaryRepository summaryRepository;

    public SummaryService(SummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    public void addSummary(Summary summary) {
        if (summaryRepository.getMinDate() == null) {
            summaryRepository.setMinDate(summary.getDate());
        }
        summaryRepository.addItem(summary.getDate(), summary);
    }

    public Collection<Summary> getAllSummaries() {
        return summaryRepository.getAll();
    }

    public Collection<Summary> getSummariesFromPeriod(LocalDate dateFrom, LocalDate dateTo) {
        List<Summary> summaries = new ArrayList<>();
        for (Summary it : summaryRepository.getAll()) {
            if (it.getDate().isAfter(dateFrom) && it.getDate().isBefore(dateTo)) {
                summaries.add(it);
            }
        }
        return summaries;
    }

    private double calculatePrognosisPerCategory(CategoryType category, LocalDate date) {
        double n = summaryRepository.getAll().size();
        if (n < 2.0) {
            return 0.0;
        }
        double expense1 = 0.0, expenseN = 0.0;
        Summary summary1 = summaryRepository.getById(summaryRepository.getMinDate());
        Summary summaryN = summaryRepository.getById(date.minusMonths(1));

        if (summary1 != null) {
            expense1 = summary1.getCategoryExpensesMap().get(category).getExpenses();
        }
        if (summaryN != null) {
            expenseN = summaryN.getCategoryExpensesMap().get(category).getExpenses();
        }
        return (n * expenseN - expense1) / (n - 1.0);
        //metoda usredniania zwrotow x_{n+1} = x_n + S, S = (x_n - x_1) / (n - 1);
    }

    private MonthlyExpensesAndIncomeType calculatePrognosisOfIncomeAndExpense(LocalDate date) {
        double n = summaryRepository.getAll().size();
        if (n < 2.0) {
            return new MonthlyExpensesAndIncomeType(date, 0.0, 0.0);
        }

        double income1 = 0.0, incomeN = 0.0;
        double expense1 = 0.0, expenseN = 0.0;
        Summary summary1 = summaryRepository.getById(summaryRepository.getMinDate());
        Summary summaryN = summaryRepository.getById(date.minusMonths(1));

        if (summary1 != null) {
            expense1 = summary1.getExpensesAndIncome().getExpenses();
            income1 = summary1.getExpensesAndIncome().getIncome();
        }
        if (summaryN != null) {
            expenseN = summaryN.getExpensesAndIncome().getExpenses();
            incomeN = summaryN.getExpensesAndIncome().getIncome();
        }

        return new MonthlyExpensesAndIncomeType(date,
                (n * expenseN - expense1) / (n - 1.0),
                (n * incomeN - income1) / (n - 1.0));
        //metoda usredniania zwrotow x_{n+1} = x_n + S, S = (x_n - x_1) / (n - 1);
    }


    public Summary calculatePrognosis(LocalDate date) {
        Summary summary = new Summary();
        for (CategoryType category : CategoryType.values()) {
            summary.addExpense(
                    new CategoryExpensesType(date, category, calculatePrognosisPerCategory(category, date)));
        }
        summary.setExpensesAndIncome(calculatePrognosisOfIncomeAndExpense(date));
        return summary;
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
