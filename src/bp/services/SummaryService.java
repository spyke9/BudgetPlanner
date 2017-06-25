package bp.services;

import bp.model.*;
import bp.repository.SummaryRepository;
import bp.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by szkutek on 17.06.17.
 */
public class SummaryService {
    private SummaryRepository summaryRepository;
    private TransactionRepository transactionRepository;

    public SummaryService(SummaryRepository summaryRepository, TransactionRepository transactionRepository) {
        this.summaryRepository = summaryRepository;
        this.transactionRepository = transactionRepository;
    }

    public SummaryRepository getSummaryRepository() {
        return summaryRepository;
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public void addSummary(Summary summary) {
        if (summaryRepository.getMinDate() == null) {
            summaryRepository.setMinDate(summary.getDate());
        }
        summaryRepository.addItem(summary.getDate(), summary);
    }

    //    public void addSummaryFromMap(LocalDate date, Map<CategoryType, Double>) {
//
//    }

    public LocalDate getMinDate() {
        return summaryRepository.getMinDate();
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

    public Summary summaryPerMonth(LocalDate date) {
        Summary summary = new Summary(date);

        double expensePerMonth = 0.0;
        for (CategoryType category : CategoryType.values()) {
            CategoryExpensesType tmp = sumTransactionsPerCategory(date, category);
            summary.addExpense(tmp);
            expensePerMonth += tmp.getExpenses();
        }
        summary.setExpensesAndIncome(
                new MonthlyExpensesAndIncomeType(date, expensePerMonth, sumIncomingTransactionsFromMonth(date)));
        return summary;
    }

    private CategoryExpensesType sumTransactionsPerCategory(LocalDate date, CategoryType category) {
        double sum = 0.0;
        for (ITransaction transaction : transactionRepository.getAll()) {
            if (transaction.getCategory() == category
                    && transaction.getDate().getYear() == date.getYear()
                    && transaction.getDate().getMonth() == date.getMonth()) {
                if (transaction.getType() == Transaction.TransactionType.EXPENSE) {
                    sum += transaction.getAmount();
                }
            }
        }
        return new CategoryExpensesType(date, category, sum);
    }

    private double sumIncomingTransactionsFromMonth(LocalDate date) {
        double sum = 0.0;
        for (ITransaction transaction : transactionRepository.getAll()) {
            if (transaction.getDate().getYear() == date.getYear()
                    && transaction.getDate().getMonth() == date.getMonth()) {
                if (transaction.getType() == Transaction.TransactionType.INCOME) {
                    sum += transaction.getAmount();
                }
            }
        }
        return sum;
    }


}
