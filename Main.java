import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "bank_data.dat";
    private static ArrayList<TransactionRecord> transactions = new ArrayList<>();
    private static ArrayList<ArrayList<String>> accounts = new ArrayList<>();
    private static ArrayList<DepositWithdraw> bankAccounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData(); // Load existing data at start

        // Account Creation Phase
        int users = 0;
        while (users < 3) {
            ArrayList<String> account = CreateAccounts.creatAccounts();
            if (account != null) {
                accounts.add(account);
                bankAccounts.add(new DepositWithdraw(
                        account.get(1),  // name
                        "ACC" + (users + 1000),  // account number
                        500.00  // initial balance
                ));
                users++;
            }
        }

        // Account Summary
        System.out.println("\n===== Account Created Successfully =====");
        System.out.println("Bank Name: " + CreateAccounts.BANK_NAME);
        System.out.println("Minimum Balance: " + CreateAccounts.MINIMUM_BALANCE);
        System.out.println("Bank Constant: " + CreateAccounts.BankConstants);

        System.out.println("\nAccount Holders:");
        for (ArrayList<String> acc : accounts) {

            System.out.println("Username: " + acc.get(0));
            System.out.println("Name: " + acc.get(1));
            System.out.println("Phone: +968 " + acc.get(3));
            System.out.println("----------------");
        }

        // Banking Operations
        DepositWithdraw currentAccount = new DepositWithdraw("Default", "000000000", 500.00);

        while (true) {
            System.out.println("\nBank Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Deposit amount: ");
                    double deposit = scanner.nextDouble();
                    currentAccount.deposit(deposit);
                    transactions.add(new TransactionRecord("DEPOSIT", deposit));
                    break;

                case 2:
                    System.out.print("Withdraw amount: ");
                    double withdraw = scanner.nextDouble();
                    currentAccount.withdraw(withdraw);
                    transactions.add(new TransactionRecord("WITHDRAWAL", withdraw));
                    break;

                case 3:
                    currentAccount.checkBalance();
                    break;

                case 4:
                    viewTransactions();
                    break;

                case 5:
                    saveData();
                    break;

                case 6:
                    saveData();
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
    }

    // New helper methods
    private static void viewTransactions() {
        System.out.println("\n=== Transaction History ===");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet");
        } else {
            transactions.forEach(System.out::println);
        }
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
            oos.writeObject(bankAccounts);
            oos.writeObject(transactions);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                accounts = (ArrayList<ArrayList<String>>) ois.readObject();
                bankAccounts = (ArrayList<DepositWithdraw>) ois.readObject();
                transactions = (ArrayList<TransactionRecord>) ois.readObject();
                System.out.println("Data loaded successfully!");
            } catch (Exception e) {
                System.err.println("Error loading data: " + e.getMessage());
            }
        } else {
            System.out.println("No existing data file found. Starting fresh.");
        }
    }
}

