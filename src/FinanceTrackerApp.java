import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class   FinanceTrackerApp {
    public static final String FILE_PATH ="transaction_data.txt";
    static void main() {
        TransactionRepository repo = new TransactionRepository();
        TransactionFileStore file =  new TransactionFileStore(FILE_PATH);
        Scanner sc = new Scanner(System.in);
        file.loadInto(repo);
        boolean running = true;
        while (running){
            printMenu();
            String choose = sc.nextLine().trim();

            switch (choose){
                case "1":
                    addTransaction(sc,repo);
                    break;

                case "2":
                    listTransactions(repo);
                    break;

                case "3":
                    findByDescription(sc,repo);
                    break;

                case "4":
                    findByDateRange(sc,repo);

                    break;

                case "5":
                    removeTransaction(sc,repo);
                     break;

                case "6":    showState(repo); break;
                case "7":    sortByAmount(repo); break;

                case "8":
                    List<Transaction> allTransaction =repo.findAll();
                    file.save(allTransaction);
                    System.out.println("Your input is save in the file");
                    running=false;
                    break;
                default:
                    System.out.println("Given input is incorrect");
                    break;














            }



        }



    }
    public static void  addTransaction(Scanner sc ,TransactionRepository repo){
        System.out.println("Enter a userId ");
        int userId ;
        try{
            userId = Integer.parseInt(sc.nextLine().trim());

        }catch (NumberFormatException e){
            System.out.println("Invalid userId "+e.getMessage());
            return;
        }

        System.out.println("Write a Description ");
        String description=sc.nextLine().trim();

        System.out.println("Your amount");
        double amount ;
        try{
            amount= Double.parseDouble(sc.nextLine().trim());

        }catch (NumberFormatException e ){
            System.out.println("invalid amount "+e.getMessage());
            return;
        }
        System.out.println("Category of your spending ex Food ,clothes etc.");
        String category = sc.nextLine().trim();
        System.out.println("Transaction type Income Or expense ");
        TransactionType type ;
        try{
            type=TransactionType.valueOf(sc.nextLine().trim().toUpperCase());

        } catch (IllegalArgumentException e){
            System.out.println(" invalid Type "+e.getMessage());
            return;
        }

        System.out.println("write a current date ");
        LocalDate date =LocalDate.now();
        repo.createTransaction(userId,description,amount,category,type,date);



    }
    public static void listTransactions( TransactionRepository repo){
        List<Transaction> all = repo.findAll();
        if(all.isEmpty()){
            System.out.println("List is Empty");
            return;
        }
        for (Transaction t : all){
            System.out.println(t);

        }
    }
    public static void findByDescription(Scanner sc, TransactionRepository repo){
        System.out.println("write a description ");
        String desc =sc.nextLine().trim();
        List<Transaction> allDescription = repo.findAll();
        Transaction result = TransactionAlgo.searchByDescription(allDescription, desc);
        if(result==null){
            System.out.println("No matching transactions found");
        }else {
            System.out.println(result);
        }

    }
    public static void findByDateRange(Scanner sc, TransactionRepository repo){
        System.out.println("Enter a Start Date(yyyy-MM-dd): ");
        LocalDate start ;

        try {
            start = LocalDate.parse(sc.nextLine().trim());

        }catch (DateTimeParseException e){
            System.out.println("Invalid date "+ e.getMessage());
            return;
        }

        System.out.println("Enter a end Date(yyyy-MM-dd): ");
        LocalDate end ;
        try {
            end = LocalDate.parse(sc.nextLine().trim());

        }catch (DateTimeParseException e){
            System.out.println("Invalid date "+ e.getMessage());
            return;
        }

        List<Transaction> results=repo.findByDateRange(start,end);
        if(results.isEmpty()){
            System.out.println("no Transaction ");
            return;
        }else {
            for(Transaction t: results){
                System.out.println(t);
            }
        }

    }
    public static void removeTransaction(Scanner sc, TransactionRepository repo){
        System.out.println("enter a Id ");
        int id=0;
        try{
            id = Integer.parseInt(sc.nextLine().trim());
        }catch (NumberFormatException e) {
            System.out.println("invalid id"+e.getMessage());
            return;

        }
        Optional<Transaction> maybe = repo.findById(id);
        if(maybe.isPresent()){
            repo.remove(id);
            System.out.println("removed "+maybe.get());

        }else {
            System.out.println("No transaction with that id");
        }

    }

    public static void printMenu(){
        System.out.println("=======<FinanceTracker>========");
        System.out.println("   1 . To Add a Transaction");
        System.out.println("   2 . All The Transaction ");
        System.out.println("   3 . Find The Transaction By Search of Description");
        System.out.println("   4 . Find The Transaction BY DateRange ");
        System.out.println("   5 . Remove The Transaction ");
        System.out.println("   6 . show states ");
        System.out.println("   7 . sortByAmount  ");
        System.out.println("   8. save and Exit ");
        System.out.println("==================================");
        System.out.println(" Choose The Option - >  ");
        System.out.println();


    }
    public static void showState(TransactionRepository repo){
        List<Transaction> transactions =repo.findAll();
        double balance =transactions.stream().mapToDouble(Transaction::getSignedAmount).sum();
        double income =transactions.stream().filter(t ->t.getType().equals(TransactionType.INCOME)).mapToDouble(Transaction::getAmount).sum();
        double expense= transactions.stream().filter(t ->t.getType().equals(TransactionType.EXPENSE)).mapToDouble(Transaction::getAmount).sum();

//        for(Transaction  t :transactions ){
//            if(t.getType().equals(TransactionType.EXPENSE)){
//                expense += t.getAmount();
//            }
//            if(t.getType().equals(TransactionType.INCOME)){
//                income += t.getAmount();
//            }
//            balance +=t.getSignedAmount();
//        }
        System.out.println("Total amount is "+ balance);
        System.out.println("Total Expense is "+ expense);
        System.out.println("Total Income is "+ income);
    }

    public static void sortByAmount(TransactionRepository repo){
        List<Transaction> list =repo.findAll();
        List<Transaction> sortedList = TransactionAlgo.sortByAmount(list);
        for(Transaction t:sortedList){
            System.out.println(t);
        }




    }



}
