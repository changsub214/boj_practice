import java.util.*;
import java.io.*;

public class boj_8012 {
    static int N, M, K, result = 0;
    static int[] arr;
    static int[] depth;
    static int[][] parent;
    static ArrayList<Integer>[] list;
    public static void dfs(int idx, int cnt){
        depth[idx] = cnt;
        int len = list[idx].size();
        for(int i=0; i<len; i++){
            int nx = list[idx].get(i);
            if(depth[nx] == 0){
                dfs(nx, cnt+1);
                parent[nx][0] = idx;
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
            if((1<<i) <= depth[a] - depth[b]){
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
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        arr = new int[N+1];
        depth = new int[N+1];
        K=0;
        for(int i=1; i<N+1; i<<=1){
            K++;
        }
        parent = new int[N+1][K];
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            list[v1].add(v2);
            list[v2].add(v1);
        }
        dfs(1, 1);
        make_parent();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        int z = 0;
        int loop = M;
        while(loop-- >0){
            st = new StringTokenizer(br.readLine());
            arr[z] = Integer.parseInt(st.nextToken());
            z++;
        }
        for(int i=1; i<M; i++){
            int a = arr[i-1];
            int b = arr[i];
            result += depth[a] + depth[b] - 2 * depth[LCA(a,b)];
        }
        System.out.print(result);
    }
}
