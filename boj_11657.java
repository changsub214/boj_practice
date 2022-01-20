import java.util.*;
import java.io.*;

public class boj_11657 {
    static class Bus implements Comparable<Bus>{
        int v;
        int w;
        public Bus(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Bus o) {
            return this.w - o.w;
        }
    }
    static int V, E;
    static long[] dis;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Bus>[] list;
    public static boolean BellmanFord(){
        dis[1] = 0;
        boolean upt = false;

        for(int i=1; i<V; i++){
            upt = false;
            for(int j=1; j<V+1; j++){
                int len = list[j].size();
                for(int k=0; k<len; k++){
                    Bus nx = list[j].get(k);
                    if(dis[j] == INF)break;
                    if(dis[nx.v] > dis[j] + nx.w){
                        dis[nx.v] = dis[j] + nx.w;
                        upt = true;
                    }
                }
            }
            if(!upt)break;
        }

        if(upt){
            for(int i=1; i<V+1; i++){
                int len = list[i].size();
                for(int j=0; j<len; j++){
                    Bus nx = list[i].get(j);
                    if(dis[i]==INF)break;
                    if(dis[nx.v] > dis[i] + nx.w){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new ArrayList[V+1];
        dis = new long[V+1];
        for(int i=0; i<V+1; i++){
            list[i] = new ArrayList<>();
        }
        Arrays.fill(dis,INF);
        while(E-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Bus(d,c));
        }
        if(BellmanFord()){
            sb.append(-1).append("\n");
        }else{
            for(int i=2; i<dis.length; i++){
                if(dis[i] == INF){
                    sb.append(-1).append("\n");
                    continue;
                }
                sb.append(dis[i]).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
}
