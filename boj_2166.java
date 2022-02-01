import java.util.*;
import java.io.*;

public class boj_2166 {
    static class Point{
        long x;
        long y;
        public Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static Point[] point;
    public static long area(){
        long res1 = 0L;
        long res2 = 0L;
        for(int i=0; i<N; i++){
            res1 += point[i].x * point[i+1].y;
            res2 += point[i].y * point[i+1].x;
        }
        return res1-res2;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long res = 0;
        point = new Point[N+1];
        int z= 0;
        while(z<N){
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            point[z] = new Point(x, y);
            z++;
        }
        point[N] = new Point(point[0].x, point[0].y);
        System.out.printf("%.1f", Math.abs(area())/2D);
    }
}
