import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class TransactionRecord implements Serializable {
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    public TransactionRecord(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: OMR %.2f",
                timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                type, amount);
    }
}