package pool;

import java.util.concurrent.BlockingDeque;

/**
 * @author Mingming
 * @Description
 * @Date Created in 10:38 2018/2/22
 * @Modificd By
 */
public class Producer implements Runnable {
    BlockingDeque<String> queue;
   public Producer(BlockingDeque<String> queue){
       this.queue = queue;
   }
    @Override
    public void run() {
        try {
            String temp = "A Product,生产线程："+Thread.currentThread().getName();
            System.out.println("I have made a product:"+Thread.currentThread().getName());
            queue.put(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
