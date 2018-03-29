import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Mingming
 * @Description
 * @Date Created in 21:58 2018/3/1
 * @Modificd By
 */
public class ZookeeperCreateApiSysUsage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static void main(String []args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("zookeeper1:2181,zookeeper2:2181,zookeeper3:2181",5000,new ZookeeperCreateApiSysUsage());
        connectedSemaphore.await();
        String path1 = zooKeeper.create("/zk-test-ehpemeral","mingming".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create znode"+path1);
        String path2 = zooKeeper.create("/zk-test-ephemeral-","yueyue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode:"+path2);

    }
    @Override
    public void process(WatchedEvent watchedEvent) {
            if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                connectedSemaphore.countDown();
            }
    }
}
