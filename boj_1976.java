import java.util.*;
import java.io.*;

public class boj_1976 {
    static int N, M;
    static int[] parent;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }
    public static boolean check(int x, int y){
        x = find(x);
        y = find(y);
        if( x == y) return true;
        return false;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        int[] arr = new int[M];
        for(int i=0; i<N+1; i++)parent[i] = i;
        for(int i=0; i<N; i++){
            String[] temp = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                int t = Integer.parseInt(temp[j]);
                if(t == 0){
                    continue;
                }else if(t == 1){
                    union(i+1, j+1);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean flag = true;
        for(int i=0; i<M-1; i++){
            if(check(arr[i],arr[i+1])==false){
                flag= false;
            }
        }
        if(flag){
            System.out.print("YES");
        }else{
            System.out.print("NO");
        }
    }
}
