package enumpackge;

import scala.util.parsing.combinator.testing.Str;

import java.util.*;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Mingming
 * @Description
 * @Date Created in 10:36 2018/3/17
 * @Modificd By
 */
public class Nike {
    /** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    static long min(int[] from, int[] to) {
        int[][] dp = new int[from.length][from.length];
        dp[0][2] = to[0]*to[1];
        dp[1][1] = to[0]*from[1];
        dp[2][0] = from[0]*from[1];
        int i = 2;
        int j = 3;

            return dp[to.length+1][to.length+1];

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        long res;

        int _from_size = 0;
        _from_size = Integer.parseInt(in.nextLine().trim());
        int[] _from = new int[_from_size];
        int _from_item;
        for(int _from_i = 0; _from_i < _from_size; _from_i++) {
            _from_item = Integer.parseInt(in.nextLine().trim());
            _from[_from_i] = _from_item;
        }

        int _to_size = 0;
        _to_size = Integer.parseInt(in.nextLine().trim());
        int[] _to = new int[_to_size];
        int _to_item;
        for(int _to_i = 0; _to_i < _to_size; _to_i++) {
            _to_item = Integer.parseInt(in.nextLine().trim());
            _to[_to_i] = _to_item;
        }

        res = min(_from, _to);
        System.out.println(String.valueOf(res));

    }
}