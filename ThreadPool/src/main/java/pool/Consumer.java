package pool;

import java.util.concurrent.BlockingDeque;

/**
 * @author Mingming
 * @Description
 * @Date Created in 10:38 2018/2/22
 * @Modificd By
 */
public class Consumer implements Runnable{
    BlockingDeque<String> queue;

    public Consumer(BlockingDeque<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String temp = queue.take();
            System.out.println(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
