import java.util.*;
import java.io.*;

public class boj_1197 {
    //Kruskal Algorithm
    static class Node implements Comparable<Node>{
        int s;
        int d;
        int w;
        public Node(int s, int d, int w){
            this.s = s;
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
    static int V, E;
    static int[] parents;
    static Node[] e_list;
    public static int kruskal(){
        int res = 0;
        int cnt = 0;
        //need to srot array ascending
        Arrays.sort(e_list);
        for(Node node : e_list){
            if(union(node.s,node.d)){
                res += node.w;
                if(++cnt == V-1)return res;
            }
        }
        return res;
    }
    public static boolean union(int s, int d){
        int f_root = find(s);
        int s_root = find(d);
        if(f_root == s_root)return false;
        parents[f_root] = s_root;
        return true;
    }
    public static int find(int e){
        if(e == parents[e])return e;
        return parents[e] = find(parents[e]);
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V+1];
        e_list = new Node[E];
        for(int i=1; i<V+1; i++){
            parents[i] = i;
        }
        int i=0;
        while(E-- >0){
            st = new StringTokenizer(br.readLine());;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            e_list[i] = new Node(s,d,c);
            i++;
        }
        System.out.print(kruskal());
    }
    /*
    //Prim algorithm
    static class Node implements Comparable<Node>{
        int v;
        int w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
    static int V, E;
    static boolean[] visit;
    static ArrayList<Node>[] list;
    public static long prim(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0)); //begin
        long res = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(visit[node.v])continue;
            res += node.w; //sum weight
            visit[node.v] = true;
            if(++cnt == V) return res;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(visit[nx.v])continue;
                pq.add(nx);
            }
        }
        return res;
    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new ArrayList[V+1];
        visit = new boolean[V+1];
        for(int i=0; i<V+1; i++){
            list[i] = new ArrayList<>();
        }
        while(E-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d,c));
            list[d].add(new Node(s,c));
        }
        System.out.print(prim());
    }*/
}
