package graph;
import graph.exception.ArrayNotTriangularException;

import java.util.*;

public class Graph extends HashSet<Vertex> {

	public Graph() {
		super();
	}
	
	public Graph(Collection<Vertex> leaves) {
		this();
		this.addAll(leaves);
	}

	public static Graph createGraphFromTriangle(int[][] triangle) throws ArrayNotTriangularException {
		if (!isTriangleShaped(triangle)){
			throw new ArrayNotTriangularException();
		}

		// Populate the bottom row of vertices
		List<Vertex> children = new ArrayList<Vertex>();
		for (int i=0; i < triangle[triangle.length - 1].length; i++){
			children.add(new Vertex(triangle[triangle.length - 1][i]));
		}
		Graph graph = new Graph(children);
		
		for (int childRow=triangle.length - 1; childRow > 0; childRow--){
			
			List<Vertex> parents = getParentsAndAttachToChildren(children, triangle[childRow - 1]);
			graph.addAll(parents);
			children = parents;
		}
		return graph;
	}
	
	private static boolean isTriangleShaped(int[][] triangle){
		for (int row=0; row < triangle.length; row++){
			if (triangle[row].length != row + 1){
				return false;
			}
		}
		return true;
	}
	
	// Working from the bottom row of leaves means we can create the vertices and the edges in a single pass
	private static List<Vertex> getParentsAndAttachToChildren(List<Vertex> children, int[] parentWeights){
		List<Vertex> parentRow = new ArrayList<Vertex>();
		// Each adjacent pair of children neighbours one unique parent, which we can create and attach to its children
		for (int i=0; i < children.size() - 1; i++ ){
			Vertex parent = new Vertex(parentWeights[i]);
			parent.addNeighbours(children.subList(i, i+2));
			parentRow.add(parent);
		}
		return parentRow;	
	}
	
	// Use triangle numbers to move between row/column indices and a single left-to-right index
	// Note the triangle index is naturally 1-based
	private static int getRowFromIndex(int index){
		return (int) Math.ceil(Math.sqrt(2*(index + 1) + 0.25) - 0.5) - 1;
	}
	
	private static int getLastIndexOfRow(int row){
		return (row + 2)*(row + 1)/2 - 1;
	}

	public Graph getVertices(Colour requiredColour) {
		Graph colouredVertices = new Graph();
		for (Vertex vertex : this){
			if (vertex.getColour() == requiredColour){
				colouredVertices.add(vertex);
			}
		}
		return colouredVertices;
	}
}
