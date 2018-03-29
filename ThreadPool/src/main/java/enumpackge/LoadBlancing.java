package enumpackge;


import scala.util.parsing.combinator.testing.Str;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author Mingming
 * @Description
 * @Date Created in 9:34 2018/3/18
 * @Modificd By
 */
public class LoadBlancing   {
    public static HashMap<String,Integer> serverWeightMap = new HashMap<String, Integer>();
    static {
        serverWeightMap.put("192.168.1.100",1);
        serverWeightMap.put("192.168.1.101",1);
        //权重为4
        serverWeightMap.put("192.168.1.102",4);
        serverWeightMap.put("192.168.1.103",1);
        serverWeightMap.put("192.168.1.104",1);
        //权重为3
        serverWeightMap.put("192.168.1.105",3);
        serverWeightMap.put("192.168.1.106",1);
        //权重为2
        serverWeightMap.put("192.168.1.107",2);
        serverWeightMap.put("192.168.1.108",1);
        serverWeightMap.put("192.168.1.109",1);
        serverWeightMap.put("192.168.1.110",1);
    }
    public static void main(String[] args){
        while (true){
//            System.out.println( WeightRoundRobin.getServer());
            System.out.println(WeightRandom.getServer());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//轮询法

/**
 * 优点：试图做到请求的绝对均衡
 * 缺点：为了做到请求转移的绝对均衡，必须付出相当大的代价，
 * 因为为了保证pos变量修改的互斥性，需要引入重量级锁sunchronized,这回导致该阶段轮询代码的并发量明显下降
 */
class RoundRobRin{
    private static Integer pos = 0;
    public static String  getServer(){
        //重建一个Map，避免服务器上下线导致的并发问题
        Map<String ,Integer> serverMap = new HashMap<String, Integer>(LoadBlancing.serverWeightMap);
        //取得ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>(keySet);
        String server = null;
        synchronized(pos){
            if(pos >= keyList.size()){
                pos = 0;
            }
            server = keyList.get(pos);
            pos++;
            /*
            没有实现权重的响应关系？
             */
        }
        return server;

    }
}
//随机地址法

/**
 * 基于概率统计的理论，吞吐量越大，随机算法的效果越接近于轮询算法的实现。
 */
class Random{
    public static String getServer(){
        //重建一个Map，避免服务器上下线导致的并发问题
        Map<String,Integer> serverMap = new HashMap<String, Integer>(LoadBlancing.serverWeightMap);
        ArrayList<String> keyList = new ArrayList<String>(serverMap.keySet());
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(keyList.size());
        return keyList.get(randomPos);
    }
}
//原地址哈希法

/**
 * 优点：保证了客户端的IP地址将会被hash到同一台后端服务器，
 * 直到后端服务器变更。根据此特性可以在服务消费者与服务提供者之间建立session
 * 缺点：除非集群中服务器的非常稳定，基本上不会上下线，否则一旦有服务器上线，下线，
 * 那么通过源地址hash算法路由到服务器上下线之前的服务器的概率非常低，如果是session则取不到session，如果是缓存问题
 * 则可能引发雪崩。
 */
class Hash{
    public static String getServer(){
        //重建一个map，避免服务器上下线导致的并发问题
        Map<String,Integer> serverMap = new HashMap<String, Integer>(LoadBlancing.serverWeightMap);
        //取得ip地址的list
        ArrayList<String> keyList = new ArrayList<String>(serverMap.keySet());
        String remoteIp = "127.0.0.1";
        int hashCode = remoteIp.hashCode();
        int serverListSize = keyList.size();
        int serverPos = hashCode%serverListSize;
        return keyList.get(serverPos);
    }
}

/**加权轮询
 *
 */
class WeightRoundRobin{
    private static Integer pos = 0;
    public static String  getServer(){
        //重建一个Map，避免服务器上下线导致的并发问题
        Map<String ,Integer> serverMap = new HashMap<String, Integer>(LoadBlancing.serverWeightMap);
        //取得ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String ip = iterator.next();
            int weight = serverMap.get(ip);
            while (weight-->0){
                keyList.add(ip);
            }
        }
        String server = null;
        synchronized(pos){
            if(pos >= keyList.size()){
                pos = 0;
            }
            server = keyList.get(pos);
            pos++;
            /*
            没有实现权重的响应关系？
             */
        }
        return server;

    }
}
/**加权随机算法
 * 基于概率统计的理论，吞吐量越大，随机算法的效果越接近于轮询算法的实现。
 */
class WeightRandom{
    public static String getServer(){
        //重建一个Map，避免服务器上下线导致的并发问题
        Map<String,Integer> serverMap = new HashMap<String, Integer>(LoadBlancing.serverWeightMap);
        //取得ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String ip = iterator.next();
            int weight = serverMap.get(ip);
            while (weight-->0){
                keyList.add(ip);
            }
        }
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(keyList.size());
        return keyList.get(randomPos);
    }
}