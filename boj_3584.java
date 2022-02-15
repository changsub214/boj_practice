import java.util.*;
import java.io.*;

public class boj_3584 {
    static int T, N, M, K;
    static int[] parent;
    static ArrayList<Integer>[] list;
    public static int depth(int x){
        int K = 1;
        while(x!=0){
            K++;
            x = parent[x];
        }
        return K;
    }
    public static int LCA(int a, int b, int a_depth, int b_depth){
        if(a_depth < b_depth){
            while(a_depth != b_depth){
                b_depth--;
                b = parent[b];
            }
        }else if(a_depth > b_depth){
            while(a_depth != b_depth){
                a_depth--;
                a = parent[a];
            }
        }
        while(a!=b){
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            list = new ArrayList[N+1];
            for(int i=0; i<N+1; i++){
                list[i] = new ArrayList<>();
            }
            parent = new int[N+1];
            for(int i=0; i<N-1; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[s].add(d);
                list[d].add(s);
                parent[d] = s;
            }
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int a_depth = depth(a);
            int b_depth = depth(b);
            sb.append(LCA(a,b,a_depth,b_depth)).append("\n");
        }
        System.out.print(sb);
    }
}
