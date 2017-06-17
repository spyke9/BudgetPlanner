package bp.services;

import bp.model.CategoryType;
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
        LocalDate minDate = date;
        Collection<LocalDate> keys = summaryRepository.getKeys();
        double n = 0.0;
        for (LocalDate d : keys) {
            if (d.isBefore(minDate)) {
                minDate = d;
            }
            if (d.isBefore(date)) {
                n++;
            }
        }
        if (n < 2.0) {
            return 0.0;
        } else {
            double min = 0.0;
            double max = 0.0;
            Summary minSummary = summaryRepository.getById(minDate);
            Summary maxSummary = summaryRepository.getById(date.minusMonths(1));

            if (minSummary != null) {
                min = minSummary.getExpenses().get(category).getExpenses();
            }
            if (maxSummary != null) {
                max = maxSummary.getExpenses().get(category).getExpenses();
            }

            return (max - min) / (n - 1.0);
        }
    }

    public Summary calculatePrognosis(LocalDate date) {
        for (CategoryType category : CategoryType.values()) {
            calculatePrognosisPerCategory(category, date);
        }

        return new Summary();
    }
}
