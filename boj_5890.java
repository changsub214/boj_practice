import java.util.*;
import java.io.*;

public class boj_5890 {
    static class Tractor implements Comparable<Tractor>{
        int x;
        int y;
        int c;
        public Tractor(int x, int y, int c){
            this.x =x;
            this.y =y;
            this.c =c;
        }

        @Override
        public int compareTo(Tractor o) {
            return this.c-o.c;
        }
    }
    static int N, s_x, s_y;
    static int[] move_x = {-1,1,0,0};
    static int[] move_y = {0,0,-1,1};
    static int[][] arr;
    static int[][] hay;
    public static int dijkstra(int x, int y){
        PriorityQueue<Tractor> pq = new PriorityQueue<>();
        pq.add(new Tractor(x,y,0)); //begin
        hay[x][y] = 0;
        while(!pq.isEmpty()){
            Tractor node = pq.poll();
            if(hay[node.x][node.y] < node.c)continue;
            for(int i=0; i<4; i++){
                int nx = node.x + move_x[i];
                int ny = node.y + move_y[i];
                if(nx <1 || ny<1 || nx>1000 || ny>1000){ //the tractor get free!
                    return hay[node.x][node.y];
                }
                if(hay[nx][ny] > hay[node.x][node.y] + arr[nx][ny]){
                    hay[nx][ny] = hay[node.x][node.y] + arr[nx][ny];
                    pq.add(new Tractor(nx,ny,hay[nx][ny]));
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
        s_x = Integer.parseInt(st.nextToken());
        s_y = Integer.parseInt(st.nextToken());
        arr = new int[1002][1002];
        hay = new int[1002][1002];
        for(int i=0; i< hay.length; i++){
            Arrays.fill(hay[i],Integer.MAX_VALUE);
        }
        while(N-- >0){
            st = new StringTokenizer(br.readLine());
            int temp_x = Integer.parseInt(st.nextToken());
            int temp_y = Integer.parseInt(st.nextToken());
            arr[temp_x][temp_y] = 1;
        }
        int res = dijkstra(s_x,s_y);
        System.out.print(res);
    }
}
