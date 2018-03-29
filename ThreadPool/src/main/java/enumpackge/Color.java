package enumpackge;

import org.junit.Test;

/**
 * @author Mingming
 * @Description
 * @Date Created in 15:15 2018/2/23
 * @Modificd By
 */
public enum  Color {
    RED,GREEN,BLANK,YELLOW
}
enum Signal{
    GREEN,YELLOW,RED
}
class TrafficLight{
    Signal color = Signal.RED;
    public void change(){
        switch (color){
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
            default:
                break;
        }
    }
}
