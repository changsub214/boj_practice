import java.util.*;
import java.io.*;

public class boj_1504 {
    static class Node implements Comparable<Node>{
        int v;
        int cost;
        public Node(int v, int cost){
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static int V, E;
    static final int INF=Integer.MAX_VALUE;
    static int[] dis;
    //static boolean[] visit;
    static ArrayList<Node>[] list;
    public static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dis = new int[V+1];
        Arrays.fill(dis,INF);
        //Arrays.fill(visit,false);
        dis[start] = 0;
        pq.add(new Node(start,0));//start
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(dis[node.v] < node.cost)continue;
            //if(visit[node.v])continue;
            //visit[node.v]= true;
            if(node.v == end)break;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
               // if(visit[nx.v])continue;
                if(dis[nx.v] > node.cost + nx.cost){
                    dis[nx.v] = node.cost +nx.cost;
                    pq.add(new Node(nx.v,dis[nx.v]));
                }
            }
        }
        return dis[end];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        //visit = new boolean[V+1];
        list = new ArrayList[V+1];
        for(int i=0; i<V+1; i++){
            list[i] = new ArrayList<>();
        }
        while(E-- >0){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[S].add(new Node(D,C));
            list[D].add(new Node(S,C)); //two-way
        }
        st = new StringTokenizer(br.readLine());
        int v_1 = Integer.parseInt(st.nextToken());
        int v_2 = Integer.parseInt(st.nextToken());
        long sum = 0;
        sum += dijkstra(1,v_1);
        sum += dijkstra(v_1,v_2);
        sum += dijkstra(v_2,V);
        long sum2 = 0;
        sum2 += dijkstra(1,v_2);
        sum2 += dijkstra(v_2,v_1);
        sum2 += dijkstra(v_1,V);
        /*for(int i=1; i<dis.length; i++){
            System.out.print(dis[i] + " ");
        }*/
        if(sum>=INF && sum2>=INF){
            System.out.print(-1);
        }else{
            long res = Math.min(sum,sum2);
            System.out.print(res);
        }
    }
}
