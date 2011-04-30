package trianglepaths;

import graph.DirectedAcyclicGraph;
import graph.Graph;
import graph.algorithm.DijkstraWithDestination;
import graph.exception.ArrayNotTriangularException;
import graph.exception.GraphDoesNotContainSpecifiedVerticesException;

public class MinTrianglePath {

	/**
	 * @param args
	 * @throws ArrayNotTriangularException 
	 * @throws GraphDoesNotContainSpecifiedVerticesException 
	 */
	public static void main(String[] args) throws ArrayNotTriangularException, GraphDoesNotContainSpecifiedVerticesException {
		int[][] triangle = {
				{14},
				{25,63},
				{44,56,46},
				{73,28,49,10},
				{73,84,95,12,43},
				{78,81,91,50,56,76},
				{70,88,29,50,10,10,11},
				{71,82,93,10,45,67,78,90},
				{12,12,18,14,22,33,44,22,10},
				{92,83,45,10,21,31,40,10,66,22}
				};
		DirectedAcyclicGraph graph = DirectedAcyclicGraph.createGraphFromTriangle(triangle);
		DijkstraWithDestination dj = new DijkstraWithDestination(graph, graph.getInitialVertices().choose(), graph.getTerminalVertices());
		System.out.println(dj.getMinimalPathString());

	}

}
