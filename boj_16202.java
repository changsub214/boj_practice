import java.util.*;
import java.io.*;

public class boj_16202 {
    static class Node implements Comparable<Node>{
        int s;
        int d;
        int c;
        public Node(int s, int d, int c){
            this.s = s;
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
    static int N, M, K;
    static int[] parent;
    static ArrayList<Node> list;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y)parent[y] =x;
            else parent[x] =y;
        }
    }
    public static boolean chekcs(){
        for(int i=1; i<N; i++){
            if(find(i) != find(i+1)){
                return false;
            }
        }
        return true;
    }
    public static int kruskal(){
        int res = 0;
        int len = list.size();
        for(int i=0; i<len; i++){
            Node node = list.get(i);
            if(find(node.s)!=find(node.d)){
                union(node.s,node.d);
                res += node.c;
            }
        }
        if(chekcs()){
            return res;
        }else{
            return 0;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for(int i=1; i<M+1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Node(s, d, i));
        }
        while(K-- >0){
            parent = new int[N+1];
            for(int i=0; i<N+1; i++){
                parent[i] = i;
            }
            Collections.sort(list);
            sb.append(kruskal() + " ");
            list.remove(0);
        }
        System.out.print(sb);
    }
}
