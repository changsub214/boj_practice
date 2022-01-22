import java.util.*;
import java.io.*;

public class boj_10844 {
    static int n;
    static long[][] arr;
    public static long dfs(int digit, int num){
        if(digit == 1){
            return arr[digit][num];
        }
        if(arr[digit][num] == 0){
            if(num == 0 ){
                arr[digit][num] = dfs(digit-1,1);
            }else if(num == 9){
                arr[digit][num] = dfs(digit-1,8);
            }else{
                arr[digit][num] = dfs(digit-1,num-1) + dfs(digit-1, num+1);
            }
        }
        return arr[digit][num] % 1000000000;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        final long MODULAR = 1000000000;
        long res = 0;
        n = in.nextInt();
        arr = new long[n+1][10];
        for(int i=0; i<10; i++){
            arr[1][i] = 1;
        }
        for(int i=1; i<10; i++){
            res += dfs(n,i);
        }
        System.out.print(res % MODULAR);

    }
}
