import java.util.*;
import java.io.*;

public class boj_12781 {
    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int CCW(Point p1, Point p2, Point p3){
        long r = (p1.x-p2.x)*(p1.y-p3.y) - (p1.x-p3.x)*(p1.y-p2.y);
        if(r>0)return 1; //counter-clock
        else if(r<0)return -1; //clock
        else return 0; //never use in this question
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());;
        Point[] point = new Point[4];
        for(int i=0; i<4; i++){
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            point[i] = new Point(x,y);
        }
        int a = CCW(point[0],point[1],point[2]);
        int b = CCW(point[0],point[1],point[3]);
        int c = CCW(point[2],point[3],point[0]);
        int d = CCW(point[2],point[3],point[1]);
        if(a*b <0 && c*d <0){
            System.out.print(1);
        }else{
            System.out.print(0);
        }
    }
}
