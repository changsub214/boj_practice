import java.util.*;
import java.io.*;

public class boj_2887 {
    static class Point{
        int n;
        int x;
        int y;
        int z;
        public Point(int n, int x, int y, int z){
            this.n = n;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static class Node implements Comparable<Node>{
        int a;
        int b;
        int cost;
        public Node(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static int N, res =0;
    static Point[] point;
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
            if(find(node.a) != find(node.b)){
                union(node.a,node.b);
                res += node.cost;
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        point = new Point[N];
        parent = new int[N];
        for(int i=0; i<N; i++){
            parent[i]= i;
        }
        int num = 0;
        int loop = N;
        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            point[num] = new Point(num,x,y,z);
            num++;
        }
        Arrays.sort(point,(o1,o2)->o1.x-o2.x);
        for(int i=0; i<N-1; i++){
            int dis = Math.abs(point[i].x-point[i+1].x);
            list.add(new Node(point[i].n, point[i+1].n, dis));
        }
        Arrays.sort(point,(o1,o2)->o1.y-o2.y);
        for(int i=0; i<N-1; i++){
            int dis = Math.abs(point[i].y-point[i+1].y);
            list.add(new Node(point[i].n, point[i+1].n, dis));
        }
        Arrays.sort(point,(o1,o2)->o1.z-o2.z);
        for(int i=0; i<N-1; i++){
            int dis = Math.abs(point[i].z-point[i+1].z);
            list.add(new Node(point[i].n, point[i+1].n, dis));
        }
        Collections.sort(list);
        kruskal();
        System.out.print(res);
    }
}
