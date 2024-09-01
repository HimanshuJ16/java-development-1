import java.util.*;

public class JavaBasics54 {
  static class Edge {
    int src;
    int dest;
    int wt;

    public Edge(int s, int d, int w) {
      this.src = s;
      this.dest = d;
      this.wt = w;
    }
  }

  static void createGraph(ArrayList<Edge> graph[]) {
    for(int i=0; i<graph.length; i++) {
      graph[i] = new ArrayList<>();
    }

    graph[0].add(new Edge(0, 1, 2));
    graph[0].add(new Edge(0, 2, 4));

    graph[1].add(new Edge(1, 3, 7));
    graph[1].add(new Edge(1, 2, 1));

    graph[2].add(new Edge(2, 4, 3));

    graph[3].add(new Edge(3, 5, 1));
 
    graph[4].add(new Edge(4, 3, 2));
    graph[4].add(new Edge(4, 5, 5));
  }

  static void createGraph1(ArrayList<Edge> graph[]) {
    for(int i=0; i<graph.length; i++) {
      graph[i] = new ArrayList<>();
    }

    graph[0].add(new Edge(0, 1, 2));
    graph[0].add(new Edge(0, 2, 4));

    graph[1].add(new Edge(1, 2, -4));

    graph[2].add(new Edge(2, 3, 2));

    graph[3].add(new Edge(3, 4, 4));
 
    graph[4].add(new Edge(4, 1, -1));
  }

  static void createGraph2(ArrayList<Edge> graph) {
    graph.add(new Edge(0, 1, 2));
    graph.add(new Edge(0, 2, 4));

    graph.add(new Edge(1, 2, -4));

    graph.add(new Edge(2, 3, 2));

    graph.add(new Edge(3, 4, 4));
 
    graph.add(new Edge(4, 1, -1));
  }

  static void createGraph3(ArrayList<Edge> graph[]) {
    for(int i=0; i<graph.length; i++) {
      graph[i] = new ArrayList<>();
    }

    graph[0].add(new Edge(0, 1, 10));
    graph[0].add(new Edge(0, 2, 15));
    graph[0].add(new Edge(0, 3, 30));

    graph[1].add(new Edge(1, 0, 10));
    graph[1].add(new Edge(1, 3, 40));

    graph[2].add(new Edge(2, 0, 15));
    graph[2].add(new Edge(2, 3, 50));

    graph[3].add(new Edge(3, 1, 40));
    graph[3].add(new Edge(3, 2, 50));
  }

  static class Pair implements Comparable<Pair> {
    int n;
    int path;

    public Pair(int n, int path) {
      this.n = n;
      this.path = path;
    }

    @Override
    public int compareTo(Pair p2) {
      return this.path - p2.path;
    }
  }

  public static void dijkstra(ArrayList<Edge> graph[], int src) {
    int dist[] = new int[graph.length];
    for(int i=0; i<graph.length; i++) {
      if (i != src) {
        dist[i] = Integer.MAX_VALUE;
      }
    }

    boolean vis[] = new boolean[graph.length];
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(new Pair(src, 0));

    while (!pq.isEmpty()) {
      Pair curr = pq.remove();
      if (!vis[curr.n]) {
        vis[curr.n] = true;
        for(int i=0; i<graph[curr.n].size(); i++) {
          Edge e = graph[curr.n].get(i);
          int u = e.src;
          int v = e.dest;
          int wt = e.wt;

          if (dist[u] + wt < dist[v]) {
            dist[v] = dist[u] + wt;
            pq.add(new Pair(v, dist[v]));
          }
        }
      }
    }

    for(int i=0; i<dist.length; i++) {
      System.out.print(dist[i] + " ");
    }
    System.out.println();
  }

  static void addEdge(ArrayList<Integer> adj[], int u, int v) {
    adj[u].add(v);
    adj[v].add(u);
  }
  
  static boolean isCyclicConntected(ArrayList<Integer> adj[], int s,int V, boolean visited[]) {
    int parent[] = new int[V];
    Arrays.fill(parent, -1);
    
    Queue<Integer> q = new LinkedList<>();
    visited[s] = true;
    q.add(s);
    
    while (!q.isEmpty()) {
      int u = q.poll();
      for (int i=0; i<adj[u].size(); i++) {
        int v = adj[u].get(i);
        if (!visited[v]) {
          visited[v] = true;
          q.add(v);
          parent[v] = u;
        } else if (parent[u] != v) {
          return true;
        }
      }
    }
    
    return false;
  }
  
  static boolean isCyclicDisconntected(ArrayList<Integer> adj[], int V) {
    boolean visited[] = new boolean[V];
    Arrays.fill(visited, false);
    
    for (int i=0; i<V; i++) {
      if (!visited[i] && isCyclicConntected(adj, i, V, visited)) {
        return true;
      }
    }  
    
    return false;
  }

  static class Node {
    int data;
    Node left, right;
  }
  
  static class qItem {
    Node node;
    int depth;
    
