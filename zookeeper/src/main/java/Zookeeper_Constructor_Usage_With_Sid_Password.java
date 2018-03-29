import org.apache.kafka.common.metrics.stats.Count;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Mingming
 * @Description
 * @Date Created in 21:44 2018/3/1
 * @Modificd By
 */
public class Zookeeper_Constructor_Usage_With_Sid_Password implements Watcher{
   private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
   public static void main(String[] args) throws IOException, InterruptedException {
       ZooKeeper zooKeeper = new ZooKeeper("zookeeper1:2181,zookeeper2:2181,zookeeper3:2181",5000,new Zookeeper_Constructor_Usage_With_Sid_Password());
       System.out.println(zooKeeper.getState());
       try {
           connectedSemaphore.await();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       long sessionId = zooKeeper.getSessionId();
       byte[] passwd = zooKeeper.getSessionPasswd();
       zooKeeper = new ZooKeeper("zookeeper1:2181,zookeeper2:2181,zookeeper3:2181",5000,new Zookeeper_Constructor_Usage_With_Sid_Password(),sessionId,passwd);
        Thread.sleep(Integer.MAX_VALUE);
   }
   @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event"+watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
