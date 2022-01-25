import java.util.*;
import java.io.*;

public class boj_10217 {
    static class Node implements Comparable<Node>{
        int v;
        int c;
        int s;
        public Node(int v, int c, int s){
            this.v = v;
            this.c = c;
            this.s = s;
        }
        @Override
        public int compareTo(Node o) {
            if(this.s == o.s){
                return this.c - o.c;
            }
            return this.s - o.s;
        }
    }
    static int T, N, M, K;
    static int[][] time;
    static ArrayList<Node>[] list;
    public static long dijkstra(int start, int destination){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0,0));
        time = new int[N+1][M+1];
        for(int i=1; i<N+1; i++){
            Arrays.fill(time[i],Integer.MAX_VALUE);
        }
        time[start][0] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(node.v == destination){
                return node.s;
            }
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                int sum_money = node.c + nx.c;
                if(sum_money>M)continue;
                if(time[nx.v][sum_money] > node.s + nx.s){
                    time[nx.v][sum_money] = node.s + nx.s;
                    pq.add(new Node(nx.v, sum_money, time[nx.v][sum_money]));
                }
            }
        }
        return -1;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-- >0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            list = new ArrayList[N+1];
            for(int i=0; i<N+1; i++){
                list[i] = new ArrayList<>();
            }
            while(K-- >0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int sec = Integer.parseInt(st.nextToken());
                list[s].add(new Node(d,c,sec));
            }
            long res = dijkstra(1,N);
            if(res == -1){
                sb.append("Poor KCM").append('\n');
            }else{
                sb.append(res).append('\n');
            }
        }
        System.out.print(sb);
    }
}
