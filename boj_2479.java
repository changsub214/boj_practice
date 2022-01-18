import java.util.*;
import java.io.*;

public class boj_2479 {
    static class Node implements Comparable<Node>{
        int v;
        int difference;
        public Node(int v, int difference){
            this.v = v;
            this.difference = difference;
        }
        @Override
        public int compareTo(Node o) {
            return this.difference - o.difference;
        }
    }
    static int N,K;
    static String[] value;
    static int[] des;
    static int[] path;
    static ArrayList<Node>[] list;
    public static void dijkstra(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        des[s] = 0;
        path[s] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(des[node.v] < node.difference)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(des[nx.v] > node.difference + nx.difference){
                    des[nx.v] = node.difference + nx.difference;
                    path[nx.v] = node.v; //for the backtracking
                    pq.add(new Node(nx.v,des[nx.v]));
                }
            }
        }
    }
    public static void backtracking(int v){
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while(v != 0){
            stack.add(v);
            v = path[v];
            if(v == Integer.MAX_VALUE){ //can't reach to the destination point
                stack.clear();
                System.out.println("-1");
                return;
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        value = new String[N+1];
        path = new int[N+1];
        des = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            value[i] = br.readLine();
        }
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList();
        }
        Arrays.fill(des,Integer.MAX_VALUE);
        Arrays.fill(path,Integer.MAX_VALUE);
        for(int i=1; i<N; i++){
            for(int j=i+1; j<N+1; j++){
                int difference = 0;
                int cnt =0;
                while(cnt < K){
                    if(value[i].charAt(cnt)!=value[j].charAt(cnt))difference++;
                    cnt++;
                }
                if(difference==1){
                    list[i].add(new Node(j,difference)); //two-way
                    list[j].add(new Node(i,difference));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        dijkstra(s);
        backtracking(d);
    }
}
