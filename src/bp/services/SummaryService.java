package bp.services;

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
    private SummaryRepository summaryRepository;

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
}
