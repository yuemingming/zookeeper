package akka;

import akka.actor.UntypedActor;

/**
 * @author Mingming
 * @Description
 * @Date Created in 15:32 2018/3/27
 * @Modificd By
 */


public class Greeter extends UntypedActor{
    public static enum Msg{
        GREET,DONE;
    }
    @Override
    public void onReceive(Object message) throws Exception {
        if(message == Msg.GREET){
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE,getSelf());
        }else {
            unhandled(message);
        }
    }
}
