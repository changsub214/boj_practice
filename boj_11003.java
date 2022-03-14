import java.util.*;
import java.io.*;

public class boj_11003 {
    static class Node{
        int value;
        int index;
        public Node(int value, int index){
            this.value = value;
            this.index = index;
        }
    }
    static Deque<Node> deque = new ArrayDeque<>();
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(st.nextToken());
        int cnt_size = 0;
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<num; i++){
            int temp = Integer.parseInt(st.nextToken());
            while(!deque.isEmpty() && deque.peekFirst().index <= i-len){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && deque.peekLast().value > temp){
                deque.pollLast();
            }
            deque.addLast(new Node(temp, i));
            sb.append(deque.peekFirst().value).append(" ");
        }

        System.out.print(sb);
    }
}
