package akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author Mingming
 * @Description
 * @Date Created in 17:10 2018/3/27
 * @Modificd By
 */
public class MyWorker extends UntypedActor{
    private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);
    public static enum Msg{
        WORKING,DONE,CLOSE;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("MyWorker is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("MyWorker is stoping");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if(message == Msg.WORKING){
            System.out.println("I am working");
        }
        if(message == Msg.DONE){
            System.out.println("Stop working");
        }
        if(message == Msg.CLOSE){
            System.out.println("I will shutdown");
            getSender().tell(Msg.CLOSE,getSelf());
            getContext().stop(getSelf());
        }else {
            unhandled(message);
        }
    }
}
