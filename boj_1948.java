import java.util.*;
import java.io.*;

public class boj_1948 {
    static class Node implements Comparable<Node>{
        int s;
        int c;
        public Node(int s, int c){
            this.s = s;
            this.c = c;
        }
        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
    static int N, M, start , end, cnt = 0;
    static int[] edge_num;
    static int[] dis;
    static boolean[] visit;
    static ArrayList<Node>[] list;
    static ArrayList<Node>[] trace;
    static ArrayList<Integer> result;
    public static int topological_sort(){
        Queue<Integer> q = new LinkedList<>();
        dis = new int[N+1];
        for(int i=1; i<N+1; i++){
            if(edge_num[i]==0)q.add(i);
        }
        while(!q.isEmpty()){
            int v = q.poll();
            int len = list[v].size();
            for(int i=0; i<len; i++){
                int vv = list[v].get(i).s;
                edge_num[vv]--;
                if(edge_num[vv]==0)q.add(vv);

                if(dis[vv] < dis[v] + list[v].get(i).c){
                    dis[vv] = dis[v] + list[v].get(i).c;
                }
            }
        }
        dfs(start);
        return dis[start];
    }
    public static void dfs(int start_){
        if(visit[start_])return;
        visit[start_] = true;
        int len = trace[start_].size();
        for(int i=0; i<len; i++){
            int s = trace[start_].get(i).s;
            int c = trace[start_].get(i).c;
            if(dis[start_] == dis[s] + c){
                cnt++;
                dfs(s);
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        trace = new ArrayList[N+1];
        visit = new boolean[N+1];
        edge_num = new int[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
            trace[i] = new ArrayList<>();
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[d].add(new Node(s, c));
            trace[s].add(new Node(d, c));
            edge_num[s]++;
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        int r = topological_sort();
        System.out.println(r);
        System.out.println(cnt);

    }
}
