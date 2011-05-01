package trianglepaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		Integer[][] triangle = {
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<ArrayList<Integer>> triangleIn = new ArrayList<ArrayList<Integer>>();
		try {
			while (reader.ready()){
				ArrayList<Integer> row = new ArrayList<Integer>();
				for (String s : reader.readLine().split(" ")){
					row.add(Integer.parseInt(s));
				}
				triangleIn.add(row);
			}
			graph = DirectedAcyclicGraph.createGraphFromTriangle(triangleIn);
			dj = new DijkstraWithDestination(graph, graph.getInitialVertices().choose(), graph.getTerminalVertices());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
