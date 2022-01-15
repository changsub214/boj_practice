import java.util.*;
import java.io.*;
/*
class Node{
    int x, y;
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}*/
public class boj_1245 {
        static int N, M, ans = 0, minHeight = Integer.MAX_VALUE;
        static int[][] map;
        static boolean[][] visited;
        static int[][] dt = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    minHeight = Math.min(minHeight, map[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) continue;
                    bfs(i, j , map[i][j]);
                }
            }

            System.out.println(ans);
            br.close();
        }

        public static void bfs(int x, int y, int height) {

            Queue<Loc> q = new LinkedList<>();
            boolean topFlag = true;
            q.add(new Loc(x, y));
            visited[x][y] = true;

            while (!q.isEmpty()) {
                Loc poll = q.poll();
                for (int d = 0; d < 8; d++) {
                    int dx = poll.x + dt[d][0];
                    int dy = poll.y + dt[d][1];
                    if (check(dx, dy)) {
                        if (map[dx][dy] > height) {
                            topFlag = false;
                        } else if (!visited[dx][dy] && map[dx][dy] == height) {
                            visited[dx][dy] = true;
                            q.add(new Loc(dx, dy));
                        }
                    }
                }
            }

            if (topFlag && height > minHeight) ans++;
        }

        public static boolean check(int x, int y) {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        public static class Loc {
            int x, y;

            public Loc(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
   /* static int N, M, res=0, min_h=Integer.MAX_VALUE;
    static int[][] arr;
    static boolean[][] arr2;
    static int[] move_x = {-1,1,0,0,-1,1,-1,1};
    static int[] move_y = {0,0,-1,1,1,1,-1,-1};
    public static void bfs(int x, int y, int h){
        Queue<Node> que = new LinkedList<>();
        boolean flag = true;
        arr2[x][y] = true;
        que.add(new Node(x,y));
        while(!que.isEmpty()){
            Node node = que.poll();
            for(int i=0; i<8; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx>=0 && ny>=0 && nx<N && ny<M){
                    if(!arr2[nx][ny] && arr[nx][ny]==h){
                        arr2[nx][ny] = true; //visit
                        flag= true;
                        que.add(new Node(nx,ny));
                    }else if(h<arr[nx][ny]){
                        flag= false;
                    }
                }
            }
        }
        if(flag && h>min_h){
            res++;
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        arr = new int[N][M];
        arr2 = new boolean[N][M];
        for(int i=0; i<N; i++){
            String[] temp = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(temp[j]);
                min_h = Math.min(arr[i][j],min_h);
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(arr2[i][j])continue;
                bfs(i,j,arr[i][j]);
            }
        }
        System.out.print(res);
        br.close();
    }*/
}
