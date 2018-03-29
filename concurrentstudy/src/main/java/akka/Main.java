package akka;

import java.util.Scanner;

/**
 * @author Mingming
 * @Description
 * @Date Created in 20:10 2018/3/27
 * @Modificd By
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String NK = scanner.nextLine();
//        String[] strs = NK.split(" ");
//        int n = Integer.valueOf(strs[0]);
//        int k = Integer.valueOf(strs[1]);
//        int count = 0;
//        if(k==0)
//            System.out.println(n*n);
//        else {
//            for (int y = k + 1; y <= n; y++) {
//                count += (y - k) * n / y;
//                if (n % y - k >= 0)
//                    count += (n % y - k + 1);
//            }
//            System.out.println(count);
//        }
        String str = scanner.nextLine();
        String[] words = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length-1; i>=0;i--){
            if(i != words.length-1)
                stringBuilder.append(" ");
            stringBuilder.append(words[i]);
        }
        System.out.println(stringBuilder);
    }
}
