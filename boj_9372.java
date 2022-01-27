import java.util.*;
import java.io.*;

public class boj_9372 {
    static int T, N, M;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-- >0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            while(M-->0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                //그냥 모션만 취함
            }
            //비행기 = 간선, MST문제로 최소의 개수로 모든 국가 방문은 국가-1이다.
            sb.append(N-1).append("\n");
        }
        System.out.print(sb);
    }
}
