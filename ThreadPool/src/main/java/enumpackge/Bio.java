package enumpackge;


import java.net.Socket;

/**
 * @author Mingming
 * @Description
 * @Date Created in 20:53 2018/3/12
 * @Modificd By
 */
public class Bio {

}
class ConnectIOnHandler extends Thread{
    private Socket socket;
    public ConnectIOnHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()&&!socket.isClosed()){
        }
    }
}