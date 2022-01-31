import java.util.*;
import java.io.*;

public class boj_15686 {
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, res = Integer.MAX_VALUE;
    static int[][] arr;
    static boolean[] open;
    static ArrayList<Node> home;
    static ArrayList<Node> chicken;
    public static void dfs(int s, int chicken_num){
        if(chicken_num == M){
            int res2 = 0;
            //한 집에서 오픈한 모든 치킨집의 거리를 구하면서 치킨거리를 선정
            for(int i=0; i<home.size(); i++){
                int temp = Integer.MAX_VALUE;
                for(int j=0; j<chicken.size(); j++){
                    if(open[j]){
                        int dis = Math.abs(home.get(i).x-chicken.get(j).x) + Math.abs(home.get(i).y-chicken.get(j).y);
                        temp = Math.min(temp,dis);
                    }
                }
                res2 += temp;
            }
            res = Math.min(res,res2);
            return;
        }
        for(int i=s; i<chicken.size(); i++){
            open[i] = true;
            dfs(i+1,chicken_num+1);
            open[i] = false;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1){
                    home.add(new Node(i, j));
                }
                if(arr[i][j] == 2){
                    chicken.add(new Node(i, j));
                }
            }
        }
        open = new boolean[chicken.size()];
        dfs(0,0);
        System.out.print(res);

    }
}
