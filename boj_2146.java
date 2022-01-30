import java.util.*;
import java.io.*;

public class boj_2146 {
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Bridge{
        int x;
        int y;
        int dis;
        public Bridge(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    static int N;
    static int minimum = Integer.MAX_VALUE;
    static int[][] arr;
    static boolean[][] visit;
    static boolean[][] visit2;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    public static void confer_numer_to_island(int x, int y, int idx){
        Queue<Node> q = new LinkedList();
        q.add(new Node(x, y));
        arr[x][y] = idx;
        visit[x][y] = true;
        while(!q.isEmpty()){
            Node node = q.poll();
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N)continue;
                if(arr[nx][ny] == 1 && !visit[nx][ny]){
                    arr[nx][ny] = idx;
                    visit[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }
            }
        }
    }
    public static void make_bridge(int x, int y, int idx){
        Queue<Bridge> q = new LinkedList<>();
        visit2 = new boolean[N][N];
        int temp = Integer.MAX_VALUE;
        q.add(new Bridge(x,y,0));
        visit2[x][y] = true;
        while(!q.isEmpty()) {
            Bridge node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visit2[nx][ny]) continue;
                visit2[nx][ny] = true;
                if (arr[nx][ny] != idx) {
                    if (arr[nx][ny] != 0) {
                        minimum = Math.min(minimum, node.dis);
                    } else {
                        q.add(new Bridge(nx, ny, node.dis + 1));
                    }
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[N][N];
        int island_num = 2;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == 1 && !visit[i][j]){
                    confer_numer_to_island(i,j,island_num++);
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] != 0){
                    make_bridge(i, j, arr[i][j]);
                }
            }
        }
        System.out.print(minimum);
    }
}
