package bp.repository;

import bp.model.Summary;

import java.time.LocalDate;

public class SummaryRepository extends GenericRepository<LocalDate, Summary> {

    private LocalDate minDate = LocalDate.MAX;

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMinDate() {
        return minDate;
    }
}
