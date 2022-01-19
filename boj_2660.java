import java.util.*;
import java.io.*;

public class boj_2660 {
    static int N, INF=98754321;
    static int[][] arr;
    static int[] res;
    public static void floyd(){
        for(int k=1; k<N+1; k++){
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    arr[i][j] = Math.min(arr[i][j],arr[i][k] + arr[k][j]);
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        res = new int[N+1];
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
               if(i==j)continue;
               arr[i][j] = INF;
            }
        }
        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a==-1 && b==-1)break;
            arr[a][b] = 1;
            arr[b][a] = 1;
        }
        floyd();

        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                res[i] = Math.max(arr[i][j],res[i]);
            }
        }
        int minimum = Integer.MAX_VALUE ;
        for(int i=1; i<N+1; i++){
            minimum = Math.min(minimum, res[i]);
        }
        sb.append(minimum + " ");
        int cnt =0;
        for(int i=1; i<N+1; i++){
            if(res[i] == minimum){
                cnt++;
            }
        }
        sb.append(cnt + "\n");
        for(int i=1; i<N+1; i++){
            if(res[i] == minimum){
                sb.append(i + " ");
            }
        }
        System.out.print(sb);
    }
}
