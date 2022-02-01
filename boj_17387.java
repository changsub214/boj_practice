import java.util.*;
import java.io.*;

public class boj_17387 {
    static class Point{
        long x;
        long y;
        public Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
    public static int ccw(Point p1, Point p2, Point p3){
        long r = (p1.x-p2.x)*(p1.y-p3.y) - (p1.x-p3.x)*(p1.y-p2.y);
        if(r>0)return 1; //counterwatch
        else if(r<0)return -1;
        else return 0; // This question do never evoke this case
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Point[] point = new Point[4];
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            long x1 = Integer.parseInt(st.nextToken());
            long y1 = Integer.parseInt(st.nextToken());
            long x2 = Integer.parseInt(st.nextToken());
            long y2 = Integer.parseInt(st.nextToken());
            point[i] = new Point(x1,y1);
            i++;
            point[i] = new Point(x2,y2);
        }
        int a = ccw(point[0],point[1],point[2]);
        int b = ccw(point[0],point[1],point[3]);
        int c = ccw(point[2],point[3],point[0]);
        int d = ccw(point[2],point[3],point[1]);
        int judge1 = a * b;
        int judge2 = c * d;
        boolean compare1 = Math.min(point[0].x, point[1].x) <= Math.max(point[2].x, point[3].x);
        boolean compare2 = Math.min(point[2].x, point[3].x) <= Math.max(point[0].x, point[1].x);
        boolean compare3 = Math.min(point[0].y, point[1].y) <= Math.max(point[2].y, point[3].y);
        boolean compare4 = Math.min(point[2].y, point[3].y) <= Math.max(point[0].y, point[1].y);
        if(judge1<=0 && judge2<=0){
            if(compare1&&compare2&&compare3&&compare4){
                System.out.print(1);  // 1-2-3-4
            }else{
                System.out.print(0); // 1-2 - - - 3-4 (끊어져 있음 따라서 교차 x)
            }
        }else if(judge1<0 && judge2<=0){
            System.out.print(1);
        }else{
            System.out.print(0);
        }
    }
}
