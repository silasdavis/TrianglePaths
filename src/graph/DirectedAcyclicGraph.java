package graph;

import graph.exception.ArrayNotTriangularException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirectedAcyclicGraph extends Graph {
	private Graph initialVertices;
	private Graph terminalVertices;
	
	public DirectedAcyclicGraph(List<Vertex> vertices) {
		super(vertices);
	}
	
	public static DirectedAcyclicGraph createGraphFromTriangle(int[][] triangle) throws ArrayNotTriangularException {
		if (!isTriangleShaped(triangle)){
			throw new ArrayNotTriangularException();
		}

		// Populate the bottom row of vertices
		List<Vertex> children = new ArrayList<Vertex>();
		for (int i=0; i < triangle[triangle.length - 1].length; i++){
			children.add(new Vertex(triangle[triangle.length - 1][i]));
		}
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(children);
		graph.setInitialVertices(children);
		
		for (int childRow=triangle.length - 1; childRow > 0; childRow--){
			List<Vertex> parents = getParentsAndAttachToChildren(children, triangle[childRow - 1]);
			graph.addAll(parents);
			children = parents;
		}
		graph.setTerminalVertices(children);
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

	public void setInitialVertices(Collection<Vertex> initialVertices) {
		this.initialVertices = (Graph) initialVertices;
	}
	
	public Graph getInitialVertices() {
		return initialVertices;
	}
	public void setTerminalVertices(Collection<Vertex>  terminalVertices) {
		this.terminalVertices = (Graph) terminalVertices;
	}
	public Graph getTerminalVertices() {
		return terminalVertices;
	}
	
}
