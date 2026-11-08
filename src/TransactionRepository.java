import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionRepository implements Repository<Transaction>{

    private final HashMap<Integer,Transaction> transaction = new HashMap<>();
     private  int nextId =1;
     public void setNextId(int nextId){
         this.nextId=nextId;
     }

    public Transaction createTransaction(int userId , String description , double amount , String category, TransactionType type, LocalDate date ){
        Transaction transaction1 = new Transaction(nextId++,userId,description,amount,category,type,date);
        save(transaction1);
        return transaction1;
    }



    @Override
    public void save(Transaction items) {
        transaction.put(items.getId(),items);

    }


    @Override
    public List<Transaction> findAll() {
        return transaction.values().stream().sorted(Comparator.comparing(Transaction::getId)).collect(Collectors.toUnmodifiableList());
    }

    public List<Transaction> findByDateRange(LocalDate start ,LocalDate end){
        List<Transaction> transactionList = transaction.values().stream().filter(t -> !t.getDate().isBefore(start)&&!t.getDate().isAfter(end)).collect(Collectors.toUnmodifiableList());
        //new ArrayList<>();
//        for (Transaction t :transaction.values()){
//           if(t.getDate().isAfter(start) || t.getDate().equals(start) && t.getDate().isBefore(end)||t.getDate().isEqual(end)) {
//               transactionList.add(t);
//           }
//
//        }
//
        return transactionList;
    }
    public Optional<Transaction> findById(int id ){
         return Optional.ofNullable(transaction.get(id));
//       Transaction t= transaction.get(id);
//       if(t==null) {
//           System.out.println("nothing is there");
//           return Optional.empty();
//       }else {
//           return Optional.of(t);
//       }


    }

    public boolean remove(int id ){

//        if(transaction.containsKey(id)){
//            transaction.remove(id);
//            System.out.println("The Transaction is removed");
//            return true;
//        }

        return transaction.remove(id)!=null;

    }






}
