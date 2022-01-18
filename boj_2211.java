import java.util.*;
import java.io.*;

public class boj_2211 {
    static class Network implements Comparable<Network>{
        int v;
        int cost;
        public Network(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Network o) {
            return this.cost - o.cost;
        }
    }
    static int V, E;
    static ArrayList<Network>[] list;
    static int[] des;
    static int[] path;
    public static void dijkstra(){
        PriorityQueue<Network> pq = new PriorityQueue<>();
        pq.add(new Network(1,0)); // computer1 is super-computer
        des[1] = 0;
        while(!pq.isEmpty()){
            Network node = pq.poll();
            if(des[node.v] < node.cost)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Network nx = list[node.v].get(i);
                if(des[nx.v] > node.cost + nx.cost){
                    path[nx.v] = node.v; //for the backtracking
                    des[nx.v] = node.cost+nx.cost;
                    pq.add(new Network(nx.v,des[nx.v]));
                }
            }
        }
      /*  for(int i=1; i<des.length; i++){
            System.out.print(des[i] + " ");
        }*/
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new ArrayList[V+1];
        des = new int[V+1];
        path = new int[V+1];
        for(int i=0; i<V+1; i++){
            list[i] = new ArrayList<>();
        }
        Arrays.fill(des,Integer.MAX_VALUE);
        Arrays.fill(path,Integer.MAX_VALUE);
        while(E-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Network(d,c));
            list[d].add(new Network(s,c));
        }
        dijkstra();
        int connected = 0;
        for(int i=1; i<path.length; i++){
            if(path[i] == Integer.MAX_VALUE){
                //can't reach the site..!
                continue;
            }
            connected++;
            sb.append(i + " " + path[i]).append('\n');
        }
        System.out.println(connected);
        System.out.print(sb);
    }
}
