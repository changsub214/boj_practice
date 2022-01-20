import java.util.*;
import java.io.*;

public class boj_9370 {
    //I did manipulate weight
    //When I must go designated road, I add odd-weight
    //and the others was added even-weight
    //The result of dis[] give information
    //the information give dis's index is target-vertex and if you went designated road
    //dis's value is odd but If you wasn't, that is just even..!
    static class Road implements Comparable<Road>{
        int v;
        int c;
        public Road(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Road o) {
            return this.c - o.c;
        }
    }
    static int T, I, R, Candidate, S, G, H;
    static final int INF = 1000000000;
    static int[] dis;
    static ArrayList<Road>[] list;
    public static void dijkstra(int s){
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(s, 0)); //start
        dis[s] = 0;
        while(!pq.isEmpty()){
            Road node = pq.poll();
            if(dis[node.v] < node.c)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Road nx = list[node.v].get(i);
                if(dis[nx.v] > node.c + nx.c){
                    dis[nx.v] = node.c + nx.c;
                    pq.add(new Road(nx.v,dis[nx.v]));
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-- >0){
            ArrayList<Integer> save = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            Candidate = Integer.parseInt(st.nextToken());
            list = new ArrayList[I+1];
            dis = new int[I+1];
            Arrays.fill(dis,INF);
            for(int i=0; i<I+1; i++){
                list[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken()); //start-point
            G = Integer.parseInt(st.nextToken()); //must intersect
            H = Integer.parseInt(st.nextToken()); //must intersect
            while(R-- >0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if((s==G&&d==H) || (s==H&&d==G)){
                    c = 2*c -1;
                }else{
                    c = 2*c;
                }
                list[s].add(new Road(d, c));
                list[d].add(new Road(s, c));
            }
            dijkstra(S);
            while(Candidate-- >0){
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                if(dis[n]<INF && dis[n] %2 !=0){
                    save.add(n);
                }
            }
            Collections.sort(save);
            for(int i=0; i<save.size(); i++){
                sb.append(save.get(i) + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
