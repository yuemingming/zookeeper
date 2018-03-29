import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Mingming
 * @Description
 * @Date Created in 14:39 2018/3/27
 * @Modificd By
 */
public class CompletableFutureDemo {
    public static Integer cals(Integer para){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para*para;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->cals(50)).thenApply((i)->Integer.toString(i)).thenApply((str)->"\""+str+"\"").thenAccept(System.out::println);
    }
}
