import java.util.*;
import java.io.*;

class Node{
    int x;
    int y;
    int des;
    int dig;
    Node(int x,int y, int des, int dig){
        this.x = x;
        this.y = y;
        this.des = des;
        this.dig = dig;
    }
}
public class boj_14923 {
    static int N,M;
    static int[][] arr;
    static boolean[][][] visit; // [][][0] don't dig [][][1] did dig
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    public static int bfs(int s_x,int s_y, int d_x, int d_y){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(s_x-1,s_y-1,0,0));
        visit[0][0][0] = true;
        visit[0][0][1] = true;
        while(!que.isEmpty()){
            Node node = que.poll();
            if(node.x == d_x-1 && node.y == d_y-1){
                return node.des;
            }
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx>=0 && ny>=0 && nx<N && ny<M){
                    if(arr[nx][ny] == 0){
                        //한번 벽을 뚫고 온 애랑 벽을 뚫지않고 온 애랑 구별해서 방문체크 해야함
                        if(visit[nx][ny][node.dig] == false){
                            que.add(new Node(nx,ny, node.des +1,node.dig));
                            visit[nx][ny][node.dig] = true;
                        }
                    }else if(arr[nx][ny] == 1){ //mee the wall
                        if(visit[nx][ny][1] == false && node.dig == 0){
                            que.add(new Node(nx,ny,node.des+1,node.dig+1));
                            visit[nx][ny][1] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        arr = new int[N][M];
        visit = new boolean[N][M][2];
        int s_x = in.nextInt();
        int s_y = in.nextInt();
        int d_x = in.nextInt();
        int d_y = in.nextInt();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                arr[i][j] = in.nextInt();
            }
        }
        int res = bfs(s_x,s_y,d_x,d_y);
        System.out.print(res);

    }
}
