import java.util.*;
import java.io.*;

public class boj_2307 {
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
    static int V, E, INF=1000000000;
    static int[] dis;
    static int[] dis2;
    static int[] path;
    static ArrayList<Node>[] list;
    public static void dijkstra(int s, int d){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));//start
        Arrays.fill(dis,INF);
        dis[s] = 0;
        path[s] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(dis[node.v] < node.c)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(dis[nx.v] > node.c + nx.c){
                    dis[nx.v] = node.c +nx.c;
                    path[nx.v] = node.v;
                    pq.add(new Node(nx.v,dis[nx.v]));
                }
            }

        }
    }
    public static void dijkstra2(int s, int d){ //It's a function to cut each of them off
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));//start
        Arrays.fill(dis2,INF);
        dis2[1] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(dis2[node.v] < node.c)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(node.v == s && nx.v == d)continue; //can't go
                if(node.v == d && nx.v == s)continue; //can't go
                if(dis2[nx.v] > node.c + nx.c){
                    dis2[nx.v] = node.c +nx.c;
                    pq.add(new Node(nx.v,dis2[nx.v]));
                }
            }

        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new ArrayList[V+1];
        dis = new int[V+1];
        dis2 = new int[V+1];
        path = new int[V+1];
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
        //first step. calculate the shortest path
        dijkstra(1,V);
        int f_result = dis[V];
        //second step. calculate the cut shortest path
        int r_result2 = Integer.MIN_VALUE;
        for(int i=2; i<V+1; i++){
            dijkstra2(i,path[i]);
            r_result2 = Math.max(r_result2,dis2[V]);
        }
        if(r_result2>=INF){
            System.out.print(-1);
        }else{
            System.out.print(r_result2-f_result);
        }
    }
}