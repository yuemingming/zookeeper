import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Mingming
 * @Description
 * @Date Created in 22:08 2018/3/1
 * @Modificd By
 */
public class ZookeeperCreateAPIASyncUsage implements Watcher{
    private static CountDownLatch conenctedSemaphore = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("zookeeper1:2181,zookeeper2:2181,zookeeper3:2181",5000,new ZookeeperCreateAPIASyncUsage());
        conenctedSemaphore.await();
        zooKeeper.create("/zk-test-ephemeral-"," ".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,new IStringCallback(),"I am context");
        Thread.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            conenctedSemaphore.countDown();
        }
    }
}
class IStringCallback implements AsyncCallback.StringCallback{

    @Override
    public void processResult(int rc, String path, Object ctx, String name1) {
        System.out.println("Create path result"+rc+","+path+","+ctx+",real path name"+name1);
    }
}
