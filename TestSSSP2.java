/*
*   Maxime Sotsky
*   COMP 2631
*   Dr. Keliher
*/
import java.util.*;
import java.io.*;

public class TestSSSP2 {

    public static void main(String[] args) throws FileNotFoundException{

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter full file path: ");
        String input = sc.nextLine();
        sc.close();
        File file = new File(input);
        sc = new Scanner(file);
        int n = sc.nextInt();   //  n = numNodes(1 <= n <= 1000)
        int m = sc.nextInt();   //  m = numEdges (directed) (0 <= m <= 1000000)
        int q = sc.nextInt();   //  q = numQueries(1 <= q <= n)
        int s = sc.nextInt();   //  s = indexOfStartNode(0 <= s <= (n-1))

        int[][] edges = new int[m][3];

        for(int i = 0; i < edges.length; i++){
            for(int j = 0; j < edges[i].length; j++){
                edges[i][j] = sc.nextInt();
            }
        }

        int[] queries = new int[q];
        for(int i = 0; i < queries.length; i++){
            queries[i] = sc.nextInt();
        }
        sc.close();
        //----------------------------------
        SSSP2 graph = new SSSP2(n, m, edges);

        int[] dist = graph.bellmanFord(s);

        if(dist == null){
            System.out.println("There are negative cycles.");
        }
        if(dist != null){
            for(int query : queries){
                if(dist[query] == Integer.MAX_VALUE)
                    System.out.println("Unreachable");
                else
                    System.out.println(dist[query]);
            }
        }
    }
}