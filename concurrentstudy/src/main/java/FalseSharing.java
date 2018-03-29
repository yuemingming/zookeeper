/**
 * @author Mingming
 * @Description
 * @Date Created in 19:30 2018/3/26
 * @Modificd By
 */
public class FalseSharing implements Runnable{
    public final static int NUM_THREADS=2;//change
    public final static long ITERATIONS = 500L*1000L*1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        runtest();
        System.out.println("duration = "+(System.currentTimeMillis()-start));

    }
    private static void runtest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
            threads[i].start();
        }
        for (Thread t:threads
             ) {
            t.join();
        }
    }
    public void run() {
        long i = ITERATIONS +1;
        while (0!= --i){
            longs[arrayIndex].value = i;
        }
    }
    static class VolatileLong{
        public volatile long value = 0L;
//        public long p1,p2,p3,p4,p5,p6,p7;
    }
}

