import java.util.*;
import java.io.*;
/*class Node{
    int x;
    int y;
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}*/
public class boj_17090 { //if you use bfs, you'll get 'time over'
   /* static int N,M;
    static int[][] arr;
    static boolean[][] oper;
    static int[] move_x = {-1,0,1,0};
    static int[] move_y = {0,1,0,-1};
    public static int bfs(){
        int cnt = 0;
        Queue<Node> que = new LinkedList<>();
        for(int i=1; i<N+1; i++){
            for(int j=1; j<M+1; j++){
                int[][] arr2 = new int[N+2][M+2];
                que.add(new Node(i,j));
                arr2[i][j] = 1; //visit
                while(!que.isEmpty()){
                    Node node = que.poll();
                    int nx, ny;
                    if(arr[node.x][node.y]==5){
                        nx = node.x + move_x[0];
                        ny = node.y + move_y[0];
                    }else if(arr[node.x][node.y]==6){
                        nx = node.x + move_x[1];
                        ny = node.y + move_y[1];
                    }else if(arr[node.x][node.y]==7){
                        nx = node.x + move_x[2];
                        ny = node.y + move_y[2];
                    }else{
                        nx = node.x + move_x[3];
                        ny = node.y + move_y[3];
                    }
                    if(nx<1 || ny<1 || nx>N || ny>M){ //this means it can escape the maze
                        cnt++;
                        que.clear();
                    }else if(nx>0 && ny>0 && nx<=N && ny<=M){
                        if(arr2[nx][ny] == 0){
                            arr2[nx][ny] = 1; //visit
                            que.add(new Node(nx,ny));
                        }
                    }
                }
            }
        }
        return cnt;
    }*/
    static int N, M, res=0, num=1;
    static int[][] arr;
    static boolean[][] visit;
    public static boolean dfs(int x, int y){
        boolean result = false;
        if(check(x,y))return true;
        if(arr[x][y] == 5)return true;
        else if(arr[x][y] == 6)return false;

        if(visit[x][y]){ //have cycle
            return false;
        }
        visit[x][y] = true;
        if(arr[x][y] == 0){
            result = dfs(x-1,y);
        }else if(arr[x][y] == 1){
            result = dfs(x,y+1);
        }else if(arr[x][y] == 2){
            result = dfs(x+1,y);
        }else{
            result = dfs(x,y-1);
        }

        if(result == true){
            arr[x][y] = 5;
        }else{
            arr[x][y] = 6;
        }

        return result;
    }
    public static boolean check(int x, int y){
        return x<0 || y<0 || x>=N || y>=M;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] r = br.readLine().split(" ");
        N = Integer.parseInt(r[0]);
        M = Integer.parseInt(r[1]);
        arr = new int[N][M];
        visit = new boolean[N][M];
        for(int i=0; i<N; i++){
            String temp = br.readLine();
            for(int j=0; j<M; j++){
                char c = temp.charAt(j);
                if(c == 'U'){
                    arr[i][j] = 0;
                }else if(c == 'R'){
                    arr[i][j] = 1;
                }else if(c == 'D'){
                    arr[i][j] = 2;
                }else{
                    arr[i][j] = 3;
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(dfs(i,j))res++;
            }
        }
        System.out.print(res);
        br.close();
     /*   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] r = br.readLine().split(" ");
        N = Integer.parseInt(r[0]);
        M = Integer.parseInt(r[1]);
        arr = new int[N+2][M+2];
        oper = new boolean[N+2][M+2];
        for(int i=1; i<N+1; i++){
            String temp = br.readLine();
            for(int j=0; j<temp.length(); j++){
                if(temp.charAt(j) == 'U'){
                    arr[i][j+1] = 5;
                }else if(temp.charAt(j) == 'R'){
                    arr[i][j+1] = 6;
                }else if(temp.charAt(j) == 'D'){
                    arr[i][j+1] = 7;
                }else if(temp.charAt(j) == 'L'){
                    arr[i][j+1] = 8;
                }
            }

        }
        int res= bfs();
        System.out.print(res);*/
    }
}
