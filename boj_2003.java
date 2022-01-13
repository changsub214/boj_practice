import java.util.*;
import java.io.*;

public class boj_2003 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt(); //partial sum
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = in.nextInt();
        }
        //pointer
        int start =0;
        int end =0;

        int count = 0;
        int partial_sum = arr[0];
        while(end<N){
            if(partial_sum<M){
                end++;
                if(end<N){
                    partial_sum += arr[end];
                }
            }else if(partial_sum>M){
                partial_sum -= arr[start];
                start++;
            }else if(partial_sum == M){
                count++;
                partial_sum -= arr[start];
                start++;
                end++;
                if(end<N){
                    partial_sum += arr[end];
                }
            }
        }
        System.out.print(count);
    }
}
