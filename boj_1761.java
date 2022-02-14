import java.util.*;
import java.io.*;

public class boj_1761 {
    static class Node{
        int d;
        int c;
        public Node(int d, int c){
            this.d = d;
            this.c = c;
        }
    }
    static int N, M, K = 0;
    static int[] depth;
    static int[] cost;
    static int[][] parent;
    static ArrayList<Node>[] list;
    public static void dfs(int idx, int cnt){
        depth[idx] = cnt;
        int len = list[idx].size();
        for(int i=0; i<len; i++){
            Node nx = list[idx].get(i);
            if(depth[nx.d] == 0){
                cost[nx.d] = cost[idx] + nx.c;
                dfs(nx.d, cnt+1);
                parent[nx.d][0] = idx;
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
            if((1<<i) <= depth[a]-depth[b]){
                a = parent[a][i];
            }
        }
        if(a==b)return a;
        for(int i=K-1; i>=0; i--){
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
        depth = new int[N+1];
        for(int i=1; i<N+1; i<<=1){
            K++;
        }
        parent = new int[N+1][K];
        cost = new int[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d, c));
            list[d].add(new Node(s, c));
        }
        dfs(1, 1);
        make_parent();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(cost[a]+cost[b]-2*cost[LCA(a,b)]).append("\n");
        }
        System.out.print(sb);
    }
}
