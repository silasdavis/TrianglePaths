package graph.exception;

import graph.Graph;

public class GraphDoesNotContainSpecifiedVerticesException extends Exception {

	private Graph graph;
	private Graph subGraph;
	
	public GraphDoesNotContainSpecifiedVerticesException(Graph graph, Graph subGraph) {
		super();
		this.graph = graph;
		this.subGraph = subGraph;
	}
	
	public Graph getGraph() {
		return graph;
	}

	public Graph getSubGraph() {
		return subGraph;
	}
}
