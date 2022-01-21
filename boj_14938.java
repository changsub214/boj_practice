import java.util.*;
import java.io.*;

public class boj_14938 {
    static class Location implements Comparable<Location>{
        int v;
        int len;
        public Location(int v, int len){
            this.v = v;
            this.len = len;
        }
        @Override
        public int compareTo(Location o) {
            return this.len - o.len;
        }
    }
    static int V, RANGE, E, sum =0 ;
    static int[] item_n;
    static int[] dis;
    static ArrayList<Location>[] list;
    public static void dijkstra(int s){
        int temp = 0; // To count the number of items
        PriorityQueue<Location> pq = new PriorityQueue<>();
        dis = new int[V+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        pq.add(new Location(s, 0)); //start-point
        dis[s] = 0;
        while(!pq.isEmpty()){
            Location node = pq.poll();
            if(dis[node.v] < node.len)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Location nx = list[node.v].get(i);
                if(dis[nx.v] > node.len + nx.len){
                    dis[nx.v] = node.len + nx.len;
                    pq.add(new Location(nx.v, dis[nx.v]));
                }
            }
        }
        for(int i=1; i<V+1; i++){
            if(RANGE >= dis[i])temp+=item_n[i];
        }
      /*  for(int i=1; i<V+1; i++){
            System.out.print(dis[i] + " ");
        }
        System.out.println("");*/
        sum = Math.max(sum, temp);
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        RANGE = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        item_n = new int[V+1];
        list = new ArrayList[V+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<V+1; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=1; i<V+1; i++){
            item_n[i] = Integer.parseInt(st.nextToken());
        }
        while(E-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Location(d,c));
            list[d].add(new Location(s,c)); //two-way
        }
        for(int i=1; i<V+1; i++){
            dijkstra(i);
        }
        System.out.print(sum);
    }
}
