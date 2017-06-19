package bp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by szkutek on 12.06.17.
 */
public interface ITransaction extends Serializable {

    UUID getId();

    void setId(UUID id);

    Transaction.TransactionType getType();

    void setType(Transaction.TransactionType type);

    double getAmount();

    void setAmount(double amount);

    LocalDateTime getDate();

    void setDate(LocalDateTime date);

    CategoryType getCategory();

    void setCategory(CategoryType category);

}
