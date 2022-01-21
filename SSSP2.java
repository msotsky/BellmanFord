/*
*   Maxime Sotsky
*   COMP 2631
*   Dr. Keliher
*/
import java.util.*;

public class SSSP2{

    private int nNodes;
    private int nEdges;
    private int[][] edges;
    
    final int MAX_NODES = 1000;
    final int MAX_EDGES = 1000000;
    final int INFINITY = Integer.MAX_VALUE;

    public SSSP2(int nNodes, int nEdges, int[][] edges) throws IllegalArgumentException{

        if(nNodes < 0 || nNodes > MAX_NODES)
            throw new IllegalArgumentException("Number of nodes must be postive and no greater than 1,000");
        if(nEdges < 0 | nEdges > MAX_EDGES)
            throw new IllegalArgumentException("Number of edhes must be positive and no greater than 1,000,000");
        if( (checkEdgeList(nNodes,nEdges,edges)) == false )
            throw new IllegalArgumentException("Invalid EdgeList");
        this.nNodes = nNodes;
        this.nEdges = nEdges;
        this.edges = edges;
    }
    //======================================
    //Accessor
    public int getNumNodes(){
        return this.nNodes;
    }
    //Accessor
    public int getNumEdges(){
        return this.nEdges;
    }
    //======================================

    private boolean checkEdgeList(int nNodes, int nEdges, int[][] edges){
        //The edge list arg is null or has length (first dim) != 3

        if(edges == null || edges.length != nEdges){
            System.out.println("The edge list arg is null or has length (first dim) != 3");
            return false;
        }

        //second dim arr is null or has len != 3
        for(int i = 0; i < edges.length; i++){
            if(edges[i].length != 3 || edges[i] == null){
                System.out.println("second dim arr is null or has len != 3");
                return false;
            }
        }
        //node index stored in edge list ! between 0 and (n-1) inclusive (n = nNodes)
        for(int i = 0; i < edges.length; i++){
            for(int j = 0; j < 2; j++){
                if(edges[i][j] < 0 || edges[i][j] > nNodes){
                    System.out.println("node index stored in edge list ! between 0 and (n-1) inclusive (n = nNodes)");
                    return false;
                }
            }
        }
        //edge weights ! in [-2000,2000] (use constants)
        int w = 2; //weight index
        for(int i = 0; i < edges.length; i++){
            if(edges[i][w] < -2000 || edges[i][w] > 2000){
                System.out.println("edge weights ! in [-2000,2000] (use constants)");
                return false;
            }  
        }
        //else true
       return true;
    }

    private int[] infinity(int[] dist){
        for(int i = 0; i < dist.length;i++){
            dist[i] = INFINITY;
        }
        return dist;
    }

    public int[] bellmanFord(int sN){

        if(sN < 0 || sN > nNodes)
            throw new IllegalArgumentException("start node out of bounds");
            
        int[] dist = new int[nNodes];
        dist = infinity(dist);
        dist[sN] = 0;
        int[] distClone = dist.clone(); //for graphs like in27.txt
        
        for(int i = 0; i < nNodes-1; i++){  //n-1 iter
            for(int j = 0; j < nEdges; j++){ //for each edges -> relax
                if( dist[edges[j][0]] + edges[j][2] < dist[edges[j][1]] && dist[edges[j][0]] != INFINITY){
                    dist[edges[j][1]] = dist[edges[j][0]] + edges[j][2];
                }
            }
        }
           
        for(int i = 0; i < nEdges; i++){
            if(Arrays.equals(dist, distClone))
                return null;
            if( dist[edges[i][0]] + edges[i][2] < dist[edges[i][1]] && dist[edges[i][0]] != INFINITY)
                    return null; // cycle detected
        }
        return dist;
    }


}