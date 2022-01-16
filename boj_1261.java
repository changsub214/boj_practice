import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{

  int x, y, dig;
  public Node(int x, int y, int dig){
    this.x = x;
    this.y = y;
    this.dig = dig;
  }

  @Override
  public int compareTo(Node o) {
    return this.dig - o.dig;
  }
}
public class boj_1261 {

    static int N, M;
    static int[][] arr;
    static int[][] des;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    public static int dijkstra(int x, int y){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x,y,0));
        des[x][y] = arr[x][y];
        while(!pq.isEmpty()){
          Node node = pq.poll();

          if(node.x == N-1 && node.y == M-1){
            return node.dig;
          }

          for(int i=0; i<4; i++){
            int nx = node.x + move_x[i];
            int ny = node.y + move_y[i];
            if(nx>=0 && ny>=0 && nx<N && ny<M){
              if(des[nx][ny] > des[node.x][node.y] + arr[nx][ny]){
                des[nx][ny] = des[node.x][node.y] + arr[nx][ny];
                pq.add(new Node(nx,ny,des[nx][ny]));
              }
            }
          }
        }
        return 0;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String t = br.readLine();
        st = new StringTokenizer(t);
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        des = new int[N][M];
        for(int i=0; i<N; i++){
          String[] r = br.readLine().split("");
          for(int j=0; j<M; j++){
            arr[i][j] = Integer.parseInt(r[j]);
          }
        }
        for(int i=0; i<N; i++){
          for(int j=0; j<M; j++){
            des[i][j] = Integer.MAX_VALUE;
          }
        }
        int res = dijkstra(0,0);
        System.out.print(res);
     }
}
