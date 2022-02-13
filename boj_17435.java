import java.util.*;
import java.io.*;

public class boj_17435 {
    static final int ln = 18; //log2(500000) = 18.9xxxx
    static int N, M;
    static int[][] dp;
    public static void sparse_table(){
        for(int i=1; i<=ln; i++){
            for(int j=1; j<=N; j++){
                dp[i][j] = dp[i-1][dp[i-1][j]];
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[ln+1][N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            dp[0][i] = Integer.parseInt(st.nextToken());
        }
        sparse_table();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            for(int i=ln; i>=0; i--){
                int point = (1<<i);
                if(n>=point){
                    x = dp[i][x];
                    n-= point;
                    if(n==0)break;
                }
            }
            sb.append(x).append("\n");
        }
        System.out.print(sb);
    }
}
