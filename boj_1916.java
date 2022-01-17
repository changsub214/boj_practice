import java.util.*;
import java.io.*;

public class boj_1916 {
    static class Bus implements Comparable<Bus>{
        int v;
        int cost;
        public Bus(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }
    static int N, M;
    static int[] dis;
    static boolean[] visit;
    static ArrayList<Bus>[] list;
    public static int dijkstra(int s, int d){
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(s,0)); //start point
        dis[s] = 0;
        while(!pq.isEmpty()){
            Bus node = pq.poll();
            if(node.cost > dis[node.v])continue; //not minimum We need minimum path for the cost!
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Bus nx = list[node.v].get(i);
                if(dis[nx.v] > node.cost + nx.cost){
                    dis[nx.v] = node.cost + nx.cost;
                    pq.add(new Bus(nx.v,dis[nx.v]));
                }
            }
        }
        return dis[d];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //city like vertex
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //bus like edge
        list = new ArrayList[N+1];
        dis = new int[N+1];
        for(int i=1; i<N+1; i++){
            list[i] = new ArrayList<Bus>();
        }
        Arrays.fill(dis,Integer.MAX_VALUE);
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Bus(d,c));
        }
        st = new StringTokenizer(br.readLine());
        int Start = Integer.parseInt(st.nextToken());
        int Destination = Integer.parseInt(st.nextToken());
        int res = dijkstra(Start, Destination);
        System.out.print(res);
    }
}
