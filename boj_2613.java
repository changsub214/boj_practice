import java.util.*;
import java.io.*;

public class boj_2613 {
    static int N, M;
    static int[] arr;
    static ArrayList<Integer> group;
    public static int parametric_search(int left, int right){
        int res = 0;
        while(left <= right){
            int mid = (left + right) >> 1;
            int cnt = 0, sum = 0;
            boolean flag = true;
            for(int i=0; i<N; i++){
                if(sum + arr[i] <= mid){
                    sum += arr[i];
                }else{
                    if(++cnt == M){
                        flag = false;
                        break;
                    }
                    sum = arr[i];
                }
            }
            if(cnt < M){
                flag = true;
            }

            if(flag){
                res = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return res;
    }
    public static void group_make(int res){
        int cnt = 0, sum = 0;
        for(int i=0; i<N; i++){
            sum += arr[i];
            cnt++;
            if(sum > res){
                i--;
                group.add(cnt-1);
                sum = 0;
                cnt = 0;
            }else if(sum == res){
                group.add(cnt);
                sum = 0;
                cnt = 0;
            }
            if(i == N - 1 && cnt > 0){
                group.add(cnt);
            }
        }
    }
    public static void group_division(){
        while(group.size() < M){
            for(int i=0; i<group.size(); i++){
                if(group.get(i)>1){
                    group.set(i, group.get(i)-1);
                    group.add(i, 1);
                    break;
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        group = new ArrayList<>();
        int left = 0 ;
        int right = 100*300;
        int i = 0;
        int loop = N;
        st = new StringTokenizer(br.readLine());
        while(loop-- > 0){
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left,arr[i]);
            i++;
        }
        int res = parametric_search(left, right);
        System.out.println(res);
        group_make(res);
        group_division();
        for(int k=0; k<group.size(); k++){
            System.out.print(group.get(k) + " ");
        }
    }
}
