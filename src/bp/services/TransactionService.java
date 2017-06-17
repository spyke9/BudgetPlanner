package bp.services;

import bp.config.Configuration;
import bp.model.*;
import bp.repository.TransactionRepository;

import java.time.LocalDate;

/**
 * Created by szkutek on 12.06.17.
 */
public class TransactionService {
    TransactionRepository transactionRepository = new TransactionRepository();

    public TransactionService() {
        try {
            transactionRepository = (TransactionRepository) Serializer.deserialize(Configuration.TRANSACTION_REPOSITORY_FILE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
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
//        CategoryExpensesType sum = new CategoryExpensesType();
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
        Summary summary = new Summary(date, sumIncomingTransactionsFromMonth(date));

        for (CategoryType category : CategoryType.values()) {
            summary.addExpense(sumTransactionsPerCategory(date, category));
        }
        return summary;
    }


}