    public qItem(Node node, int depth) {
      this.node = node;
      this.depth = depth;
    }
  }
  
  static int minDepth(Node root) {
    if (root == null) {
      return 0;
    }
    
    Queue<qItem> q = new LinkedList<>();
    qItem qi = new qItem(root, 1);
    q.add(qi);
    
    while (q.isEmpty() == false) {
      qi = q.peek();
      q.remove();
      Node node = qi.node;
      int depth = qi.depth;

      if (node.left == null && node.right == null) {
        return depth;
      }
      
      if (node.left != null) {
        qi.node = node.left;
        qi.depth = depth + 1;
        q.add(qi);
      }
      
      if (node.right != null) {
        qi.node = node.right;
        qi.depth = depth + 1;
        q.add(qi);
      }
    }
    
    return 0;
  }
  
  static Node newNode(int data) {
    Node temp = new Node();
    temp.data = data;
    temp.left = temp.right = null;
    return temp;
  }

  public final static int R = 3;
  public final static int C = 5;
  
  static class Ele{
    int x = 0;
    int y = 0;
    
    Ele(int x,int y) {
      this.x = x;
      this.y = y;
    }
  }
  
  static boolean isValid(int i, int j) {
    return (i >= 0 && j >= 0 && i < R && j < C);
  }
  
  static boolean isDelim(Ele temp) {
    return (temp.x == -1 && temp.y == -1);
  }
  
  static boolean checkAll(int arr[][]) {
    for (int i=0; i<R; i++) {
      for (int j=0; j<C; j++) {
        if (arr[i][j] == 1) {
          return true;
        }
      }
    }
    
    return false;
  }
  
  static int Solution(int arr[][]) {
    Queue<Ele> Q = new LinkedList<>();
    Ele temp;
    int ans = 0;
    
    for (int i=0; i<R; i++) {
      for (int j=0; j<C; j++) {
        if (arr[i][j] == 2) {
          Q.add(new Ele(i,j));
        }
      }
    }
    
    Q.add(new Ele(-1,-1));
    
    while (!Q.isEmpty()) {
      boolean flag = false;
      while (!isDelim(Q.peek())) {
        temp = Q.peek();
        if (isValid(temp.x+1, temp.y) && arr[temp.x+1][temp.y] == 1) {
          if(!flag){
            ans++;
            flag = true;
          }
          
          arr[temp.x+1][temp.y] = 2;
          temp.x++;
          Q.add(new Ele(temp.x,temp.y));
          temp.x--;
        }
        
        if (isValid(temp.x-1, temp.y) && arr[temp.x-1][temp.y] == 1) {
          if (!flag) {
            ans++;
            flag = true;
          }
          
          arr[temp.x-1][temp.y] = 2;
          temp.x--;
          Q.add(new Ele(temp.x,temp.y));
          temp.x++;
        }
        
        if (isValid(temp.x, temp.y+1) && arr[temp.x][temp.y+1] == 1) {
          if(!flag) {
            ans++;
            flag = true;
          }
          
          arr[temp.x][temp.y+1] = 2;
          temp.y++;
          Q.add(new Ele(temp.x,temp.y));
          temp.y--;
        }
        
        if (isValid(temp.x, temp.y-1) && arr[temp.x][temp.y-1] == 1) {
          if (!flag) {
            ans++;
            flag = true;
          }
          
          arr[temp.x][temp.y-1] = 2;
          temp.y--;
          Q.add(new Ele(temp.x,temp.y));
        }
        
        Q.remove();
      }
      
      Q.remove();
      
      if (!Q.isEmpty()) {
        Q.add(new Ele(-1,-1));
      }
    }
    
    return (checkAll(arr))? -1: ans;
  }

  static int ROW, COL, count;
  static boolean isSafe(int[][] M, int row, int col, boolean[][] visited) {
    return ((row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !visited[row][col]));
  }
  
  static void DFS(int[][] M, int row, int col, boolean[][] visited) {
    int[] rowNbr = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] colNbr = { -1, 0, 1, -1, 1, -1, 0, 1 };
    visited[row][col] = true;
    
