import java.util.*;
import java.io.*;

class Node{
    int x;
    int y;
    int change;
    Node(int x, int y, int change){
        this.x = x;
        this.y = y;
        this.change = change;
    }
}
public class boj_2665 {
    static int N;
    static int[][] arr;
    static boolean[][][] visit;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    public static int bfs(){
        for(int k=1; k<=N+1; k++){
            // k=1 : can't never change
            // k=2 : can change the one black room
            // k=3 : can change the ttwo black room
            // ... limit is K = 11 ('Cause N has limit 10)
            Queue<Node> que = new LinkedList<>();
            que.add(new Node(0,0,0));
            visit = new boolean[N][N][k];
            while(!que.isEmpty()){
                Node node = que.poll();
                if(node.x == N-1 && node.y == N-1){
                    return node.change;
                }
                for(int i=0; i<4; i++){
                    int nx = node.x + move_x[i];
                    int ny = node.y + move_y[i];
                    if(nx>=0 && ny>=0 && nx<N && ny<N){
                        if(node.change<k && arr[nx][ny] == 1){ //white room
                            if(!visit[nx][ny][node.change]){
                                que.add(new Node(nx,ny,node.change));
                                visit[nx][ny][node.change] = true;
                            }
                        }else if(arr[nx][ny] == 0){ //black room
                            if(node.change<k && !visit[nx][ny][node.change]){
                                que.add(new Node(nx,ny,node.change + 1));
                                visit[nx][ny][node.change] = true;
                            }
                        }
                    }
                }
            }
        }
        return -1; //끝까지 안되는 경우인데 이 경우는 생길 수가 없음
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        N = Integer.parseInt(temp);
        arr = new int[N][N];
        for(int i=0; i<N; i++){
            String[] temp_ = in.next().split("");
            for(int j=0; j<temp_.length; j++){
                arr[i][j] = Integer.parseInt(temp_[j]);
            }
        }

        int res = bfs();
        System.out.print(res);
    }
}
