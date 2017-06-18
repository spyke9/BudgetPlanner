package bp.services;

import bp.model.*;
import bp.repository.TransactionRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by szkutek on 12.06.17.
 */
public class TransactionService {
    TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(ITransaction transaction) {
        transaction.setId(UUID.randomUUID());
        transactionRepository.addItem(transaction.getId(), transaction);
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public Collection<ITransaction> getAllTransactions() {
        return transactionRepository.getAll();
    }

    public Collection<ITransaction> getTransactionsFromPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        List<ITransaction> transactions = new ArrayList<>();
        for (ITransaction it : transactionRepository.getAll()) {
            if (it.getDate().isAfter(dateFrom) && it.getDate().isBefore(dateTo)) {
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
                    && transaction.getType() == AbstractTransaction.TransactionType.EXPENSE) {
                sum += transaction.getAmount();
            }
        }
        return sum;
    }

    public double sumIncomingTransactionsFromMonth(LocalDate date) {
        double sum = 0.0;
        for (ITransaction transaction : transactionRepository.getAll()) {
            if (transaction.getDate().getYear() == date.getYear()
                    && transaction.getDate().getMonth() == date.getMonth()) {
                if (transaction.getType() == AbstractTransaction.TransactionType.INCOME) {
                    sum += transaction.getAmount();
                }
            }
        }
        return sum;
    }

    public CategoryExpensesType sumTransactionsPerCategory(LocalDate date, CategoryType category) {
        double sum = 0.0;
        for (ITransaction transaction : transactionRepository.getAll()) {
            if (transaction.getCategory() == category
                    && transaction.getDate().getYear() == date.getYear()
                    && transaction.getDate().getMonth() == date.getMonth()) {
                if (transaction.getType() == AbstractTransaction.TransactionType.EXPENSE) {
                    sum += transaction.getAmount();
                }
            }
        }
        return new CategoryExpensesType(date, category, sum);
    }

    public Summary summaryPerMonth(LocalDate date) {
        Summary summary = new Summary();

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

    public MonthlyExpensesType sumTransactionsPerMonth(LocalDate date, CategoryType category) {
        double sum = 0.0;
        for (ITransaction transaction : transactionRepository.getAll()) {
            if (transaction.getCategory() == category
                    && transaction.getDate().getYear() == date.getYear()
                    && transaction.getDate().getMonth() == date.getMonth()) {
                if (transaction.getType() == AbstractTransaction.TransactionType.EXPENSE) {
                    sum += transaction.getAmount();
                }
            }
        }
        return new MonthlyExpensesType(date, sum);
    }


    public CategorySpendingStats categorySpendingStats(CategoryType category, LocalDate dateFrom, LocalDate dateTo) {
        List<MonthlyExpensesType> stats = new ArrayList<>();
        for (LocalDate date = dateFrom; date.isBefore(dateTo); date.plusMonths(1)) {
            stats.add(sumTransactionsPerMonth(date, category));
        }
        return new CategorySpendingStats(category, dateFrom, dateTo, stats);
    }
}
