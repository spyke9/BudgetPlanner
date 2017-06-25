package bp.services;

import bp.model.*;
import bp.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by szkutek on 12.06.17.
 */
public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(ITransaction transaction) {
        transactionRepository.addItem(transaction.getId(), transaction);
    }

    public void updateTransaction(ITransaction transaction) {
        transactionRepository.update(transaction.getId(), transaction);
    }

    public void deleteTransaction(UUID id) {
        transactionRepository.remove(id);
    }

    public Collection<ITransaction> getAllTransactions() {
        return transactionRepository.getAll();
    }

    public Collection<ITransaction> getTransactionsFromPeriod(LocalDate dateFrom, LocalDate dateTo) {
        List<ITransaction> transactions = new ArrayList<>();
        for (ITransaction it : transactionRepository.getAll()) {
            if (it.getDate().isAfter(dateFrom.minusDays(1)) && it.getDate().isBefore(dateTo.plusDays(1))) {
                transactions.add(it);
            }
        }
        return transactions;
    }

    public double sumOutgoingTransactionsFromMonth(LocalDate date) {
        double sum = 0.0;
        for (ITransaction transaction : transactionRepository.getAll()) {
            if (transaction.getDate().getYear() == date.getYear()
                    && transaction.getDate().getMonth() == date.getMonth()
                    && transaction.getType() == Transaction.TransactionType.EXPENSE) {
                sum += transaction.getAmount();
            }
        }
        return sum;
    }

    private MonthlyExpensesType sumTransactionsPerMonth(LocalDate date, CategoryType category) {
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
        return new MonthlyExpensesType(date, sum);
    }


    public CategorySpendingStats categorySpendingStats(CategoryType category, LocalDate dateFrom, LocalDate dateTo) {
        List<MonthlyExpensesType> stats = new ArrayList<>();
        for (LocalDate date = dateFrom; date.isBefore(dateTo); date = date.plusMonths(1)) {
            stats.add(sumTransactionsPerMonth(date, category));
        }
        return new CategorySpendingStats(category, dateFrom, dateTo, stats);
    }
}
