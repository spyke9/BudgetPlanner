package bp.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by szkutek on 12.06.17.
 */
public abstract class AbstractTransaction implements ITransaction {
    private UUID id;
    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private CategoryType category;

    public enum TransactionType {
        INCOME("I"), EXPENSE("E");
        private String acronym;

        TransactionType(String acronym) {
            this.acronym = acronym;
        }

        public static TransactionType fromAcronym(String acronym) {
            for (TransactionType t : TransactionType.values()) {
                if (Objects.equals(t.acronym, acronym)) {
                    return t;
                }
            }
            return null;
        }

        public String getAcronym() {
            return acronym;
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
