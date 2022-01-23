import java.util.*;
import java.io.*;

public class boj_11049 {
    static int[][] arr;
    static int[][] dp;
    public static int matrix(int s, int e){
        if(s == e) return 0; // only one matrix
        if(dp[s][e] != Integer.MAX_VALUE)return dp[s][e];
        for(int i=s; i<e; i++){
            int c = matrix(s, i) + matrix(i+1, e) + arr[s][0] * arr[i][1] * arr[e][1];
            dp[s][e] = Math.min(dp[s][e],c);
        }
        return dp[s][e];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        dp = new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(matrix(0, n-1));

    }
}
