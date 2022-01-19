import java.util.*;
import java.io.*;

public class boj_2458 {
    static int N, M, INF=987654321;
    static boolean[][] arr;
    static boolean[][] arr2;
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
    public static void floyd2(){
        for(int k=1; k<N+1; k++){
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    if(arr2[i][k] && arr2[k][j]){
                        arr2[i][j] = true;
                    }
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new boolean[N+1][N+1];
        arr2 = new boolean[N+1][N+1];

        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int shorter = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());
            arr[shorter][taller] = true;
            arr2[taller][shorter] = true;
        }
        floyd();
        floyd2();
        int cnt = 0;
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                arr[i][j] |= arr2[i][j];
            }
        }
       /* for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }*/


        outer : for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                if(i==j)continue;
                if(!arr[i][j]){
                    continue outer;
                }
            }
            cnt++;
        }
        System.out.print(cnt);
    }
}
