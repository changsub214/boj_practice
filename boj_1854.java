import java.util.*;
import java.io.*;

public class boj_1854 {
    //We shoud find the K-th short path
    static class Node implements Comparable<Node>{
        int v;
        int c;
        public Node(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c-o.c;
        }
    }
    static final int INF = 1000000000;
    static int V, E, K;
    static PriorityQueue<Integer>[] dis;
    static ArrayList<Node>[] list;
    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0)); // start-point
        dis[1].add(0);
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(dis[nx.v].size() < K){
                    dis[nx.v].add(node.c+nx.c);
                    pq.add(new Node(nx.v, node.c+nx.c));
                }else if(dis[nx.v].peek() > node.c+nx.c){
                    dis[nx.v].poll();
                    dis[nx.v].add(node.c+nx.c);
                    pq.add(new Node(nx.v,node.c+nx.c));
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[V+1];
        dis = new PriorityQueue[V+1];
        for(int i =0; i<V+1; i++){
            list[i] = new ArrayList<>();
        }
        for(int i =1; i<V+1; i++){
            dis[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        //Arrays.fill(dis, INF);
        while(E-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d, c));
        }
        dijkstra();

        for(int i=1; i<V+1; i++){
            if(dis[i].size() == K){
                sb.append(dis[i].peek()).append("\n");
            }else{
                sb.append(-1).append('\n');
            }
        }
        System.out.print(sb);
    }
}
