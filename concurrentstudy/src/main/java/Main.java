//import javax.xml.bind.SchemaOutputResolver;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * @author Mingming
// * @Description
// * @Date Created in 19:21 2018/3/24
// * @Modificd By
// */
//public class Main {
//    public static void main(String[] args) {
//      int N = 3,K = 3,H = 2;
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        String[] strs = str.split(" ");
//        N = Integer.valueOf(strs[0]);
//        K = Integer.valueOf(strs[1]);
//        H = Integer.valueOf(strs[2]);
//        List<Integer> list = new ArrayList<>();
//        while (N-->0){
//            list.add(Integer.valueOf(scanner.nextLine()));
//        }
//        list.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1-o2;
//            }
//        });
//        int[] array = new int[list.size()];
//        int i = 0;
//        for (Integer a:list
//             ) {
//            array[i++] = a;
//        }
//      int sum = 0;
//      int index = 0;
//      while (K-->0){
//          while (index<array.length&&sum+H>=array[index]){
//              index++;
//          }
//          sum = sum + (array[--index]-sum)*2;
//          if(sum>=array[array.length-1]){
//              break;
//          }
//      }
//        System.out.println(sum);
//    }
//}
