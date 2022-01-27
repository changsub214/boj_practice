import java.util.*;
import java.io.*;

public class boj_14502 {
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, max=Integer.MIN_VALUE;
    static int[][] arr;
    static int[][] arr2;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    static ArrayList<Node> list;
    public static int[][] copy(int[][] arr){
        int[][] c_c = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                c_c[i][j] = arr[i][j];
            }
        }
        return c_c;
    }
    public static void make_wall(int wall){
        if(wall==3){
            spread_virus();
            return;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(arr2[i][j] == 0){
                    arr2[i][j] = 1;
                    make_wall(wall+1);
                    arr2[i][j] = 0;
                }
            }
        }
    }
    public static void spread_virus(){
        int[][] copy__ = copy(arr2);
        Queue<Node> q = new LinkedList<>();
        int len = list.size();
        for(int i=0; i<len; i++){
            q.add(list.get(i));
        }
        while(!q.isEmpty()){
            Node node = q.poll();
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M)continue;
                if(copy__[nx][ny] == 0){
                    copy__[nx][ny] = 2;
                    q.add(new Node(nx,ny));
                }
            }
        }
        int get = check(copy__);
        max = Math.max(max,get);
    }
    public static int check(int[][] copy_arr){
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(copy_arr[i][j] == 0)cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        arr2 = new int[N][M];
        list = new ArrayList<>();
        for(int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j< M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2)list.add(new Node(i,j));
            }
        }
        arr2 = copy(arr);
        make_wall(0);
        System.out.print(max);
    }
}
