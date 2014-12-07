/**
 * Paths.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 *
 * Purpose:	Find the minimum distance from u to v for every pair of
 *		vertices.
 */

import java.util.Scanner;

public class Paths {
	
	// Constants
	
	public static final int MAX_VERTICES = 1000;

	// Attributes
	
	private int numVertices;
	private int numEdges;
	private int[][] G;
	private int[][][] S;
	
	// Constructor
	
	/**
	 * Constructor for creating an object used for completing the Paths
	 * problem algorithm, The object stores important information needed
	 * to solve the problem, like the graph and the solution array.
	 * 
	 * @param numVertices	The number of vertices in the graph.
	 * @param numEdgeshe	The number of edges in the graph.
	 * @param graph		The array representing the graph.
	 */
	public Paths( int numVertices, int numEdges, int[][] graph ) {
		this.numVertices = numVertices;
		this.numEdges = numEdges;
		this.G = graph;
		this.S = new int[numVertices+1][numVertices+1][numVertices+1];
		for( int i = 0 ; i <= numVertices ; i++ ) {
			for( int j = 0 ; j <= numVertices ; j++ ) {
				for( int k = 0 ; k <= numVertices ; k++ ) {
					S[i][j][k] = Integer.MAX_VALUE;
				}//end for k
			}//end for j
		}//end for i
	}//end Paths constructor
	
	// Methods
	
	/**
	 * The main algorithm for solving the Paths problem,  Uses the
	 * Floyd-Warshall algorithm for finding all the shortest paths for
	 * each pair.
	 */
	public void findPaths() {
		for( int i = 1 ; i <= numVertices ; i++ ) {
			for( int j = 1 ; j <= numVertices ; j++ ) {
				S[i][j][0] = G[i][j];
			}//end for j
		}//end for i
		for( int k = 1 ; k <= numVertices ; k++ ) {
			for( int i = 1 ; i <= numVertices ; i++ ) {
				for( int j = 1 ; j <= numVertices ; j++ ) {
					int second;
					if( S[i][k][k-1] == Integer.MAX_VALUE ||
					S[k][j][k-1] == Integer.MAX_VALUE ) {
						second = Integer.MAX_VALUE;
					}//end if
					else{
						second = S[i][k][k-1] +
							S[k][j][k-1];
					}//end else
					S[i][j][k] = Math.min( S[i][j][k-1],
						second );
				}//end for j
			}//end for i
		}//end for k
	}//end findPaths
	
	/**
	 * Method for displaying the results of the algorithm to the user.
	 */
	public void printResult() {
		int numPath = 1;
		String minPathInf = "inf";
		for( int i = 1 ; i <= numVertices ; i++ ) {
			for( int j = 1 ; j <= numVertices ; j++ ) {
				if( S[i][j][numVertices] ==
					Integer.MAX_VALUE ) {
					System.out.printf( "%s/%d ",
						minPathInf, 0 );
				}//end if
				else if( S[i][j][numVertices] == 0 ) {
					System.out.printf( "%d/%d ", 0, 1 );
				}//end else if
				else {
					System.out.printf( "%d/%d ",
						S[i][j][numVertices], numPath );
				}//end else
			}//end for j
			System.out.println();
		}//end for i
	}//end printResult
	
	/**
	 * The main logic for the program, Reads input from the user and then
	 * feeds that information into the algorithm, and then displays the
	 * results.
	 * 
	 * @param args	Command line arguments, unused.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in );
		String input;
		input = sc.next();
		int numVertices = Integer.parseInt( input );
		input = sc.next();
		int numEdges = Integer.parseInt( input );
		int start, end, weight;
		int[][] graph = new int[MAX_VERTICES+1][MAX_VERTICES+1];
		for( int i = 0 ; i <= numVertices ; i++ ) {
			for( int j = 0 ; j <= numVertices ; j++ ) {
				graph[i][j] = Integer.MAX_VALUE;
			}//end for j
		}//end for i
		for( int i = 0 ; i < numEdges ; i++ ) {
			input = sc.next();
			start = Integer.parseInt( input );
			input = sc.next();
			end = Integer.parseInt( input );
			input = sc.next();
			weight = Integer.parseInt( input );
			graph[start][end] = weight;
		}//end for i
		sc.close();
		
		for( int i = 1 ; i <= numVertices ; i++ ) {
			graph[i][i] = 0;
		}//end for i
		
		Paths P = new Paths( numVertices, numEdges, graph );
		P.findPaths();
		P.printResult();
	}//end main
}//end Paths class
