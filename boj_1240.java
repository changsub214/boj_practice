import java.util.*;
import java.io.*;

public class boj_1240 {
    static class Node implements Comparable<Node>{
        int d;
        int c;
        public Node(int d, int c){
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
    static int N, M;
    static int[] dis;
    static boolean[] visit;
    static ArrayList<Node>[] list;
    public static int dijkstra(int s, int d){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dis,Integer.MAX_VALUE);
        Arrays.fill(visit,false);
        pq.add(new Node(s,0));
        dis[s] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(visit[node.d])continue;
            visit[node.d] = true;
            int len = list[node.d].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.d].get(i);
                if(dis[nx.d] > node.c + nx.c){
                    dis[nx.d] = node.c+nx.c;
                    pq.add(new Node(nx.d,dis[nx.d]));
                }
            }
        }
        return dis[d];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        dis = new int[N+1];
        visit = new boolean[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        while(N-- >1){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d,c));
            list[d].add(new Node(s,c));
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            sb.append(dijkstra(s, d)).append("\n");
        }
        System.out.print(sb);
    }
}
