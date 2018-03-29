package pool;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Mingming
 * @Description
 * @Date Created in 10:39 2018/2/22
 * @Modificd By
 */
public class Test {
    public static void main(String[] args){
        BlockingDeque<String>  queue = new LinkedBlockingDeque<String>(2);
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        for (int i = 0; i < 5; i++) {
            new Thread(producer,"Producer"+(i+1)).start();
            new Thread(consumer,"Consumer"+(i+1)).start();
        }
    }
}
