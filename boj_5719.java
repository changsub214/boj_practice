import java.util.*;
import java.io.*;

public class boj_5719 {
    static class Road implements Comparable<Road>{
        int d;
        int c;
        public Road(int d, int c){
            this.d = d;
            this.c = c;
        }
        @Override
        public int compareTo(Road o) {
            return this.c - o.c;
        }
    }
    static int N, M;
    static int start, end;
    static boolean[][] visit;
    static int[] dis;
    static ArrayList<Road>[] road_list;
    static ArrayList<Integer>[] trace_road;
    public static void dijkstra(){

        PriorityQueue<Road> pq = new PriorityQueue<>();
        dis = new int[N];
        pq.add(new Road(start, 0)); //begin
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        while(!pq.isEmpty()){
            Road node = pq.poll();
            if(dis[node.d] < node.c)continue;
            int len = road_list[node.d].size();
            for(int i=0; i<len; i++){
                Road nx = road_list[node.d].get(i);
                int cost = node.c + nx.c;
                if(!visit[node.d][nx.d]){
                    if(dis[nx.d] == dis[node.d] + nx.c){
                        trace_road[nx.d].add(node.d);
                    }
                    if(dis[nx.d] > cost){
                        dis[nx.d] = cost;
                        trace_road[nx.d].clear(); //Because the shortest path renew
                        trace_road[nx.d].add(node.d);
                        pq.add(new Road(nx.d, dis[nx.d]));
                    }
                }
            }
        }
    }
    public static void cut_route(int start, int n){

        if(n == start)return;
        int len = trace_road[n].size();
        for(int i=0; i<len; i++){
            int v = trace_road[n].get(i);
            if(!visit[v][n]){
                visit[v][n] = true;
                cut_route(start, v);
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N==0 && M==0)break;
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            visit = new boolean[N][N];
            road_list = new ArrayList[N];
            trace_road = new ArrayList[N];
            for(int i=0; i<N; i++){
                road_list[i] = new ArrayList<>();
                trace_road[i] = new ArrayList<>();
            }
            while(M-->0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                road_list[s].add(new Road(d, c));
            }

            dijkstra();
            cut_route(start, end);
            dijkstra();

            if(dis[end] == Integer.MAX_VALUE){
                sb.append(-1).append("\n");
            }else{
                sb.append(dis[end]).append("\n");
            }
        }
        System.out.print(sb);
    }
}
