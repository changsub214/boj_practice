import java.util.*;
import java.io.*;

public class boj_1412 {
    static int N;
    static boolean[] visit;
    static boolean flag;
    static String[][] arr;
    public static void topological_sort(int v){
        if(visit[v]){
            flag = true; //재방문 하므로 싸이클이 돌았다는 뜻
            return;
        }
        visit[v] = true;
        for(int i=0; i<N; i++){
            if(arr[v][i].equals("Y") && arr[i][v].equals("N")){
                arr[v][i] = "N";
                topological_sort(i);
            }
        }
        visit[v] = false;

    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new String[101][101];
        visit = new boolean[101];
        for(int i=0; i<N; i++){
            String[] temp = br.readLine().split("");
            for(int j=0; j<N; j++){
                arr[i][j] = temp[j];
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j].equals("Y") && arr[j][i].equals("N")){
                    topological_sort(i);
                    arr[i][j] = "N";
                }
                if(flag){
                    System.out.print("NO");
                    System.exit(0);
                }
            }
        }
        System.out.print("YES");
    }
}
