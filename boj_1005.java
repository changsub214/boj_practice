import java.util.*;
import java.io.*;

public class boj_1005 {
    static class Node{
        int vertex;
        public Node(int vertex){
            this.vertex = vertex;
        }
    }
    static int T, N, K;
    static int[] D;
    static int[] edge_num;
    static ArrayList<Node>[] list;
    public static int topological_sort(int find){
        Queue<Integer> q = new LinkedList<>();
        int[] arr = new int[N+1];
        for(int i=1; i<N+1; i++){
            arr[i] = D[i];
            if(edge_num[i]==0)q.add(i);
        }
        while(!q.isEmpty()){
            int v = q.poll();
            int len = list[v].size();
            for(int i=0; i<len; i++){
                int vertex = list[v].get(i).vertex;
                arr[vertex] = Math.max(arr[vertex], arr[v]+D[vertex]);
                edge_num[vertex]--;
                if(edge_num[vertex]==0){
                    q.add(vertex);
                }
            }
        }
        return arr[find];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            D = new int[N+1];
            edge_num = new int[N+1];
            list = new ArrayList[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<N+1; i++){
                D[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=0; i<N+1; i++){
                list[i] = new ArrayList<>();
            }
            while(K-->0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                edge_num[d]++;
                list[s].add(new Node(d));
            }
            st = new StringTokenizer(br.readLine());
            int res = topological_sort(Integer.parseInt(st.nextToken()));
            System.out.println(res);
        }
    }
}
