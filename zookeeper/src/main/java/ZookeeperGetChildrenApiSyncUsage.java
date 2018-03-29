import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Mingming
 * @Description
 * @Date Created in 22:34 2018/3/1
 * @Modificd By
 */
public class ZookeeperGetChildrenApiSyncUsage implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/";
        zk = new ZooKeeper("zookeeper1:2181,zookeeper2:2181,zookeeper3:2181",5000,new ZookeeperGetChildrenApiSyncUsage());
        connectedSemaphore.await();
        List<String> children = zk.getChildren(path, true);
        System.out.println(children);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
