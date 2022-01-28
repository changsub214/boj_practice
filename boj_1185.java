import java.util.*;
import java.io.*;

public class boj_1185 {
    static class Node implements Comparable<Node>{
        int s;
        int d;
        int c;
        public Node(int s, int d, int c){
            this.s = s;
            this.d = d;
            this.c = c;
        }
        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
    static int N, P, res = 0;
    static int[] vertex_cost;
    static int[] parent;
    static ArrayList<Node> list;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y)parent[y]= x;
            else parent[x] =y;
        }
    }
    public static void kruskal(){
        int len =list.size();
        for(int i=0; i<len; i++){
            Node node = list.get(i);
            if(find(node.s)!=find(node.d)){
                union(node.s, node.d);
                res += node.c;
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        vertex_cost = new int[N+1];
        parent = new int[N+1];
        list = new ArrayList<>();
        int city = N;
        int edge = P;
        int minimum_start = Integer.MAX_VALUE;
        for(int i=0; i<N+1; i++)parent[i] = i;
        int z =1;
        while(city-->0){
            st = new StringTokenizer(br.readLine());
            vertex_cost[z] = Integer.parseInt(st.nextToken());
            minimum_start = Math.min(vertex_cost[z],minimum_start);
            z++;
        }
        while(edge-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Node(s,d, (2*c) + (vertex_cost[s] + vertex_cost[d])));
        }
        Collections.sort(list);
        kruskal();
        System.out.print(res + minimum_start);
    }
}
