package graph;
import java.util.*;

public class Graph extends HashSet<Vertex> {
	public Graph() {
		
	}
	
	public static Graph createGraphFromTriangle(){
		return null;
	}
	
	private static Graph connectVertices(Vertex root, int[][] triangle, int index){
		HashSet<Vertex> children = new HashSet<Vertex>();
		int row = getRowFromIndex(index);
		int column = index - getLastIndexOfRow(row - 1) - 1;
		children.add(new Vertex(triangle[row + 1][column]));
	}
	
	
	private static int getRowFromIndex(int index){
		return (int) Math.floor(Math.sqrt(2*index + 0.25) - 0.5) - 1;
	}
	
	private static int getLastIndexOfRow(int row){
		return (row + 2)*(row + 1)/2;
	}
	}
}
