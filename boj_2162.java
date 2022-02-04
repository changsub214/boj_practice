import java.util.*;
import java.io.*;

public class boj_2162 {
    static class Point{
        int x1;
        int y1;
        int x2;
        int y2;
        public Point(int x1,int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    static int N, INF=1987654321;
    static Point[] point;
    static int[] parent;
    static int[] line_group;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y)parent[y] = x;
            else parent[x] = y;
        }
    }
    public static double intersection_x(Point p1, Point p2){
        long son = (p1.x1*p1.y2 - p1.x2*p1.y1)*(p2.x1-p2.x2) - (p1.x1-p1.x2)*(p2.x1*p2.y2 - p2.x2*p2.y1);
        long motehr = (p1.x1-p1.x2)*(p2.y1-p2.y2) - (p1.y1-p1.y2)*(p2.x1-p2.x2);
        if(motehr == 0){
            return INF;
        }
        return son/motehr;
    }
    public static double intersection_y(Point p1, Point p2){
        long son = (p1.x1*p1.y2 - p1.x2*p1.y1)*(p2.y1-p2.y2) - (p1.y1-p1.y2)*(p2.x1*p2.y2 - p2.x2*p2.y1);
        long mother = (p1.x1-p1.x2)*(p2.y1-p2.y2) - (p1.y1-p1.y2)*(p2.x1-p2.x2);
        if(mother == 0){
            return INF;
        }
        return son/mother;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        point = new Point[N+1];
        parent = new int[N+1];
        line_group = new int[N+1];
        int cnt = 1;
        int loop = N;
        while(loop-- >0){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            point[cnt] = new Point(x1,y1,x2,y2);
            parent[cnt] = cnt;
            cnt++;
        }

        for(int i=1; i<N; i++){
            for(int j=i+1; j<N+1; j++){
                Point p1 = point[i];
                Point p2 = point[j];

                double r = intersection_x(p1, p2);

                //p1, p2 평행 or 일치
                if(r == INF){
                    if(p1.x2 - p1.x1 == 0 && p2.x2 - p2.x1 == 0){ // This case ) x = a
                        if(p1.x1 != p2.x1){
                            continue;
                        }
                        if(p1.x2 - p1.x1 == 0 && p2.x2 - p2.x1 == 0){
                            if ((p1.y1 > p2.y1 && p1.y1 > p2.y2 && p1.y2 > p2.y1 && p1.y2 > p2.y2)
                                    || (p2.y1 > p1.y1 && p2.y1 > p1.y2 && p2.y2 > p1.y1 && p2.y2 > p1.y2)) {
                                continue;
                            }
                            union(i, j);
                            continue;
                        }
                    }
                    //not is x = a
                    double g1 = (p1.y2-p1.y1)/(p1.x2-p1.x1);
                    double g2 = (p2.y2-p2.y1)/(p2.x2-p2.x1);
                    double n1 = -g1* p1.x1 + p1.y1;
                    double n2 = -g2* p2.x1 + p2.y1;
                    if(n1 != n2){
                        //평행
                        continue;
                    }
                    if((p1.x1 > p2.x1 && p1.x1 > p2.x2 && p1.x2 > p2.x1 && p1.x2 > p2.x2)
                            || (p2.x1 > p1.x2 && p2.x1 > p1.x2 && p2.x2 > p1.x1 && p2.x2 > p1.x2)){
                        //일치하지만 만나지않는 경우
                        continue;
                    }
                    if((p1.y1 > p2.y1 && p1.y1 > p2.y2 && p1.y2 > p2.y1 && p1.y2 > p2.y2)
                            || (p2.y1 > p1.y1 && p2.y1 > p1.y2 && p2.y2 > p1.y1 && p2.y2 > p1.y2)){
                        // y = a (기울기 0)
                        continue;
                    }
                    union(i, j);
                }else{
                    //교점이 p1, p2선분 내에 존재하는지 확인
                    if(r>= Math.min(p1.x1, p1.x2) && r<= Math.max(p1.x1, p1.x2) &&
                    r>= Math.min(p2.x1, p2.x2) && r<= Math.max(p2.x1, p2.x2)){
                        r = intersection_y(p1, p2);
                        if (r <= INF && r >= -INF) {
                            if (r >= Math.min(p1.y1, p1.y2) && r <= Math.max(p1.y1, p1.y2)
                                    && r >= Math.min(p2.y1, p2.y2) && r <= Math.max(p2.y1, p2.y2)) {
                                union(i, j);
                            }
                        }
                    }
                }
            }
        }
        for(int i=1; i<N+1; i++){
            line_group[find(i)]++;
        }
        int g_cnt = 0;
        int l_cnt = 0;
        for(int i=1; i<N+1; i++){
            if(line_group[i] > 0)g_cnt++;

            l_cnt = Math.max(l_cnt, line_group[i]);
        }
        System.out.println(g_cnt);
        System.out.print(l_cnt);
    }
}
