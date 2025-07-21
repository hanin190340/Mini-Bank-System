import java.util.ArrayList;
import java.util.Scanner;

public class CreateAccounts {
    public static final String BANK_NAME = "Oman National Bank";
    public static final double MINIMUM_BALANCE = 100.00;
    public static final double BankConstants = 5000.00;

    public static ArrayList<String> creatAccounts() {
        Scanner myObj = new Scanner(System.in);
        ArrayList<String> account = new ArrayList();
        String accountNumber;
        String userName;
        String name;

        System.out.print("Enter account number: ");
        accountNumber = myObj.nextLine();

        if (accountNumber != null && !accountNumber.isEmpty()) {
            System.out.println("Bank account already registered and active");
            return null;
        }
        System.out.println(" --------------------Create Accounts------------------- ");
        System.out.println("Enter username: ");
        userName = myObj.nextLine();
        account.add(userName);

        System.out.println("Enter your name: ");
        name = myObj.nextLine();
        account.add(name);

        System.out.println("Enter User Gender: male /female");
        String gender = myObj.nextLine();
        account.add(gender);

        System.out.println("Enter your phone number: ");
        String phoneNumber = myObj.nextLine();
        account.add(phoneNumber);

        System.out.println("Enter your ID: ");
        String id = myObj.nextLine();
        account.add(id);

        System.out.println("Enter your date of birth (dd/mm/yyyy): ");
        String dateOfBirth = myObj.nextLine();
        account.add(dateOfBirth);

        System.out.println("Enter your address: ");
        String address = myObj.nextLine();
        account.add(address);
        System.out.println(account);
        return account;
    }
}
