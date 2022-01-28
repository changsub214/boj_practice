import java.util.*;
import java.io.*;

public class boj_1647 {
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
            return this.c-o.c;
        }
    }
    static int N, M, res =0;
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
            if(x<y)parent[y] = x;
            else parent[x] = y;
        }
    }
    public static void kruskal(){
        int edge = 0;
        int len = list.size();
        for(int i=0; i<len; i++){
            Node node = list.get(i);
            if(find(node.s) != find(node.d)){
                union(node.s,node.d);
                res += node.c;
                if(++edge == N-2)return; //각 마을이 최소 연결이라면 마을 내 집수 -1인데 두 마을 연결은
                //안되어도 되므로 연결되는게 없다는 것을 고려
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        list = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            parent[i] = i;
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Node(s, d, c));
        }
        Collections.sort(list);
        kruskal();
        System.out.print(res);
    }
}
