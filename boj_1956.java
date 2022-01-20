import java.util.*;
import java.io.*;

public class boj_1956 {
    static int V,E;
    static final int INF = 1000000000;
    static int[][] arr;
    public static void floyd_warshall(int s, int d){
        for(int k=1; k<V+1; k++){
            for(int i=1; i<V+1; i++){
                for(int j=1; j<V+1; j++){
                    arr[i][j] = Math.min(arr[i][j],arr[i][k] + arr[k][j]);
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        arr = new int[V+1][V+1];
        for(int i=1; i<V+1; i++){
            for(int j=1; j<V+1; j++){
                if(i==j)continue;
                arr[i][j] = INF;
            }
        }
        while(E-- > 0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[s][d] = c;
        }
        floyd_warshall(1,V);
        int res = INF;
        for(int i=1; i<V+1; i++){
            for(int j=1; j<V+1; j++){
                if(i==j)continue;
                if(arr[i][j]!=INF && arr[j][i]!=INF){
                    res = Math.min(res, arr[i][j] + arr[j][i]);
                }
            }
        }
        if(res == INF){
            System.out.print(-1);
        }else{
            System.out.print(res);
        }
    }
}
