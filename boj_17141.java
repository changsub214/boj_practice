import java.util.*;
import java.io.*;

public class boj_17141 { //BFS + Combination
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, cnt =0, res = Integer.MAX_VALUE;
    static int[][] arr;
    static boolean[] visit;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    static ArrayList<Node> virus;
    public static int[][] copy(){
        int[][] copy = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == 2){
                    copy[i][j] = 0;
                    continue;
                }
                copy[i][j] = arr[i][j];
            }
        }
        for(int i=0; i<virus.size(); i++){
            if(visit[i]){
                Node node = virus.get(i);
                copy[node.x][node.y] = 2;
            }
        }
        return copy;
    }
    public static void combination(int dep, int s){
        if(dep==M){
            int[][] copy = copy();
            bfs(copy,cnt);
            return;
        }
        for(int i=s; i<virus.size(); i++){
            visit[i] = true;
            combination(dep+1, i+1);
            visit[i] = false;
        }
    }
    public static void bfs(int[][] arrs, int cnt){
        Queue<Node> q = new LinkedList<>();

        for(int i=0; i<virus.size(); i++){
            if(visit[i])q.add(virus.get(i));
        }

        int time = 0;

        while(!q.isEmpty()){
            if(res<=time)break; // The mean is this combination is slower than ex-combination
            int len = q.size();
            for(int j=0; j<len; j++){
                Node now = q.poll();
                for(int i=0; i<4; i++){
                    int nx = now.x + move_x[i];
                    int ny = now.y + move_y[i];
                    if(nx>=0 && ny>=0 && nx<N && ny<N){
                        if(arrs[nx][ny] != 0)continue; // meet the wall
                        arrs[nx][ny] = 2; //get infected
                        q.add(new Node(nx,ny));
                        cnt--;
                    }
                }
            }
            time++;
            if(cnt ==0){
                res = time;
                return;
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        virus = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    virus.add(new Node(i,j));
                }
                if(arr[i][j] == 0){
                    cnt++;
                }
            }
        }
        visit = new boolean[virus.size()];
        cnt += virus.size()-M;

        if(cnt ==0)res =0;
        else combination(0,0);

        if(res == Integer.MAX_VALUE){
            System.out.print(-1);
        }else{
            System.out.print(res);
        }
    }
}
