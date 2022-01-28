import java.util.*;
import java.io.*;

public class boj_1922 {
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
    static int N,M, res = 0;
    static int[] parent;
    static ArrayList<Node> connect;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y)parent[y]=x;
            else parent[x]=y;
        }
    }
    public static void kruskal(){
        int len = connect.size();
        for(int i=0; i<len; i++){
            Node node = connect.get(i);
            if(find(node.s)!=find(node.d)){
                union(node.s,node.d);
                res += node.c;
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
        connect = new ArrayList<>();
        parent = new int[N];
        for(int i=0; i<N; i++){
            parent[i] = i;
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            connect.add(new Node(s-1, d-1, c));
        }
        Collections.sort(connect);
        kruskal();
        System.out.print(res);
    }
}
