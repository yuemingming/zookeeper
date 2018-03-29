import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @author Mingming
 * @Description
 * @Date Created in 13:24 2018/3/27
 * @Modificd By
 */
public class jdk8demo {
    public static void main(String[] args) {
//        int[] arr = {1,3,5,4,7,8,9,10};
//        Arrays.stream(arr).map((x)->x=x+1).forEach(System.out::println);
//        List<Integer> numbers = Arrays.asList(1,23,4,5,6);
//        numbers.forEach(System.out::println);
//        final int num = 2;
//        Function<Integer,Integer> stringConverter = (from)->from*num;
//        System.out.println(stringConverter.apply(3));
//        long count = IntStream.range(1, 1000000).filter(jdk8demo::isPrime).count();
//        System.out.println(count );
        int[] arr = new int[10000000];
        Arrays.parallelSort(arr);
    }
    public static boolean isPrime(int number){
        int tmp = number;
        if(tmp < 2){
            return false;
        }
        for (int i = 2; Math.sqrt(tmp)>=i; i++){
            if(tmp % i == 0){
                return false;
            }
        }
        return true;
    }
}
