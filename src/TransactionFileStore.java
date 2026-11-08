import java.io.*;
import java.util.List;

public class TransactionFileStore {
    private final String filePath ;
    int maxId=0;

    public TransactionFileStore(String filePath){
        this.filePath=filePath;
    }

    public void save(List<Transaction> transaction){
        try(BufferedWriter writer =new BufferedWriter(new FileWriter(filePath))){
            for (Transaction t : transaction){
                writer.write(t.toFileLine());
                writer.newLine();
            }
            System.out.println("Saved"+transaction.size()+"transcation to "+filePath);

        }catch (IOException e){
            System.out.println("Failed to load "+e.getMessage());
        }


    }
    public void loadInto(TransactionRepository repo){
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("file not found");
            return;
        }

        try(BufferedReader reader =new BufferedReader(new FileReader(file))){
            String line ;
            while((line =reader.readLine() ) !=null){
                if(!line.isBlank()){
                    Transaction t = Transaction.fromFileLine(line);
                    if(t.getId()>maxId){
                        maxId=t.getId();
                    }
                    repo.save(t);

                }
                repo.setNextId(maxId+1);
                System.out.println(line);
            }

        }catch  (IOException e){
            System.out.println("Failed to load "+ e.getMessage());

        }



    }





}
