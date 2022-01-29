import java.util.*;
import java.io.*;

public class boj_13905 {
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
            return o.c - this.c; //descending
        }
    }
    static int N, M, start, end, res =0;
    static int[] parent;
    static ArrayList<Node> list;
    static ArrayList<Node> list2;
    public static int find(int x){
        if(parent[x] == x){
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
        for(int i=0; i<list.size(); i++){
            Node node = list.get(i);
            if(find(node.s)!=find(node.d)){
                union(node.s, node.d);
                if(find(start) == find(end)){
                    res = node.c;
                    break;
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=0; i<N+1; i++)parent[i] = i;
        list = new ArrayList<>();
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Node(s,d,c));
        }
        Collections.sort(list);
        kruskal();
        System.out.print(res);
    }
}
