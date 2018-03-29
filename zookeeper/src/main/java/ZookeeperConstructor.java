import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Mingming
 * @Description
 * @Date Created in 21:30 2018/3/1
 * @Modificd By
 */
public class ZookeeperConstructor implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("zookeeper1:2181,zookeeper2:2181,zookeeper3:2181",5000,new ZookeeperConstructor());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event:"+watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
