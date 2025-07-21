import java.io.*;
import java.util.ArrayList;

public class SerializationTest {
    public static void main(String[] args) {
        // Create a sample DepositWithdraw object
        DepositWithdraw account = new DepositWithdraw("John Doe", "ACC1001", 500.00);
        ArrayList<TransactionRecord> transactions = new ArrayList<>();
        transactions.add(new TransactionRecord("DEPOSIT", 200.00));

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test_data.dat"))) {
            oos.writeObject(account);
            oos.writeObject(transactions);
            System.out.println("Serialization successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test_data.dat"))) {
            DepositWithdraw deserializedAccount = (DepositWithdraw) ois.readObject();
            ArrayList<TransactionRecord> deserializedTransactions = (ArrayList<TransactionRecord>) ois.readObject();
            System.out.println("Deserialization successful.");
            System.out.println(deserializedAccount);
            System.out.println(deserializedTransactions);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

