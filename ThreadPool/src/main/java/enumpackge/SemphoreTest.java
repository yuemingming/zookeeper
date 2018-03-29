package enumpackge;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Mingming
 * @Description
 * @Date Created in 17:14 2018/3/11
 * @Modificd By
 */
public class SemphoreTest {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            readLock.lock();
            readLock.unlock();
            writeLock.lock();
            writeLock.unlock();
            Runnable run = new Runnable() {
                public void run() {
                    try {
// 获取许可
                        semaphore.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 10000));
// 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能
                       // 打印 5 条记录，之后线程一直阻塞
                        semaphore.release();
                    } catch (InterruptedException e) {
                    }
                }
            };
            exec.execute(run);
        }
// 退出线程池
        exec.shutdown();
    }
}
