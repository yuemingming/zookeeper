/**
 * @author Mingming
 * @Description
 * @Date Created in 17:29 2018/3/26
 * @Modificd By
 */
public class DeadLock extends Thread{
    protected Object tool;
    static Object fork1 = new Object();
    static Object fork2 = new Object();

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public DeadLock(Object tool) {
        this.tool = tool;
        if(tool == fork1){
            this.setName("哲学家A");
        }else {
            this.setName("哲学家B");
        }
    }

    @Override
    public void run() {
        if(tool == fork1){
            synchronized (fork1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork2){
                    System.out.println("哲学家A开始吃饭了");
                }
            }
        }
        if(tool == fork2){
            synchronized (fork2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork1){
                    System.out.println("哲学家B开始吃饭了");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock A = new DeadLock(fork1);
        DeadLock B = new DeadLock(fork2);
        A.start();
        B.start();
        Thread.sleep(1000);

    }
}
