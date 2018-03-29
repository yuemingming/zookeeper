package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Mingming
 * @Description
 * @Date Created in 15:38 2018/3/27
 * @Modificd By
 */
public class HelloMainSimple {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a = actorSystem.actorOf(Props.create(HelloWorld.class),"helloWorld");
        System.out.println("HelloWorld Actor Path:"+a.path());
    }
}
