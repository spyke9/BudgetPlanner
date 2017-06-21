package bp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by szkutek on 12.06.17.
 */
public interface ITransaction extends Serializable {

    UUID getId();

    Transaction.TransactionType getType();

    void setType(Transaction.TransactionType type);

    double getAmount();

    void setAmount(double amount);

    LocalDate getDate();

    void setDate(LocalDate date);

    CategoryType getCategory();

    void setCategory(CategoryType category);

}
