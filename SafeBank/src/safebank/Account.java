package safebank;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import exceptions.WithdrawalLimitExceededException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private static final BigDecimal MAX_WITHDRAWAL =
            new BigDecimal("100000");

    private String accountHolderName;
    private BigDecimal accountBalance;
    private List<Transaction> transactions;

    public Account(String accountHolderName, BigDecimal openingBalance)
            throws InvalidAmountException {

        if (accountHolderName == null || accountHolderName.isBlank()) {
            throw new InvalidAmountException(
                    "Account holder name cannot be null or blank"
            );
        }

        if (openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException(
                    "Opening balance cannot be negative"
            );
        }

        this.accountHolderName = accountHolderName;
        this.accountBalance = openingBalance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(BigDecimal amount)
            throws InvalidAmountException {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero"
            );
        }

        accountBalance = accountBalance.add(amount);

        transactions.add(
                new Transaction("DEPOSIT", amount)
        );
    }

    public void withdraw(BigDecimal amount)
            throws InvalidAmountException,
            InsufficientFundsException,
            WithdrawalLimitExceededException {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero"
            );
        }

        if (amount.compareTo(MAX_WITHDRAWAL) > 0) {
            throw new WithdrawalLimitExceededException(
                    "Maximum single withdrawal is ₦100,000"
            );
        }

        if (amount.compareTo(accountBalance) > 0) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Available balance: ₦"
                            + accountBalance
            );
        }

        accountBalance = accountBalance.subtract(amount);

        transactions.add(
                new Transaction("WITHDRAWAL", amount)
        );
    }
}