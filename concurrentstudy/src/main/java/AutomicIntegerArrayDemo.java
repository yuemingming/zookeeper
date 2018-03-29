import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Mingming
 * @Description
 * @Date Created in 15:44 2018/3/26
 * @Modificd By
 */
public class AutomicIntegerArrayDemo {
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);
     static class AddThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                arr.getAndIncrement(i%arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new Thread(new AddThread());
            ts[i].start();
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }
        System.out.println(arr);
    }
}
