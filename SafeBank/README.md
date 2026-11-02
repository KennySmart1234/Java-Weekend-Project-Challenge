# SafeBank Transaction Simulator

## Overview

SafeBank is a console-based banking transaction simulator built in Java.

The project focuses on **Exception Handling** and demonstrates how custom exceptions can be used to protect account balances from invalid transactions.

## Features

- Create a bank account
- Validate account holder name
- Validate opening balance
- Deposit money
- Withdraw money
- View current balance
- View successful transaction history
- Validate menu selections
- Handle non-numeric input
- Handle invalid transaction amounts
- Prevent withdrawals above the available balance
- Prevent withdrawals above the ₦100,000 single-withdrawal limit
- Continue running after failed operations

## Project Structure

```text
SafeBank/
└── src/
    ├── exceptions/
    │   ├── InvalidAmountException.java
    │   ├── InsufficientFundsException.java
    │   └── WithdrawalLimitExceededException.java
    │
    └── safebank/
        ├── Main.java
        ├── Account.java
        └── Transaction.java
```

## Custom Exceptions

### InvalidAmountException

Used when:

- The account holder name is blank.
- The opening balance is negative.
- A deposit is zero or negative.
- A withdrawal is zero or negative.

### InsufficientFundsException

Used when a withdrawal is greater than the available account balance.

### WithdrawalLimitExceededException

Used when a withdrawal is greater than the maximum single-withdrawal limit of ₦100,000.

## Business Rules

1. Account holder name must not be blank.
2. Opening balance must not be negative.
3. Deposit amount must be greater than zero.
4. Withdrawal amount must be greater than zero.
5. Withdrawal must not exceed the available balance.
6. Withdrawal must not exceed ₦100,000.
7. Failed transactions must not change the account balance.
8. Failed transactions must not be added to transaction history.
9. Invalid input must not terminate the application.

## Testing Checklist

| Test | Expected Result | Actual Result |
|---|---|---|
| Create account with valid name and balance | Account created | Passed |
| Negative opening balance | Account creation rejected | Passed |
| Blank account holder name | Account creation rejected | Passed |
| Deposit valid amount | Balance increases | Passed |
| Deposit `-500` | Deposit rejected | Passed |
| Deposit `0` | Deposit rejected | Passed |
| Withdrawal within balance | Withdrawal succeeds | Passed |
| Withdrawal greater than balance | Withdrawal rejected | Passed |
| Withdrawal of `₦120,000` from `₦200,000` | Withdrawal rejected | Passed |
| Withdrawal above `₦100,000` | Withdrawal rejected | Passed |
| Non-numeric transaction input | Helpful error displayed | Passed |
| Invalid menu selection | Helpful error displayed | Passed |
| Successful operation after failure | Operation succeeds and application continues | Passed |
| Failed transaction appears in history | It must not appear | Passed |
| Exit application | Application terminates normally | Passed |

## Example

```text
================================
        Welcome to SafeBank
================================
Enter account holder name: Kenny
Enter opening balance: 500

Account created successfully!
Account Holder: Kenny
Balance: ₦500

========== SafeBank Menu ==========
1. Deposit
2. Withdraw
3. View Balance
4. Transaction History
5. Exit
====================================
```

Example successful transaction:

```text
Deposit successful!
Deposited: ₦300
New balance: ₦800
```

Example rejected transaction:

```text
Withdrawal failed: Insufficient funds. Available balance: ₦500
```

## Design Decisions

### BigDecimal for money

`BigDecimal` is used instead of `double` for monetary values because it provides more appropriate precision for financial calculations.

### Business rules in Account

The `Account` class is responsible for validating deposits and withdrawals because these rules belong to the account's business logic.

### Custom Exceptions

Specific exceptions are used to distinguish different business-rule failures:

- `InvalidAmountException`
- `InsufficientFundsException`
- `WithdrawalLimitExceededException`

### Transaction History

Successful transactions are stored in a `List<Transaction>`. A transaction is added only after all validation succeeds.

Therefore, rejected transactions do not modify the balance or transaction history.

### Exception Handling

`Account` throws business-rule exceptions, while `Main` catches them and displays helpful messages to the user.

This keeps business logic separate from console interaction.

## How to Compile

From the `SafeBank` directory:

```bash
javac -d out src/exceptions/*.java src/safebank/*.java
```

## How to Run

```bash
java -cp out safebank.Main
```

## Limitations

- The application currently manages one account per program run.
- Account data is not persisted after the application exits.
- The transfer feature was not implemented because it is an optional stretch requirement.

## Stretch Goal

The challenge suggests adding transfers between two accounts.

This was not implemented because the required SafeBank functionality was prioritized first.