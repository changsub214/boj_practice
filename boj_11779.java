import java.util.*;
import java.io.*;

public class boj_11779 {
    static class Bus implements Comparable<Bus>{
        int vertex;
        int cost;
        public Bus(int vertex, int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }
    static int C,B;
    static int[] dis;
    static int[] path;
    static ArrayList<Bus>[] list;
    public static void dijkstra(int v){
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(v, 0)); //start
        dis[v] = 0;
        path[v] = 0;
        while(!pq.isEmpty()){
            Bus node = pq.poll();
            if(dis[node.vertex] < node.cost)continue;
            int len = list[node.vertex].size();
            for(int i=0; i<len; i++){
                Bus nx = list[node.vertex].get(i);
                if(dis[nx.vertex] > node.cost + nx.cost){
                    dis[nx.vertex] = node.cost + nx.cost;
                    path[nx.vertex] = node.vertex;
                    pq.add(new Bus(nx.vertex, dis[nx.vertex]));
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        list = new ArrayList[C+1];
        path = new int[C+1];
        dis = new int[C+1];
        for(int i=0; i<C+1; i++){
            list[i] = new ArrayList<>();
        }
        Arrays.fill(dis, Integer.MAX_VALUE);
        while(B-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Bus(d,c));
        }
        st = new StringTokenizer(br.readLine());
        int Start = Integer.parseInt(st.nextToken());
        int Destination = Integer.parseInt(st.nextToken());

        dijkstra(Start);
        sb.append(dis[Destination]).append("\n");

        Stack<Integer> stack = new Stack<>();
        int cnt =0;
        while(Destination != 0){
            cnt++;
            stack.add(Destination);
            Destination = path[Destination];
        }
        sb.append(cnt).append("\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }
        sb.append("\n");
        System.out.print(sb);
        br.close();
    }
}
