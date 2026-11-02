package safebank;

import java.math.BigDecimal;

public class Transaction {
    private String type;
    private BigDecimal amount;

    public Transaction(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" + "type=" + type + ", amount=" + amount + '}';
    }
}
