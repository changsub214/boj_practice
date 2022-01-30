import java.util.*;
import java.io.*;

public class boj_17472 {
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Node2{
        int x;
        int y;
        int dis;
        public Node2(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    static class Bridge implements Comparable<Bridge>{
        int s;
        int d;
        int dis;
        public Bridge(int s, int d, int dis){
            this.s = s;
            this.d = d;
            this.dis = dis;
        }
        @Override
        public int compareTo(Bridge o) {
            return this.dis - o.dis; //ascending
        }
    }
    static PriorityQueue<Bridge> pq = new PriorityQueue();
    static int N, M, res =0;
    static int[] parent;
    static int[][] map;
    static boolean[][] visit;
    static boolean[][] visit2;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    public static void confer_num_to_island(int x, int y,int num){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        while(!q.isEmpty()){
            Node node = q.poll();
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M)continue;
                if(visit[nx][ny])continue;
                if(map[nx][ny] == 1){
                    map[nx][ny] = num;
                    visit[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }
            }
        }
    }
    public static void make_bridge(int x, int y, int idx){
        Queue<Node2> q = new LinkedList<>();
        visit2 = new boolean[N][M];
        //You have to build a bridge from each point to 4 directions
        for(int i=0; i<4; i++){
            q.add(new Node2(x, y ,0));
            visit2[x][y] = true;
            while(!q.isEmpty()){
                Node2 node = q.poll();
                int nx = node.x +move_x[i];
                int ny = node.y +move_y[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M)continue;
                if(visit2[nx][ny])continue;
                if(map[nx][ny] != idx){
                    if(map[nx][ny] != 0){
                        int from = idx; //start vertex
                        int to = map[nx][ny]; //end vertex
                        if(node.dis>1){
                            pq.add(new Bridge(from,to,node.dis));
                            break;
                        }
                    }else{
                        visit2[nx][ny] = true;
                        q.add(new Node2(nx,ny, node.dis+1));
                    }
                }
            }
        }
    }
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
    public static int kruskal(){
        int len = pq.size();
        for(int i=0; i<len; i++){
            Bridge node = pq.poll();
            if(find(node.s) != find(node.d)){
                union(node.s, node.d);
                res += node.dis;
            }
        }
        for(int i=3; i<parent.length; i++){
        //섬이 2번부터 시작하기때문임
            if(parent[2] != find(parent[i]))return -1;
        }
        return res;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int island_num  = 2;
        visit = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] ==1 && !visit[i][j]){
                    map[i][j] = island_num;
                    visit[i][j] = true;
                    confer_num_to_island(i,j,island_num++);
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] != 0){
                    make_bridge(i, j, map[i][j]);
                }
            }
        }
        island_num--; // We need to minus -1 because 1 is added to the number of islands
        parent = new int[island_num+1];
        for(int i=1; i<island_num+1; i++){
            parent[i] = i;
        }
        System.out.print(kruskal());
       /* int len = pq.size();
        for(int i=0; i<len; i++){
            Bridge node = pq.poll();
            System.out.println(node.s+1 + " " + (node.d+1) + " " + node.dis);
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }*/
    }
}
