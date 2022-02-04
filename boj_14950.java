import java.util.*;
import java.io.*;

public class boj_14950 {
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
    static int N, M, t, res = 0;
    static int[] parent;
    static Node[] list;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y) parent[y] = x;
            else parent[x] = y;
        }
    }
    public static void kruskal(){
        int cnt = 0 ;
        for(Node node : list){
           // Node node = list.get(i);
            if(find(node.s) != find(node.d)){
                union(node.s, node.d);
                res += node.c + (cnt*t);
                cnt++;
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        list = new Node[M];
        parent = new int[N+1];
        for(int i=0; i<N+1; i++){
            parent[i] = i;
        }
        int i = 0;
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[i] = new Node(s, d, c);
            i++;
        }
        Arrays.sort(list);
        kruskal();
        System.out.print(res);
    }
}
