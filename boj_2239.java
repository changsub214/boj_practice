import java.util.*;
import java.io.*;

public class boj_2239 {
    static int[][] arr;
    public static void sudoku(int row, int col){
        if(col==9){
            sudoku(row+1,0);
            return;
        }
        if(row==9){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println("");
            }
            System.exit(0);
        }
        if(arr[row][col] == 0){
            for(int i=1; i<10; i++){
                if(possible(row,col,i)){
                    arr[row][col] = i;
                    sudoku(row,col+1);
                }
            }
            arr[row][col] = 0;
            return;
        }
        sudoku(row,col+1);
    }
    public static boolean possible(int row, int col, int value){
        for(int i=0; i<9; i++){
            if(arr[row][i] == value)return false;
            if(arr[i][col] == value)return false;
        }
        for(int i=(row/3)*3; i<(row/3)*3+3; i++){
            for(int j=(col/3)*3; j<(col/3)*3+3; j++){
                if(arr[i][j] == value)return false;
            }
        }
        return true;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new int[9][9];
        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            String[] temp = st.nextToken().split("");
            for(int j=0; j<9; j++){
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        sudoku(0,0);
    }
}
