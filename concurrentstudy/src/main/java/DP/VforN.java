package DP;

import javax.sound.midi.SoundbankResource;
import java.util.Scanner;

/**
 * @author Mingming
 * @Description
 * @Date Created in 12:44 2018/3/25
 * @Modificd By
 */
public class VforN{
    private final static int MAX = 41;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n,i,input;
        int[] sum = new int[MAX];
        for (int j = 0; j <MAX ; j++) {
            sum[j] = 0;
        }
        n = Integer.valueOf(scanner.nextLine());
        while (n-->0){
            input = Integer.valueOf(scanner.nextLine());
            for (i = 40; i >= 1; i--){
                if(sum[i] > 0 && i+input <= 40){
                    sum[i+input]+=sum[i];
                }
            }
            sum[input]++;
        }
        System.out.println(sum[40]);
    }
}
