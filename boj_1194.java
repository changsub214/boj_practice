import java.util.*;
import java.io.*;

public class boj_1194 {
    static class Node{
        int x;
        int y;
        int key;
        int dis;
        public Node(int x, int y, int key, int dis){
            this.x = x;
            this.y = y;
            this.key = key;
            this.dis = dis;
        }
    }
    static int N, M;
    static char[][] arr;
    static boolean[][][]visit;
    static boolean[] room = new boolean[6];
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    public static int bfs(int s_x, int s_y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s_x, s_y, 0,0));
        visit[s_x][s_y][0] = true;
        while(!q.isEmpty()){
            Node node = q.poll();
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx>=0 && ny>=0 && nx<N && ny<M){
                    //meet the exit
                    if(arr[node.x][node.y] == '1')return node.dis;
                    //meet the wall
                    if(arr[nx][ny] == '#')continue;
                    //meet the already visited point
                    if(visit[nx][ny][node.key])continue;
                    if(arr[nx][ny] - 'a' >= 0 && arr[nx][ny] - 'a' < 6){
                        //THINK ASCII CODE !!
                        //AND USE BIT-MASKING
                        int temp_key = node.key | (1 << (arr[nx][ny] -'a'));
                        if(!visit[nx][ny][temp_key]){
                            visit[nx][ny][temp_key] = true;
                            visit[nx][ny][node.key] = true;
                            q.add(new Node(nx,ny,temp_key, node.dis+1));
                        }
                    }else if(arr[nx][ny] - 'A' >= 0 && arr[nx][ny] -'A' < 6){
                        int temp_door = node.key & (1 << (arr[nx][ny] - 'A'));
                        if(temp_door >0){ //have any key
                            visit[nx][ny][node.key] = true;
                            q.add(new Node(nx,ny,node.key, node.dis+1));
                        }
                    }else{
                        visit[nx][ny][node.key] = true;
                        q.add(new Node(nx,ny,node.key,node.dis+1));
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visit = new boolean[N][M][64]; //key's number of case is 64 (2^6 // key is 'a','b','c','d','e','f')
        int s_x =0, s_y =0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for(int j=0; j<M; j++){
                arr[i][j] = temp.charAt(j);
                if(arr[i][j] == '0'){
                    s_x = i;
                    s_y = j;
                }
            }
        }
        int res = bfs(s_x,s_y);
        System.out.print(res);
      /* for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }*/
    }
}
