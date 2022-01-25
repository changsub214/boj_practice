import java.util.*;
import java.io.*;

public class boj_10423 {
    static class Node implements Comparable<Node>{
        int v;
        int c;
        public Node(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c-o.c;
        }
    }
    static int N, M, K;
    static int[] power_plant;
    static boolean[] visit;
    static ArrayList<Node>[] list;
    public static int prim(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i< power_plant.length; i++){
            pq.add(new Node(power_plant[i], 0));//begin
        }
        int res = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(visit[node.v])continue;
            res += node.c;
            visit[node.v] = true;
            if(++cnt == N)return res;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.v].get(i);
                if(visit[nx.v])continue;
                pq.add(nx);
            }
        }
        return -1;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        visit = new boolean[N+1];
        power_plant = new int[K];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){ //get power_plant number
            power_plant[i] = Integer.parseInt(st.nextToken());
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(d, c));
            list[d].add(new Node(s, c));
        }
        System.out.print(prim());
    }
}
