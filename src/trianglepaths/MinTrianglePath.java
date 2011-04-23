package trianglepaths;

import graph.Graph;
import graph.exception.ArrayNotTriangularException;

public class MinTrianglePath {

	/**
	 * @param args
	 * @throws ArrayNotTriangularException 
	 */
	public static void main(String[] args) throws ArrayNotTriangularException {
		int[][] triangle = {{1},{2,3},{4,5,6},{7,8,9,10}};
		Graph graph = Graph.createGraphFromTriangle(triangle);
		System.out.println("oo");

	}

}
