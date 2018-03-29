import com.lmax.disruptor.EventFactory;

/**
 * @author Mingming
 * @Description
 * @Date Created in 19:11 2018/3/26
 * @Modificd By
 */
public class PCDataFactory implements EventFactory<PCData>{
    public PCData newInstance() {
        return new PCData();
    }
}
