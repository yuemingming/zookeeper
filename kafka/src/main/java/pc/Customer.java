package pc;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import scala.util.parsing.combinator.testing.Str;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Mingming
 * @Description
 * @Date Created in 0:36 2018/2/12
 * @Modificd By
 */
public class Customer {
    private final static String TOPIC = "test";
    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("zookeeper.connect","zookeeper1:2181,zookeeper2:2181,zookeeper3:2181");
        properties.put("group.id","testGroup");
        properties.put("zookeeper.session.timeout.ms","400");
        properties.put("zookeeper.sync.time.ms","200");
        properties.put("auto.commit.interval.ms","1000");
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
        Map<String,Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC,new Integer(1));
        Map<String,List<KafkaStream<byte[],byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[],byte[]> stream = consumerMap.get(Customer.TOPIC).get(0);
        ConsumerIterator<byte[],byte[]> it = stream.iterator();
        while (it.hasNext()){
            System.out.println(new String(it.next().message()));
        }
    }
}
