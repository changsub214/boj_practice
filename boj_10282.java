import java.util.*;
import java.io.*;

public class boj_10282 {
    static class Computer implements Comparable<Computer>{
        int v;
        int second;
        public Computer(int v, int second){
            this.v = v;
            this.second = second;
        }

        @Override
        public int compareTo(Computer o) {
            return this.second - o.second;
        }
    }
    static int T, A, B, first_infection_com;
    static int[] des;
    static int[] path;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Computer>[] list;
    public static void dijkstra(int s){
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.add(new Computer(s, 0)); //start
        des = new int[A+1];
        path = new int[A+1];
        Arrays.fill(des, Integer.MAX_VALUE);
        Arrays.fill(path, Integer.MAX_VALUE);
        des[s] = 0;
        path[s] = 0;
        while(!pq.isEmpty()){
            Computer node = pq.poll();
            if(des[node.v] < node.second)continue;
            int len = list[node.v].size();
            for(int i=0; i<len; i++){
                Computer nx = list[node.v].get(i);
                if(des[nx.v] > node.second + nx.second){
                    des[nx.v] = node.second + nx.second;
                   // path[nx.v] = node.v; //next-infection-route
                    pq.add(new Computer(nx.v, des[nx.v]));
                }
            }
        }
        cnt(des);
    }
    public static void cnt(int[] des){
        int cnt =0;
        int sec =-1;
        for(int i=1; i<des.length; i++){
            if(des[i]!=Integer.MAX_VALUE){
                cnt++;
                sec = Math.max(sec,des[i]);
            }
        }
        sb.append(cnt + " " + sec).append("\n");
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-- >0){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            first_infection_com = Integer.parseInt(st.nextToken());
            list = new ArrayList[A+1];
            for(int i=0; i<A+1; i++){
                list[i] = new ArrayList<>();
            }
            while(B-- >0){
                st = new StringTokenizer(br.readLine());
                int result = Integer.parseInt(st.nextToken());
                int cause = Integer.parseInt(st.nextToken());
                int sec = Integer.parseInt(st.nextToken());
                list[cause].add(new Computer(result, sec)); //one-way
            }
            dijkstra(first_infection_com);
        }
        System.out.print(sb);
    }
}
