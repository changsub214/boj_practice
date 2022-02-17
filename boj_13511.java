import java.util.*;
import java.io.*;

public class boj_13511 {
    static class Node{
        int d;
        long c;
        public Node(int d, long c){
            this.d = d;
            this.c = c;
        }
    }
    static int N, M, K;
    static int[] depth;
    static long[] cost;
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
            if( (1<<i) <= depth[a] - depth[b]){
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
        cost = new long[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        K=0;
        for(int i=1; i<N+1; i<<=1){
            K++;
        }
        parent = new int[N+1][K];
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d, c));
            list[d].add(new Node(s, c));
        }
        dfs(1, 1);
        make_parent();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int root = LCA(n1,n2);
            if(num==1){
                sb.append(cost[n1]+cost[n2]-2*cost[root]).append("\n");
            }else{
                int order = Integer.parseInt(st.nextToken());
                int n1_depth = depth[n1];
                int n2_depth = depth[n2];
                int mid = n1_depth - depth[root] + 1;
                int res = 0;
                if(mid == order) {
                    res = root;
                }else if(mid> order){
                    order--;
                    res = n1;
                    for(int i=0; i<K; i++){
                        if((order & ( 1<<i)) !=0) {
                            order -= (1<<i);
                            res = parent[res][i];
                        }
                    }
                }else{
                    order = mid + n2_depth - depth[root] - order;
                    res = n2;
                    for(int i=0; i<K; i++){
                        if((order & ( 1<<i)) !=0) {
                            order -= (1<<i);
                            res = parent[res][i];
                        }
                    }
                }
                sb.append(res).append("\n");
            }
        }
        System.out.print(sb);
    }
}
