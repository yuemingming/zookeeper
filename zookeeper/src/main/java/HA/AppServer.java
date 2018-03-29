package HA;

import org.apache.zookeeper.*;

/**
 * @author Mingming
 * @Description
 * @Date Created in 13:07 2018/3/29
 * @Modificd By
 */
public class AppServer {
    private String groupNode = "sgroup";
    private String subNode = "sub";
    public void connectZookeeper(String address) throws Exception{
        ZooKeeper zk = new ZooKeeper("192.168.7.3:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        String createPath = zk.create("/"+groupNode+"/"+subNode,address.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("create:"+createPath);
    }
    public void handle() throws InterruptedException{
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            System.out.println("The first argument must be server address");
            System.exit(1);
        }
        AppServer as = new AppServer();
        as.connectZookeeper(args[0]);
        as.handle();
    }
}
