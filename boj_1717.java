import java.util.*;
import java.io.*;

public class boj_1717 {
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
            if(x < y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }
    public static boolean check(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y){
            return true;
        }
        return false;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=0; i< parent.length; i++){
            parent[i] = i;
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 0){ //union
                union(b, c);
            }else if(a == 1){ //check
                if(check(b, c)){
                    sb.append("YES").append("\n");
                }else{
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}
