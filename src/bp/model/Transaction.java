package bp.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by szkutek on 12.06.17.
 */
public class Transaction implements ITransaction {
    private UUID id;
    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private CategoryType category;

    public Transaction() {
        this.id = UUID.randomUUID();
    }

    public Transaction(TransactionType type, double amount, LocalDateTime date, CategoryType category) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public enum TransactionType {
        INCOME("Income"), EXPENSE("Expense");
        private String name;

        TransactionType(String name) {
            this.name = name;
        }

        public static TransactionType fromName(String name) {
            for (TransactionType t : TransactionType.values()) {
                if (Objects.equals(t.name, name)) {
                    return t;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }

    public UUID getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}