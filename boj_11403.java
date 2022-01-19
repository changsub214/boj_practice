import java.util.*;
import java.io.*;

public class boj_11403 {
    static int N;
    static boolean[][] arr;
    public static void floyd(){
        for(int k=1; k<N+1; k++){
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    if(arr[i][k] && arr[k][j]){
                        arr[i][j] = true;
                    }
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
        arr = new boolean[N+1][N+1];
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 0){
                    arr[i][j] = false;
                }else if(temp == 1){
                    arr[i][j] = true;
                }
            }
        }
        floyd();
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                if(arr[i][j]){
                    sb.append(1 + " ");
                    continue;
                }
                sb.append(0 + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
