import java.util.*;
import java.io.*;

public class boj_1477 {
    static int N, M, L;
    static int[] arr;
    public static int parametric_sort(){
        int left = 1;
        int right = L;
        while(left<=right){
            int mid = (left+right)/2;
            int cnt = 0;

            for(int i=1; i<N+2; i++){
                cnt += (arr[i] - arr[i-1]-1) / mid;
                //놓을 수 있는 휴게소 갯수
            }

            if(cnt > M){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N+2];
        st = new StringTokenizer(br.readLine());
        arr[0] = 0;
        arr[N+1] = L;
        for(int i=1; i<N+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        System.out.print(parametric_sort());

    }
}
