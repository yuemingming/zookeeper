/**
 * @author Mingming
 * @Description
 * @Date Created in 20:46 2018/3/26
 * @Modificd By
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        Thread.sleep(2000);
        System.out.println("数据="+data.getResult());
    }
}
