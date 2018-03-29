package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Mingming
 * @Description
 * @Date Created in 23:30 2018/2/12
 * @Modificd By
 */
public class ThreadTest {
    public static void main(String[] args){
        List<String> stringList = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            stringList.add("String:"+i);
        }
        int threadNum = stringList.size()<5?stringList.size():5;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,5,300,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(3),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < threadNum; i++){
            executor.execute(new PrintStringThread(i,stringList,threadNum));
        }
        executor.shutdown();
    }
}

class PrintStringThread implements Runnable{
    private int num;

    private List<String> strList;

    private int threadNum;

    public PrintStringThread(int num,List<String> strList,int threadNum){
        this.num = num;
        this.strList = strList;
        this.threadNum = threadNum;
    }
    @Override
    public void run() {
     int length = 0;
     for(String str:strList){
         if(length%threadNum == num){
             System.out.println("线程编号"+num+",字符串："+str);
         }
         length++;
     }
    }
}