import java.util.*;
import java.io.*;

public class boj_14621 {
    static class Node implements Comparable<Node>{
        int d;
        int c;
        public Node(int d, int c){
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
    static int N, M;
    static char[] vertex;
    static boolean[] visit;
    static ArrayList<Node>[] list;
    public static int prim(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0)); //begin
        int res = 0; //result
        int cnt = 0; //calculate vertex
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(visit[node.d])continue;
            res += node.c;
            visit[node.d] = true;
            if(++cnt == N)return res;
            int len = list[node.d].size();
            for(int i=0; i<len; i++){
                Node nx = list[node.d].get(i);
                if(visit[nx.d])continue;
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
        vertex = new char[N+1];
        visit = new boolean[N+1];
        list = new ArrayList[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            vertex[i] = st.nextToken().charAt(0);
        }
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(vertex[s] != vertex[d]){
                list[s].add(new Node(d,c));
                list[d].add(new Node(s,c));
            }
        }
        System.out.print(prim());
    }
}
