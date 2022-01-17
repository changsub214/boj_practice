import java.util.*;
import java.io.*;

public class boj_1719 { //it can be also solved from using floyd warsh algorithm
    //this source from the other people.
    //I need to re-study this question...!
    private static int n,m;
    private final static int INF = (int)1e9;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        //경로표로 정리
        //정점(집하장)
        //간선(이동가능)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b,c));
            graph.get(b).add(new Node(a,c));

        }

        for (int i=1; i<=n; i++) {
            dijkstra(i);
        }

        System.out.println(sb);

    }

    private static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        boolean[] visit = new boolean[n+1];
        int[] path = new int[n+1];//노드 추적
        //정점 i 직전에 정점 j를 거쳐야 한다.(path[i]=j)
        int[] d = new int[n+1];

        Arrays.fill(d, INF);

        d[start] = 0;
        path[start] = start;

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            int dist = node.distance;
            int now = node.index;

            if (visit[now]) {
                //재방문시 패스
                continue;
            }
            visit[now] = true;

            for (int i=0; i<graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getDistance();

                if (cost < d[graph.get(now).get(i).getIndex()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    path[graph.get(now).get(i).getIndex()] = now;
                    pq.add(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }

        }//while

        findPath(start, path);

    }//dijkstra

    private static void findPath(int start, int[] path) {

        for (int i=1; i<=n; i++) {
            if (i == start) {
                sb.append("- ");
                continue;
            }
            int answer = 0;
            for (int j=i; j!=start; j=path[j]) {
                answer = j;
            }
            sb.append(answer + " ");
        }
        sb.append("\n");

    }//findPath

    static class Node implements Comparable<Node> {
        int index, distance;
        Node (int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        private int getIndex() {
            return this.index;
        }

        private int getDistance() {
            return this.distance;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance < o.distance) {
                return -1;
            }
            return 1;
        }
    }
   /* static class Node implements Comparable<Node>{
        int v;
        int cost;
        public Node(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static int V,E;
   // static int[] dis;
//    static int[] path; // save route
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void dijkstra(int start){
        boolean[] visit = new boolean[V+1];
        int[] path = new int[V+1]; // save route
        int[] dis = new int[V+1];
        Arrays.fill(dis,Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0)); //start
        visit[start] = true; //visited
        dis[start] = 0; //start-point cost
        path[start] = start; //start-point
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int vertex = node.v;
            int cost = node.cost;
        //    System.out.println(vertex + " " + cost + " " + visit[vertex]);
            if(visit[vertex]){
                continue;
            }
            visit[vertex] = true;
        //    System.out.println(list.get(vertex).size());
            for(int j=0; j<list.get(vertex).size(); j++){
                int c = list.get(vertex).get(j).cost + dis[vertex];
                if(dis[list.get(vertex).get(j).v] > c){
                    dis[list.get(vertex).get(j).v] = c;
                    path[list.get(vertex).get(j).v] = vertex;
                    pq.add(new Node(list.get(vertex).get(j).v,cost));
                }
            }
        }
        for(int i=1; i<path.length; i++){
            System.out.print(path[i] + " ");
        }
        System.out.println("");
        looking(path,start);
    }
    public static void looking(int[] path, int start){
        for(int i=1; i<=V; i++){
            if(i == start){
                sb.append("- ");
                continue;
            }
            int ans = 0;
            for(int j=1; j!=start; j=path[j]) { //backtracking
                ans = j;
            }
            sb.append(ans + " ");
            }
        sb.append("\n");


        for (int i=1; i<=n; i++) {
            if (i == start) {
                sb.append("- ");
                continue;
            }
            int answer = 0;
            for (int j=i; j!=start; j=path[j]) {
                answer = j;
            }
            sb.append(answer + " ");
        }
        sb.append("\n");
    }
    public static void main(String[] args)throws IOException{
        Scanner in = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // StringTokenizer st;
        //st = new StringTokenizer(br.readLine());
        V = in.nextInt();//Integer.parseInt(st.nextToken());
        E = in.nextInt();//Integer.parseInt(st.nextToken());
        for(int i=0; i<V+1; i++){
            list.add(new ArrayList<>());
        }
        for(int i=0; i<E; i++){
            //String[] temp = br.readLine().split(" ");
            int s = in.nextInt();
            int d = in.nextInt();
            int w = in.nextInt();
            list.get(s).add(new Node(d,w));
            list.get(d).add(new Node(s,w));
            System.out.println(s + " " + d + " " + w);
        }
        for(int i=1; i<V+1; i++){
            dijkstra(i);
        }
        System.out.print(sb);
    }*/
}
