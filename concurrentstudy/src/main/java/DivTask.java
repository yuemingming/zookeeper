import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mingming
 * @Description
 * @Date Created in 10:19 2018/3/26
 * @Modificd By
 */
public class DivTask implements Runnable{
    int a,b;
    public DivTask(int a,int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        System.out.println(a/b);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            executorService.submit(new DivTask(100,i));
        }
        executorService.shutdown();
    }
}

