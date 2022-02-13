import java.util.*;
import java.io.*;

public class boj_3176 {
    static class Node{
        int d;
        int c;
        public Node(int d, int c){
            this.d = d;
            this.c = c;
        }
    }
    static int N, M, K;
    static boolean[] visit;
    static int[] depth;
    static int[][] parent;
    static int[][] min;
    static int[][] max;
    static ArrayList<Node>[] list;
    public static void dfs(int idx, int cnt){
        visit[idx] = true;
        depth[idx] = cnt;
        int len = list[idx].size();
        for(int i=0; i<len; i++){
            Node node = list[idx].get(i);
            if(visit[node.d])continue;
            if(depth[node.d] == 0){
                dfs(node.d , cnt+1);
                parent[node.d][0] = idx;
                min[node.d][0] = node.c;
                max[node.d][0] = node.c;
            }
        }
        return;
    }
    public static void make_parent(){
        for(int i=1; i<M; i++){
            for(int j=1; j<N+1; j++){
                parent[j][i] = parent[parent[j][i-1]][i-1];
                min[j][i] = Math.min(min[j][i-1], min[parent[j][i-1]][i-1]);
                max[j][i] = Math.max(max[j][i-1], max[parent[j][i-1]][i-1]);
            }
        }
    }
    public static Node LCA(int a, int b){
        int res_max = Integer.MIN_VALUE;
        int res_min = Integer.MAX_VALUE;
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }
        for(int i=M-1; i>=0; i--){
            if((1<<i) <= depth[a] - depth[b]){
                res_min = Math.min(res_min, min[a][i]);
                res_max = Math.max(res_max, max[a][i]);
                a = parent[a][i];
            }
        }
        if(a==b)return new Node(res_min, res_max);
        for(int i=M-1; i>=0; i--){
            if(parent[a][i] != parent[b][i]){
                res_min = Math.min(res_min, Math.min(min[a][i], min[b][i]));
                res_max = Math.max(res_max, Math.max(max[a][i], max[b][i]));
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        res_min = Math.min(res_min, Math.min(min[a][0], min[b][0]));
        res_max = Math.max(res_max, Math.max(max[a][0], max[b][0]));
        return new Node(res_min, res_max);
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        M = 0;
        for(int i=1; i<N+1; i<<=1){
            M++;
        }
        depth = new int[N+1];
        visit = new boolean[N+1];
        parent = new int[N+1][M];
        max = new int[N+1][M];
        min = new int[N+1][M];
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
        K = Integer.parseInt(st.nextToken());
        while(K-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Node res = LCA(x, y);
            sb.append(res.d + " " + res.c).append("\n");
        }
        System.out.print(sb);
    }
}
