package pc;


import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import scala.collection.Seq;

import java.util.Properties;

public class Producer {
    private final static String TOPIC ="test";
    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list","zookeeper1:9092,zookeeper2:9092,zookeeper3:9090");
        properties.put("request.required.acks","1");
        kafka.producer.Producer<Integer,String> producer = new kafka.producer.Producer<Integer, String>(new ProducerConfig(properties));
        int messageNo = 1;
        while (true){
            String messageStr = new String("Message_"+messageNo);
            KeyedMessage<Integer, String> integerStringKeyedMessage = new KeyedMessage<Integer, String>(TOPIC, messageStr);
            producer.send((Seq<KeyedMessage<Integer, String>>) integerStringKeyedMessage);
        }
    }
}
