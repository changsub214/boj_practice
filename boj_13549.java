import java.util.*;
import java.io.*;

public class boj_13549 {
    static final int INF=1000000000;
    static int[] check;
    public static int bfs_0_1_(int s, int d){
        Deque<Integer> dq = new LinkedList<>();
        dq.add(s);
        check[s] = 0; //start
        while(!dq.isEmpty()){
            int point = dq.poll();
            if(point == d){
                return check[d];
            }
            if(point*2 < 100001 && check[point*2]==INF){
                dq.addFirst(2*point);
                check[2*point] = check[point];
            }
            if(point-1 >=0 && check[point-1] == INF){
                dq.addLast(point-1);
                check[point-1] = check[point]+1;
            }
            if(point+1 <100001 && check[point+1] == INF){
                dq.addLast(point+1);
                check[point+1] = check[point]+1;
            }
        }
        return -1;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        check = new int[100001];
        Arrays.fill(check,INF);
        int res = bfs_0_1_(s,d);
        System.out.print(res);
    }
}
