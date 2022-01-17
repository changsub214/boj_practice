import java.util.*;
import java.io.*;

public class boj_1238 {
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
    static int N, M, X, result = Integer.MIN_VALUE;
    static int[] res;
    static int[] des_x_to_home;
    static ArrayList<Node>[] list;
    public static void dijkstra(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0)); //start
        des_x_to_home[s] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(des_x_to_home[node.v] < node.cost) continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(des_x_to_home[nx.v] > node.cost + nx.cost){
                    des_x_to_home[nx.v] = node.cost + nx.cost;
                    pq.add(new Node(nx.v, des_x_to_home[nx.v]));
                }
            }
        }
    }
    public static int dijkstra2(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0)); //start
        int[] des_each_to_x = new int[N+1];
        Arrays.fill(des_each_to_x,Integer.MAX_VALUE);
        des_each_to_x[s] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(des_each_to_x[node.v] < node.cost)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(des_each_to_x[nx.v] > node.cost + nx.cost){
                    des_each_to_x[nx.v] = node.cost + nx.cost;
                    pq.add(new Node(nx.v, des_each_to_x[nx.v]));
                }
            }
        }
        return des_each_to_x[X];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        des_x_to_home = new int[N+1];
        res = new int[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        Arrays.fill(des_x_to_home, Integer.MAX_VALUE);
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d,c));
        }
        dijkstra(X);
        for(int i=1; i<N+1; i++){
            if(i==X){
                res[i] = 0;
                continue;
            }
            res[i] = dijkstra2(i);
        }
        for(int i=1; i< des_x_to_home.length; i++){
            int temp = des_x_to_home[i] + res[i];
            result = Math.max(result,temp);
        }
        System.out.print(result);
    }
}
