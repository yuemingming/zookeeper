package DP;

/**
 * @author Mingming
 * @Description
 * @Date Created in 11:42 2018/3/25
 * @Modificd By
 */
public class numsanjiao{
    static int MaxSum(int i,int j,int[][] array){
        int[][] MaxSum = new int[i][j];
        for(int s = 1; s <= i; s++) {
            MaxSum[j][s] = array[j][s];
        }
            for (int s = i-1; i>=1; --i)
                for(int m = 1; m <= s; m++){
                MaxSum[s][m] = Math.max(MaxSum[s+1][m],MaxSum[s+1][m+1])+array[s][m];
                }

        return MaxSum[1][1];
    }
}
