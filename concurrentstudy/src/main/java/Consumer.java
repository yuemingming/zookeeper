import com.lmax.disruptor.WorkHandler;

/**
 * @author Mingming
 * @Description
 * @Date Created in 19:07 2018/3/26
 * @Modificd By
 */
public class Consumer implements WorkHandler<PCData> {

    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId()+"Event:--"+pcData.getValue()*pcData.getValue()+"--");
    }
}
