package enumpackge;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author Mingming
 * @Description
 * @Date Created in 21:00 2018/3/16
 * @Modificd By
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                long num = 1000000000;
                while (num-- > 0) ;
                return 1;
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> submit = executorService.submit(callable);
        System.out.println(submit.get());
        executorService.shutdown();

    }
}
