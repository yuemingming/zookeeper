package enumpackge;


import javax.xml.bind.SchemaOutputResolver;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @author Mingming
 * @Description
 * @Date Created in 10:37 2018/3/21
 * @Modificd By
 */
public class CountDownLatchTest {
    public static void main(String[] args){

        final CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
            countDownLatch.countDown();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    System.out.println("awit4444444444");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    System.out.println("awit55555");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
        thread4.start();
        thread5.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
