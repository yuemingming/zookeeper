import java.net.*;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Mingming
 * @Description
 * @Date Created in 20:56 2018/3/26
 * @Modificd By
 */
public class NIOSocketDemo{
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();
    public static Map<Socket,Long> time_start = new HashMap<Socket, Long>(10240);
    private void startServer() throws Exception{
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(),8000);
        ssc.socket().bind(isa);
        SelectionKey acceptKey = ssc.register(selector,SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            long e = 0;
            while (iterator.hasNext()){
                SelectionKey sk = iterator.next();
                iterator.remove();
                if(sk.isAcceptable()){
                    doAccept(sk);
                }else if(sk.isValid()&&sk.isReadable()){
                    if(!time_start.containsKey(((SocketChannel)sk.channel()).socket()))
                        time_start.put(((SocketChannel)sk.channel()).socket(),System.currentTimeMillis());
                    doRead(sk);
                }else if(sk.isValid()&&sk.isWritable()){
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time_start.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend:"+(e-b)+"ms");
                }
            }
        }
    }
    private void doAccept(SelectionKey sk){
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) sk.channel();
        SocketChannel clientchannel;
        try {
            clientchannel = serverSocketChannel.accept();
            clientchannel.configureBlocking(false);
            SelectionKey clientKey = clientchannel.register(selector,SelectionKey.OP_READ);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void doRead(SelectionKey sk){

    }
    private void doWrite(SelectionKey sk){

    }

}
