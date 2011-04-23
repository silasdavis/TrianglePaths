package graph;
import graph.exception.ArrayNotTriangularException;

import java.util.*;

public class Graph extends HashSet<Vertex> {

	public Graph() {
		
	}
	
	public static Graph createGraphFromTriangle(int[][] triangle) throws ArrayNotTriangularException {
		if (!isTriangleShaped(triangle)){
			throw new ArrayNotTriangularException();
		}
		
		return connectVertices(new Vertex(triangle[0][0]), triangle, 0);
	}
	
	private static boolean isTriangleShaped(int[][] triangle){
		for (int row=0; row < triangle.length; row++){
			if (triangle[row].length != row + 1){
				return false;
			}
		}
		return true;
	}
	
	private static Graph connectVertices(Vertex root, int[][] triangle, int index){
		Graph union = new Graph();
		if (index > getLastIndexOfRow(triangle.length) - 1) {
			return union;
		}
		int row = getRowFromIndex(index);
		int column = index - getLastIndexOfRow(row - 1) - 1;
		// Add children
		union.add(new Vertex(triangle[row + 1][column]));
		union.add(new Vertex(triangle[row + 1][column + 1]));
		
		union.addAll(connectVertices(root, triangle, index + 1));
		return union;
	}
	
	// Use triangle numbers to move between row/column indices and a single left-to-right index
	// Note the triangle index is naturally 1-based
	private static int getRowFromIndex(int index){
		return (int) Math.ceil(Math.sqrt(2*(index + 1) + 0.25) - 0.5) - 1;
	}
	
	private static int getLastIndexOfRow(int row){
		return (row + 2)*(row + 1)/2 - 1;
	}
}
