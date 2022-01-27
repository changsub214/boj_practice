import java.util.*;
import java.io.*;

public class boj_4386 {
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
            if (this.c < o.c) {
                return -1;
            }
            return 1;
        }
    }
    static int N;
    static double res = 0;
    static double[][] arr;
    static int[] parent;
    static ArrayList<Node> star;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){ //if x==y, that is cycle
            if(x<y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }
    public static void kruskal(){
        for(int i=0; i<star.size(); i++){
            Node node = star.get(i);
            if(find(node.s) != find(node.d)){
                res += node.c;
                union(node.s,node.d);
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new double[N][2];
        parent = new int[N];
        star = new ArrayList<>();
        for(int i=0; i<N; i++){
            parent[i] = i;
        }
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++){
                arr[i][j] = Double.parseDouble(st.nextToken());
            }
        }
        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){
                double distance = Math.sqrt(Math.pow(Math.abs(arr[j][0]-arr[i][0]),2)+Math.pow(Math.abs(arr[j][1]-arr[i][1]),2));
                star.add(new Node(i,j, distance));
            }
        }
        Collections.sort(star); //kruskal algorithm needs ascending-sort
        kruskal();
        System.out.printf("%.2f",res);
    }
}
