import java.util.*;
import java.io.*;

public class boj_12834 {
    static class Node implements Comparable<Node>{
        int v;
        int c;
        public Node(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
    static int N, V, R;
    static int[] human;
    static int[] dis;
    static ArrayList<Node>[] list;
    public static int dijkstra(int s, int d){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0)); //begin
        dis = new int[V+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(node.v == d)return dis[d];
            if(dis[node.v] < node.c) continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(dis[nx.v] > node.c + nx.c){
                    dis[nx.v] = node.c + nx.c;
                    pq.add(new Node(nx.v, dis[nx.v]));
                }
            }
        }
        return -1;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        human = new int[N];
        list = new ArrayList[V+1];
        for(int i=0; i<V+1; i++){
            list[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        int kist_coordinate = Integer.parseInt(st.nextToken());
        int soul_coordinate = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            human[i] = Integer.parseInt(st.nextToken());
        }
        while(R-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d,c));
            list[d].add(new Node(s,c));
        }
        int res = 0;
        for(int i=0; i< human.length; i++){
            res += dijkstra(human[i],kist_coordinate);
            res += dijkstra(human[i],soul_coordinate);
        }
        System.out.print(res);
    }
}
