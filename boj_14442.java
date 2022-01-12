import java.util.*;
import java.io.*;

class Node{
    int x;
    int y;
    int des;
    int dig;
    Node(int x, int y, int des, int dig){
        this.x = x;
        this.y = y;
        this.des = des;
        this.dig = dig;
    }
}
public class boj_14442 {
    static int N, M, K;
    static int[][] arr;
    static boolean[][][] arr2; // [][][0] : ground [][][1] : wall
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    public static int bfs(int s_x, int s_y){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(s_x-1,s_y-1,1,0));
        while(!que.isEmpty()){
            Node node = que.poll();
            if(node.x == N-1 && node.y == M-1 && node.dig <= K){
                return node.des;
            }
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx>=0 && ny>=0 && nx<N && ny<M){
                    if(arr[nx][ny] == 0){ //the ground
                        if(arr2[nx][ny][node.dig] == false){
                            que.add(new Node(nx,ny,node.des+1,node.dig));
                            arr2[nx][ny][node.dig] = true;
                        }
                    }else if(arr[nx][ny] == 1){ //meet the wall
                        if(arr2[nx][ny][node.dig] == false && node.dig<K){
                            que.add(new Node(nx,ny,node.des+1,node.dig+1));
                            arr2[nx][ny][node.dig] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String[] r = in.nextLine().split(" ");
        N = Integer.parseInt(r[0]);
        M = Integer.parseInt(r[1]);
        K = Integer.parseInt(r[2]);
        arr = new int[N][M];
        arr2 = new boolean[N][M][K+1];
        for(int i=0; i<N; i++){
            String[] temp = in.next().split("");
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int res = bfs(1, 1);
        System.out.print(res);

    }
}
