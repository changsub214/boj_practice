import java.util.*;
import java.io.*;

public class boj_11404 {
    static int V, E, INF=1000000000;
    static int[][] arr;
    public static void floydwarshall(){
        for(int k=1; k<V+1; k++){ //reference node
            for(int i=1; i<V+1; i++){ //start node
                for(int j=1; j<V+1; j++){ //end node
                    arr[i][j] = Math.min(arr[i][j] , arr[i][k] + arr[k][j]);
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        arr = new int[V+1][V+1];
        for(int i=1; i<V+1; i++){
            for(int j=1; j<V+1; j++){
                if(i==j) {
                    arr[i][j] = 0;
                    continue;
                }
                arr[i][j] = INF;
            }
        }
        while(E-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[s][d] = Math.min(arr[s][d],c);
        }
        floydwarshall();
        for(int i=1; i<V+1; i++){
            for(int j=1; j<V+1; j++){
                if(arr[i][j] >= INF){
                    arr[i][j] = 0;
                    sb.append(arr[i][j]+" ");
                    continue;
                }
                sb.append(arr[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
