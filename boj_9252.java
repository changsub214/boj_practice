import java.util.*;
import java.io.*;

public class boj_9252 {
    static int[][] dp;
    static String[] one;
    static String[] two;
    public static int LCS(){
        for(int i=0; i<one.length; i++){
            for(int j=0; j<two.length; j++){
                if(one[i].equals(two[j])){
                    dp[i+1][j+1] = dp[i][j]+1;
                }else{
                    dp[i+1][j+1] = Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[one.length][two.length];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        one = br.readLine().split("");
        two = br.readLine().split("");
        dp = new int[one.length+1][two.length+1];
        int res = LCS();
        //backtracking
        int i = one.length;
        int j = two.length;
        StringBuilder sb = new StringBuilder();
        while(i!=0 && j!=0){
            if(one[i-1].equals(two[j-1])){
                sb.insert(0, one[i-1]);
                i--;
                j--;
            }else if(dp[i-1][j]==dp[i][j]){
                i--;
            }else if(dp[i][j-1]==dp[i][j]){
                j--;
            }
        }
        System.out.println(res);
        System.out.print(sb);
    }
}
