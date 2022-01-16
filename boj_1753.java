import java.util.*;
import java.io.*;

public class boj_1753 {
    static class Node implements Comparable<Node> {
        int vertex;
        int cost;
        Node(int vertex, int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }
    static int V,E,S;
    static ArrayList<Node>[] list;
    static int[] dis;
    public static void dijkstra(int S){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dis[S] = 0;
        pq.add(new Node(S,0));
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(node.cost > dis[node.vertex]) continue;
            int len = list[node.vertex].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.vertex].get(i);
                if(dis[nx.vertex] > node.cost + nx.cost){
                    dis[nx.vertex] = node.cost + nx.cost;
                    pq.add(new Node(nx.vertex,dis[nx.vertex]));
                }
            }
        }
    }
    public static void main(String[] args)throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] temp = br.readLine().split(" ");
        V = Integer.parseInt(temp[0]);
        E = Integer.parseInt(temp[1]);
        S = Integer.parseInt(br.readLine());
        list = new ArrayList[V+1];
        dis = new int[V+1];
        for(int i=0; i<dis.length; i++){
            dis[i] = Integer.MAX_VALUE;
        }
        for(int i=1; i<V+1; i++){
            list[i] = new ArrayList<Node>();
        }
        while(E-- >0){
            int s, d, c;
            String[] temp_ = br.readLine().split(" ");
            s = Integer.parseInt(temp_[0]);
            d = Integer.parseInt(temp_[1]);
            c = Integer.parseInt(temp_[2]);
            list[s].add(new Node(d,c));
        }
        dijkstra(S);

        for(int i=1; i<V+1; i++){
            if(dis[i] == Integer.MAX_VALUE){
                sb.append("INF").append('\n');
            }else{
                sb.append(dis[i]).append('\n');
            }
        }
        System.out.print(sb);
        br.close();
    }
}
