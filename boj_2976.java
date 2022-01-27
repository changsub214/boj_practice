import java.util.*;
import java.io.*;

public class boj_2976 {
    static class Node implements Comparable<Node>{
        int s;
        int d;
        double c;
        public Node(int s, int d, double c){
            this.s = s;
            this.d = d;
            this.c = c;
        }
        @Override
        public int compareTo(Node o) {
            if(this.c < o.c){
                return -1;
            }
            return 1;
        }
    }
    static int N, M;
    static double res = 0;
    static int[][] arr;
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
        int len = list.size();
        for(int i=0; i<len; i++){
            Node node = list.get(i);
            if(find(node.s) != find(node.d)){
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
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        parent = new int[N+1];
        list = new ArrayList<>();
        for(int i=0; i<N; i++){
            parent[i] = i;
        }
        int n = 0;
        while(N-- >0){
            st = new StringTokenizer(br.readLine());
            arr[n][0] = Integer.parseInt(st.nextToken());
            arr[n][1] = Integer.parseInt(st.nextToken());
            n++;
        }
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                double dis = Math.sqrt(Math.pow(Math.abs(arr[j][0]-arr[i][0]),2)+Math.pow(Math.abs(arr[j][1]-arr[i][1]),2));
                list.add(new Node(i,j,dis));
            }
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a-1,b-1);
        }
        Collections.sort(list);
        kruskal();
        System.out.printf("%.2f",res);
    }
}
