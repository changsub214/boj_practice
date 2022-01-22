import java.util.*;
import java.io.*;

public class boj_6087 {
    static class Laser{
        int x;
        int y;
        int dir;
        int mirror;
        public Laser(int x, int y, int dir, int mirror){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }
    }
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N,M;
    static char[][] arr;
    static int[][] save_mirror;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    static ArrayList<Node> list;
    public static int bfs_0_1_(Node s, Node d){
        Deque<Laser> dq = new ArrayDeque<>();
        for(int i=0; i<4; i++){
            //The reason for starting like here to determine
            //whether the direction has been changed
            dq.add(new Laser(s.x,s.y, 0,0)); //begin
            dq.add(new Laser(s.x,s.y, 1,0));
            dq.add(new Laser(s.x,s.y, 2,0));
            dq.add(new Laser(s.x,s.y, 3,0));
        }
        save_mirror[s.x][s.y] = 0;
        while(!dq.isEmpty()){
            Laser node = dq.poll();
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                int temp_mirror = node.mirror;
                if(nx>=0 && ny>=0 && nx<N && ny<M){
                    if(arr[nx][ny] == '*')continue;
                    //if the laser evoke to change the direction, plus one mirror
                    //and check whether it is minimum or not
                    if(node.dir != i){
                        temp_mirror++;
                    }
                    if(save_mirror[nx][ny] < temp_mirror)continue;
                    save_mirror[nx][ny] = temp_mirror;
                    dq.add(new Laser(nx,ny,i,temp_mirror));
                }
            }
        }
        return save_mirror[d.x][d.y];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        save_mirror = new int[N][M];
        list = new ArrayList<>();
        for(int i=0; i< save_mirror.length; i++){
            Arrays.fill(save_mirror[i],Integer.MAX_VALUE);
        }
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for(int j=0; j<M; j++){
                arr[i][j] = temp.charAt(j);
                if(arr[i][j] == 'C'){
                    list.add(new Node(i,j));
                }
            }
        }
        int res = bfs_0_1_(list.get(0),list.get(1));
        System.out.print(res);

    }
}
