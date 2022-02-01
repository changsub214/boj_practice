import java.util.*;
import java.io.*;

public class boj_11758 {
    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x =x;
            this.y =y;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Point[] point = new Point[3];
        int r = 0;
        while(r<3){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            point[r] = new Point(x, y);
            r++;
        }
        int judge = (point[0].x-point[1].x)*(point[0].y-point[2].y)-(point[0].x-point[2].x)*(point[0].y-point[1].y);
        if(judge>0)System.out.print(1);
        else if(judge<0)System.out.print(-1);
        else System.out.print(0);
    }
}
