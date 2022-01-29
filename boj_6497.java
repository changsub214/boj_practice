import java.util.*;
import java.io.*;

public class boj_6497 {
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
            return this.c- o.c;
        }
    }
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
            else parent[x] = y;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N==0 && M==0)break;
            parent = new int[N];
            list = new ArrayList<>();
            for(int i=0; i<N; i++)parent[i] = i;
            int all_cost = 0;
            int res = 0;

            while(M-- >0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list.add(new Node(s,d, c));
                all_cost += c;
            }

            Collections.sort(list);

            int len = list.size();
            for(int i=0; i<len; i++){
                Node node = list.get(i);
                if (find(node.s) != find(node.d)) {
                    union(node.s, node.d);
                    res+= node.c;
                }
            }
            System.out.println(all_cost - res);
        }
    }
}