    for (int k=0; k<8; k++) {
      if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited)) {
        count++;
        DFS(M, row + rowNbr[k], col + colNbr[k], visited);
      }
    }
  }
  
  static int largestRegion(int[][] M, int row, int col) {
    boolean[][] visited = new boolean[ROW][COL];
    int result = 0;
    
    for (int i=0; i<ROW; i++) {
      for (int j=0; j<COL; j++) {
        if (M[i][j] == 1 && !visited[i][j]) {
          count = 1;
          DFS(M, i, j, visited);
          result = Math.max(result, count);
        }
      }
    }
    
    return result;
  }

  static int shortestChainLen(String start, String target, Set<String> D) {
    if(start == target) {
      return 0;
    }
    
    if (!D.contains(target)) {
      return 0;
    }
    
    int level = 0, wordlength = start.length();
    Queue<String> Q = new LinkedList<>();
    Q.add(start);
    
    while (!Q.isEmpty()) {
      ++level;
      int sizeofQ = Q.size();
      for (int i=0; i<sizeofQ; ++i) {
        char []word = Q.peek().toCharArray();
        Q.remove();
        for (int pos=0; pos<wordlength; ++pos) {
          char orig_char = word[pos];
          for (char c='a'; c<='z'; ++c) {
            word[pos] = c;
            if (String.valueOf(word).equals(target)) {
              return level + 1;
            }
            if (!D.contains(String.valueOf(word))) {
              continue;
            }
            D.remove(String.valueOf(word));
            Q.add(String.valueOf(word));
          }
          
          word[pos] = orig_char;
        }
      }
    }
    
    return 0;
  }

  public static void bellmanFord(ArrayList<Edge> graph, int src, int V) {
    int dist[] = new int[V];
    for(int i=0; i<dist.length; i++) {
      if (i != src) {
        dist[i] = Integer.MAX_VALUE;
      }
    }

    for(int i=0; i<V-1; i++) {
      for(int j=0; j<graph.size(); j++) {
        Edge e = graph.get(j);
        int u = e.src;
        int v = e.dest;
        int wt = e.wt;
        if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
          dist[v] = dist[u] + wt;
        }
      }
    }

    for(int i=0; i<dist.length; i++) {
      System.out.print(dist[i] + " ");
    }
    System.out.println();
  }
  
  static class Pair1 implements Comparable<Pair1> {
    int v;
    int cost;

    public Pair1(int v, int c) {
      this.v = v;
      this.cost = c;
    }

    @Override
    public int compareTo(Pair1 p2) {
      return this.cost - p2.cost;
    }
  }

  public static void prims(ArrayList<Edge> graph[]) { // O(ElogE)
    boolean vis[] = new boolean[graph.length];
    PriorityQueue<Pair1> pq = new PriorityQueue<>();
    pq.add(new Pair1(0, 0));
    int finalCost = 0;

    while (!pq.isEmpty()) {
      Pair1 curr = pq.remove();
      if (!vis[curr.v]) {
        vis[curr.v] = true;
        finalCost += curr.cost;

        for(int i=0; i<graph[curr.v].size(); i++) {
          Edge e = graph[curr.v].get(i);
          pq.add(new Pair1(e.dest, e.wt));
        }
      }
    }

    System.out.println("final(min) cost of MST : " + finalCost);
  }
  public static void main(String[] args) {
    // // CODE1
    // int V = 6;
    // int src = 0;
    // ArrayList<Edge> graph[] = new ArrayList[V];
    // createGraph(graph);
    // dijkstra(graph, src);

    // // CODE2
    // int V = 4;
    // @SuppressWarnings("unchecked")
    // ArrayList<Integer> adj[] = new ArrayList[V];
    // for(int i=0; i<4; i++) {
    //   adj[i] = new ArrayList<Integer>();
    // }
    // addEdge(adj, 0, 1);
    // addEdge(adj, 1, 2);
    // addEdge(adj, 2, 0);
    // addEdge(adj, 2, 3);
    // if (isCyclicDisconntected(adj, V)) {
    //   System.out.println("Yes");
    // } else {
    //   System.out.println("No");
    // }

    // // CODE3
    // Node root = newNode(1);
    // root.left = newNode(2);
    // root.right = newNode(3);
    // root.left.left = newNode(4);
    // root.left.right = newNode(5);
    // System.out.println(minDepth(root));

    // // CODE4
    // int arr[][] = { 
    //   {2, 1, 0, 2, 1},
    //   {1, 0, 1, 2, 1},
    //   {1, 0, 0, 2, 1}
    // };
    // int ans = Solution(arr);
    // if (ans == -1) {
    //   System.out.println("All oranges cannot rot");
    // } else {
    //   System.out.println("Time required for all oranges to rot = " + ans);
    // }

    // // CODE5
    // int M[][] = { 
    //   { 0, 0, 1, 1, 0 },
    //   { 1, 0, 1, 1, 0 },
    //   { 0, 1, 0, 0, 0 },
    //   { 0, 0, 0, 0, 1 } 
    // };
    // int ROW = 4, COL = 5;
    // System.out.println(largestRegion(M, ROW, COL));

    // // CODE6
    // Set<String> D = new HashSet<String>();
    // D.add("poon");
    // D.add("plee");
    // D.add("same");
    // D.add("poie");
    // D.add("plie");
    // D.add("poin");
    // D.add("plea");
    // String start = "toon";
    // String target = "plea";
    // System.out.print("Length of shortest chain is: "+ shortestChainLen(start, target, D));

    // // CODE7
    // int V = 5;
    // // ArrayList<Edge> graph[] = new ArrayList[V];
    // ArrayList<Edge> graph = new ArrayList<>();
    // createGraph2(graph);
    // bellmanFord(graph, 0, V);

    // // CODE8
    int V = 4;
    ArrayList<Edge> graph[] = new ArrayList[V];
    createGraph3(graph);
    prims(graph);
  }
}
