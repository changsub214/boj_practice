import java.util.*;
import java.io.*;

public class boj_1644 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arr = new int[N+1];
        boolean[] arr2 = new boolean[N+1];
        arr2[0] = true;
        arr2[1] = true;
        for(int i=2; i<N+1; i++){
            for(int j=2*i; j<N+1; j+=i){
                arr2[j] = true;
            }
        }
        int i = 2;
        int j = 0;
        while(i<N+1){
            if(!arr2[i]){
                arr[j] = i;
                j++;
            }
            i++;
        }
        int start = 0;
        int end = 0;
        int cnt = 0;
        int partial_sum = arr[0];
        while(start < j){
            if(partial_sum < N){
                end++;
                if(end>j)break;
                partial_sum += arr[end];
            }else if(partial_sum == N){
                cnt++;
                partial_sum -= arr[start];
                start++;
                end++;
                if(end>j)break;
                partial_sum += arr[end];
            }else if(partial_sum > N){
                partial_sum -= arr[start];
                start++;
            }
        }
        System.out.print(cnt);
    }
}
