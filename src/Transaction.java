import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

public class Transaction {
    private final Integer id;
    private final Integer userID;
    private String description;



    private double amount ;
    private String category;
    private TransactionType type;
    private LocalDate date;


    public Transaction(Integer id, Integer userID, String description, double amount, String category, TransactionType type, LocalDate date) {
        this.id = id;
        this.userID = userID;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userID=" + userID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
    public String toFileLine(){
        return id +"|" +userID+ "|"+description+"|"+amount+"|"+category+"|"+type+"|"+date;
    }
    public static Transaction fromFileLine( String line){
        String[] parts = line.split("\\|");
        Transaction transaction =new Transaction(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),parts[2],Double.parseDouble(parts[3]),parts[4],TransactionType.valueOf(parts[5]),LocalDate.parse(parts[6]));
        return transaction;


    }
    public double getSignedAmount() {


        if(type==TransactionType.EXPENSE){
            return amount--;
        }else {
            return amount;

        }

    }






}
