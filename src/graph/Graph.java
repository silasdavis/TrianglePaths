package graph;
import graph.exception.ArrayNotTriangularException;

import java.util.*;

public class Graph extends HashSet<Vertex> {

	public Graph() {
		super();
	}
	
	public Graph(Collection<Vertex> vertices) {
		this();
		this.addAll(vertices);
	}
	
	// Axiom of choice
	public Vertex choose(){
		for (Vertex vertex : this){
			return vertex;
		}
		return null;
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
