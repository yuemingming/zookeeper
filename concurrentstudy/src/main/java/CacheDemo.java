//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
///**
// * @author Mingming
// * @Description
// * @Date Created in 13:27 2018/3/24
// * @Modificd By
// */
//public class CacheDemo {
//    private Map<String,Object> cache = new HashMap<>();
//    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//    public static void main(String[] args) {
//
//    }
//    public Object getData(String key){
//        readWriteLock.readLock().lock();
//        Object value = null;
//        try {
//            value = cache.get(key);
//            if (value == null) {
//                readWriteLock.readLock().unlock();
//                readWriteLock.writeLock().lock();
//                value = "a";
//                cache.put(key,value);
//                readWriteLock.writeLock().unlock();
//                readWriteLock.readLock().lock();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            readWriteLock.readLock().unlock();
//        }
//        return value;
//    }
//}
