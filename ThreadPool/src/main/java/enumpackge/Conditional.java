package enumpackge;

import pool.ThradPool;
import scala.util.parsing.combinator.testing.Str;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mingming
 * @Description
 * @Date Created in 21:00 2018/3/13
 * @Modificd By
 */
public class Conditional{
    static volatile int num=1;
    public static void main(String args[]) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + "\tshuzi:" + Conditional.num);
                    num++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + "\tshuzi:" + Conditional.num);
                    num++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        Thread.sleep(50);
        thread2.start();
    }
}