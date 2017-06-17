package bp.repository;

import bp.model.Summary;

import java.time.LocalDate;

public class SummaryRepository extends GenericRepository<LocalDate, Summary> {

    LocalDate minDate;

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMinDate() {
        return minDate;
    }
}
