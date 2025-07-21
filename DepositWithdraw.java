import java.io.Serializable;

public class DepositWithdraw implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization
    public static final double MINIMUM_BALANCE = 100.00;
    public static final double MAX_WITHDRAWAL_LIMIT = 500.00;

    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Constructor
    public DepositWithdraw(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        if (initialBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException("Initial balance must be at least OMR " + MINIMUM_BALANCE);
        }
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("\nDeposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("\nDeposit successful. New balance: OMR %.2f", balance);
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("\nWithdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("\nInsufficient funds.");
        } else if ((balance - amount) < MINIMUM_BALANCE) {
            System.out.println("\nCannot withdraw, Minimum balance of OMR " + MINIMUM_BALANCE + " is required.");
        } else {
            balance -= amount;
            System.out.printf("\nWithdrawal successful. New balance: OMR %.2f", balance);
        }
    }

    // Check balance method
    public void checkBalance() {
        System.out.printf("\nAccount Balance: OMR %.2f", balance);
    }

    // Override toString method
    @Override
    public String toString() {
        return "Account Holder: " + accountHolderName +
                ", Account Number: " + accountNumber +
                ", Balance: OMR " + String.format("%.2f", balance);
    }
}
