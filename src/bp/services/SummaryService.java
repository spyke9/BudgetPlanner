package bp.services;

import bp.model.CategoryExpensesType;
import bp.model.CategoryType;
import bp.model.MonthlyExpensesType;
import bp.model.Summary;
import bp.repository.SummaryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public double calculatePrognosisPerCategory(CategoryType category, LocalDate date) {
        double n = summaryRepository.getAll().size();
        if (n < 2.0) {
            return 0.0;
        }
        double min = 0.0, max = 0.0;
        Summary minSummary = summaryRepository.getById(summaryRepository.getMinDate());
        Summary maxSummary = summaryRepository.getById(date.minusMonths(1));

        if (minSummary != null) {
            min = minSummary.getExpenses().get(category).getExpenses();
        }
        if (maxSummary != null) {
            max = maxSummary.getExpenses().get(category).getExpenses();
        }

        return (n * max - min) / (n - 1.0);
        //metoda usredniania zwrotow x_{n+1} = x_n + S, S = (x_n - x_1) / (n - 1);
    }

    public Summary calculatePrognosis(LocalDate date) {
        Summary summary = new Summary();
        for (CategoryType category : CategoryType.values()) {
            summary.addExpense(new CategoryExpensesType(date, category, calculatePrognosisPerCategory(category, date)));
        }
        return summary;
    }

    public Summary pieChart(LocalDate date) {
//
        return new Summary();
    }
}
