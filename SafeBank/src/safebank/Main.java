package safebank;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import exceptions.WithdrawalLimitExceededException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("        Welcome to SafeBank");
        System.out.println("================================");


        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter opening balance: ");
        String balanceInput = scanner.nextLine();

        try {

            BigDecimal openingBalance =
                    new BigDecimal(balanceInput);

            Account account =
                    new Account(name, openingBalance);

            System.out.println();
            System.out.println("Account created successfully!");
            System.out.println("Account Holder: "
                    + account.getAccountHolderName());
            System.out.println("Balance: ₦"
                    + account.getAccountBalance());

            boolean running = true;


            while (running) {

                System.out.println();
                System.out.println("========== SafeBank Menu ==========");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. View Balance");
                System.out.println("4. Transaction History");
                System.out.println("5. Exit");
                System.out.println("====================================");

                System.out.print("Choose an option: ");
                String choice = scanner.nextLine();

                switch (choice) {


                    case "1":

                        System.out.print("Enter deposit amount: ");
                        String depositInput = scanner.nextLine();

                        try {

                            BigDecimal depositAmount =
                                    new BigDecimal(depositInput);

                            account.deposit(depositAmount);

                            System.out.println();
                            System.out.println("Deposit successful!");
                            System.out.println("Deposited: ₦"
                                    + depositAmount);
                            System.out.println("New balance: ₦"
                                    + account.getAccountBalance());

                        } catch (NumberFormatException e) {

                            System.out.println(
                                    "Invalid amount. "
                                            + "Please enter a valid number."
                            );

                        } catch (InvalidAmountException e) {

                            System.out.println(
                                    "Deposit failed: "
                                            + e.getMessage()
                            );
                        }

                        break;


                    case "2":

                        System.out.print("Enter withdrawal amount: ");
                        String withdrawalInput = scanner.nextLine();

                        try {

                            BigDecimal withdrawalAmount =
                                    new BigDecimal(withdrawalInput);

                            account.withdraw(withdrawalAmount);

                            System.out.println();
                            System.out.println("Withdrawal successful!");
                            System.out.println("Withdrawn: ₦"
                                    + withdrawalAmount);
                            System.out.println("New balance: ₦"
                                    + account.getAccountBalance());

                        } catch (NumberFormatException e) {

                            System.out.println(
                                    "Invalid amount. "
                                            + "Please enter a valid number."
                            );

                        } catch (InvalidAmountException e) {

                            System.out.println(
                                    "Withdrawal failed: "
                                            + e.getMessage()
                            );

                        } catch (InsufficientFundsException e) {

                            System.out.println(
                                    "Withdrawal failed: "
                                            + e.getMessage()
                            );

                        } catch (WithdrawalLimitExceededException e) {

                            System.out.println(
                                    "Withdrawal failed: "
                                            + e.getMessage()
                            );
                        }

                        break;


                    case "3":

                        System.out.println();
                        System.out.println("Account Holder: "
                                + account.getAccountHolderName());

                        System.out.println("Current balance: ₦"
                                + account.getAccountBalance());

                        break;



                    case "4":

                        System.out.println();
                        System.out.println("======= Transaction History =======");

                        List<Transaction> transactions =
                                account.getTransactions();

                        if (transactions.isEmpty()) {

                            System.out.println(
                                    "No successful transactions yet."
                            );

                        } else {

                            for (Transaction transaction : transactions) {

                                System.out.println(transaction);
                            }
                        }

                        System.out.println(
                                "==================================="
                        );

                        break;


                    case "5":

                        System.out.println();
                        System.out.println(
                                "Thank you for using SafeBank."
                        );

                        running = false;

                        break;


                    default:

                        System.out.println();
                        System.out.println(
                                "Invalid option. Please choose 1-5."
                        );
                }
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid amount. Please enter a valid number."
            );

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Account creation failed: "
                            + e.getMessage()
            );
        }

        scanner.close();
    }
}