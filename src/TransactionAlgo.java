import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionAlgo {

public static  Transaction searchByDescription(List<Transaction> transactions,String keyword){
return transactions.stream().filter(t ->t.getDescription().equals(keyword)).findFirst().orElse(null);

//    for (Transaction t :transactions){
//        if(t.getDescription().equals(keyword)){
//            System.out.println("found it ");
//            return t;
//
//        }
//    }



}
public static List<Transaction> sortByAmount(List<Transaction> transactions){
    List<Transaction> t = new ArrayList<>(transactions);
    t.sort(Comparator.comparing(Transaction::getAmount).reversed());
    return t;

}


}
