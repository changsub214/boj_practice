import javax.sound.sampled.Line;
import java.util.*;
import java.io.*;

public class boj_12851 {
    static int S, D, cnt= 0;
    static int[] arr;
    static int time = Integer.MAX_VALUE;
    public static void bfs(int s, int d){
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        arr[s] = 1; //begin
        while(!q.isEmpty()){
            int t = q.poll();
            if(time < arr[t])return;
            for(int i=0; i<3; i++){
                int temp = 0;
                if(i==0){ //move +1
                    temp = t+1;
                }else if(i==1){ //move -1
                    temp = t-1;
                }else{ //jump *2
                    temp = 2*t;
                }
                if(0<=temp && temp<100001){
                    //We need to permit duplicate visits
                    //Because we should find several route
                    //so we must consider three case in the same point (-1, +1, *2)
                    if(temp == d){
                        time = arr[t];
                        cnt++;
                    }
                    //현시간이 저장된 시간보다 느리면 필요가 없음
                    //빠른경우는 큐의 특성상 최단시간으로 축적되기때문에 고려하지 않아도 됨
                    //첫방문은 일단 큐에 넣자
                    //그리고 경과된 시간이 동일하다면 그 역시 최단시간이므로 큐에 삽입
                    if(arr[temp] == 0 || arr[temp] == arr[t]+1){
                        arr[temp] = arr[t]+1;
                        q.add(temp);
                    }
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        arr = new int[100001];
        if(S>=D){
            System.out.print(S-D + "\n" + 1);
        }else{
            bfs(S,D);
            System.out.print(time + "\n" + cnt);
        }
    }
}
