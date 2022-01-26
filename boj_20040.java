import java.util.*;
import java.io.*;

public class boj_20040 {
    static int N, M;
    static int[] parent;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y)return true;
        if(x!=y){
            if(x<y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
        return false;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for(int i=0; i<N; i++){
            parent[i] = i;
        }
        int res = 0;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if(union(n1,n2) && res == 0){
                res = i+1;
            }
        }
        System.out.print(res);
    }
}
