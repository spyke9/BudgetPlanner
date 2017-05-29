package repository;

import model.Summary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class SummaryRepository implements Serializable {
    private Map<LocalDate, Summary> summaryMap;

    public void addSummary(LocalDate date, Summary summary) {
        if (summaryMap.containsKey(date)) {
            summaryMap.replace(date, summary);
        } else {
            summaryMap.putIfAbsent(date, summary);
        }
    }

    public Map<LocalDate, Summary> getSummary() {
        return summaryMap;
    }

    public Summary getSummary(LocalDate date) {
        if (summaryMap.containsKey(date)) {
            return summaryMap.get(date);
        } else {
            return new Summary();
        }
    }
}
