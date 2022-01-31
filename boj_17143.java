import java.util.*;
import java.io.*;

public class boj_17143 {
    static class Shark{
        int r; //row
        int c; //col
        int s; //velocity
        int d; //direction
        int z; //size
        public Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
    static int R, C, M, res =0;
    static Shark[][] arr;
    static int[] move_x = {0,0,1,-1}; // 3 : right 4 : left
    static int[] move_y = {-1,1,0,0}; // 1 : up 2 : down
    public static void shark_catch(int col){
        for(int i=0; i<R; i++){
            if(arr[i][col]!=null){
                res += arr[i][col].z;
                arr[i][col] = null; //removed shark
                break;
            }
        }
    }
    public static void shark_move(){
        Queue<Shark> q = new LinkedList<>();
        for(int i=0; i< R; i++){
            for(int j=0; j< C; j++){
                if(arr[i][j] != null){
                    q.add(new Shark(i,j,arr[i][j].s,arr[i][j].d,arr[i][j].z));
                }
            }
        }
        arr = new Shark[R][C];
        while(!q.isEmpty()){
            Shark node = q.poll();
            int dir = node.d;
            int velocity = node.s;
            if(dir == 1 || dir == 2){ // up or down
                velocity = velocity % ((R-1) * 2);
            }
            if(dir == 3 || dir == 4){ // right or left
                velocity = velocity % ((C-1) * 2);
            }
            for(int i=0; i<velocity; i++){
                int nr = node.r + move_y[node.d-1];
                int nc = node.c + move_x[node.d-1];

                if(nr< 0 || nc<0 || nr>=R || nc>=C){
                    node.r -= move_y[node.d-1];
                    node.c -= move_x[node.d-1];
                    if(node.d == 1){
                        node.d = 2;
                    }else if(node.d == 2){
                        node.d = 1;
                    }else if(node.d == 4){
                        node.d = 3;
                    }else{
                        node.d = 4;
                    }
                    continue;
                }
                node.r = nr;
                node.c = nc;
            }

            //shark_eating
            shark_eat(arr,node);
        }
    }
    public static void shark_eat(Shark[][] arr,Shark node){
        if(arr[node.r][node.c] != null){
            if(node.z > arr[node.r][node.c].z){
                arr[node.r][node.c] = new Shark(node.r,node.c,node.s,node.d,node.z);
            }
        }else{
            arr[node.r][node.c] = new Shark(node.r,node.c,node.s,node.d,node.z);
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());//row
        C = Integer.parseInt(st.nextToken());//col
        M = Integer.parseInt(st.nextToken());//shark
        arr = new Shark[R][C];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            arr[r-1][c-1] = new Shark(r-1, c-1, s, d, z);
        }

        for(int i=0; i<C; i++){
            shark_catch(i);
            shark_move();
        }
        System.out.print(res);
    }
}
