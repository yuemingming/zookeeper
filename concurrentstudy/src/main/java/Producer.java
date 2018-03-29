import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mingming
 * @Description
 * @Date Created in 19:11 2018/3/26
 * @Modificd By
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    public void pushData(ByteBuffer buffer){
        long sequence = ringBuffer.next();
        PCData pcData = ringBuffer.get(sequence);
        pcData.setValue(buffer.getLong());
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();
        int bufferSize = 1024;
        Disruptor<PCData> disruptor = new Disruptor<PCData>(factory,bufferSize,executorService, ProducerType.MULTI,new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new Consumer(),
                                            new Consumer(),
                                            new Consumer(),
                                            new Consumer());
        disruptor.start();
        RingBuffer<PCData> riingBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(riingBuffer);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for(long i = 0; true; i++) {
            buffer.putLong(0,i);
            producer.pushData(buffer);
            Thread.sleep(100);
            System.out.println("add data"+i);
        }
    }
}
