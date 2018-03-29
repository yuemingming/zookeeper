package pool;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Mingming
 * @Description
 * @Date Created in 11:20 2018/2/22
 * @Modificd By
 */
@SuppressWarnings("ALL")
public class ThradPool implements IThreadPool {
    /**
     * WORKER_NUMBER默认开启线程数
     */
    static int WORKER_NUMBER = 5;

    /**
     * sumCount完成任务线程数，可见性
     */
    static volatile int sumCount = 0;

    /**
     * taskQueue任务队列
     */
    BlockingDeque<Runnable> taskQueue = new LinkedBlockingDeque<Runnable>();

    /**
     * 线程工作组
     */
    WorkerThread[] workerThreads;

    /**
     * 原子性
     */
    static AtomicLong threadNum = new AtomicLong();

    static ThradPool thradPool;

    public ThradPool() {
        this(WORKER_NUMBER);
    }

    public ThradPool(int workerNumber) {
        WORKER_NUMBER = workerNumber;
        //开辟工作空间
        workerThreads = new WorkerThread[WORKER_NUMBER];
        //开始创建工作线程
        for (int i = 0; i < WORKER_NUMBER; i++) {
            workerThreads[i] = new WorkerThread();
            Thread thread = new Thread(workerThreads[i], "Thread-Worker" + threadNum.incrementAndGet());
            System.out.println("初始化线程数" + (i + 1) + thread.getName());
            thread.start();
        }
    }

    public static IThreadPool getThreadPool() {
        return getThreadPool(WORKER_NUMBER);
    }

    public static IThreadPool getThreadPool(int workerNumber) {
        if (workerNumber <= 0) {
            workerNumber = WORKER_NUMBER;
        }
        if (thradPool == null) {
            thradPool = new ThradPool(workerNumber);
        }
        return thradPool;
    }

    @Override
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Runnable[] tasks) {
        for (Runnable task : tasks
                ) {
            execute(task);
        }
    }

    @Override
    public void execute(List<Runnable> tasks) {
        for (Runnable task : tasks
                ) {
            execute(task);
        }
    }

    @Override
    public void destroy() {
        while (!taskQueue.isEmpty()) {
            System.out.println("等待....");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < WORKER_NUMBER; i++) {
            workerThreads[i].interrupt();
            workerThreads[i] = null;
        }
        thradPool = null;
        taskQueue.clear();
    }

    @Override
    public String toString() {
        return "工作线程数量为" + WORKER_NUMBER
                + "已完成的任务数" + sumCount +
                "等待任务数量" + taskQueue.size();
    }

    class WorkerThread extends Thread {

        /**
         * isRunning 表示当前线程属于活动可用状态
         */
        private boolean isRunning = true;

        public void setWorkerFlag() {
            isRunning = false;
        }

        @Override
        public void run() {
           Runnable runnable = null;
                try {
                    runnable = taskQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runnable != null) {
                    runnable.run();
                }
                sumCount++;
                runnable = null;
            }
        }
    }
