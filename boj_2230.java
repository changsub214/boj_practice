import java.util.*;
import java.io.*;

public class boj_2230 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int[] arr = new int[N+1];
        for(int i=1; i<arr.length; i++){
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        int start = 1;
        int end = 1;
        int min = Integer.MAX_VALUE;
        int temp;
        while(start<=N){
            if(arr[end]<0 && arr[start]<0){
                temp = Math.abs(arr[start])-Math.abs(arr[end]);
            }else{
                temp = arr[end]-arr[start];
            }
            if(temp<M){
                end++;
                if(end>N)break;
            }else{
                if(temp==M){
                    min = M;
                    break;
                }
                min = Math.min(min,temp);
                start++;
            }
        }
        System.out.print(min);
    }
}
