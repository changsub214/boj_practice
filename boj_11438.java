import java.util.*;
import java.io.*;

public class boj_11438 {
    static int N, M, K;
    static int[] depth;
    static int[][] parent;
    static ArrayList<Integer>[] list;

    public static void dfs(int i, int cnt){
        depth[i] = cnt;
        int len = list[i].size();
        for(int j=0; j<len; j++){
            int nx = list[i].get(j);
            if(depth[nx] == 0){
                dfs(nx, cnt+1);
                parent[nx][0] = i;
            }
        }
        return;
    }
    public static void make_parent(){
        for(int i=1; i<K; i++){
            for(int j=1; j<N+1; j++){
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }
    public static int LCA(int a, int b){
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int i=K-1; i>=0; i--){
            if(Math.pow(2, i) <= depth[a] - depth[b]){
                a = parent[a][i];
            }
        }

        if(a==b)return a;

        for(int i= K-1; i>=0; i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];

        for(int i=1; i<N+1; i*=2){
            K+=1;
        }

        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        depth = new int[N+1];
        parent = new int[N+1][K];

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        dfs(1, 1);
        make_parent();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a,b)).append("\n");
        }

        System.out.print(sb);
    }
}
