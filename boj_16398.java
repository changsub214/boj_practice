import java.util.*;
import java.io.*;

public class boj_16398 {
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
    static int N;
    static long res = 0;
    static int[] parent;
    static ArrayList<Node> list;
    public static int find(int x){
        if(parent[x] ==x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y)parent[y] = x;
            else parent[x] = y;
        }
    }
    public static void kruskal(){
        int len = list.size();
        for(int i=0; i<len; i++){
            Node node = list.get(i);
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
        list = new ArrayList<>();
        parent = new int[N];
        for(int i=0; i<N; i++)parent[i] = i;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(i<j){
                    list.add(new Node(i, j, temp));
                }
            }
        }
        Collections.sort(list);
        kruskal();
        System.out.print(res);

    }
}
