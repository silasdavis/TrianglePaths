package graph.algorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import graph.*;
import graph.exception.GraphDoesNotContainSpecifiedVerticesException;

public class DijkstraWithDestination {
	private Graph graph;
	private Graph destination;
	private Vertex minimallyCloseVertex;
	private int minimalPathLength;
	
	public DijkstraWithDestination(Graph graph, Vertex source, Graph destination) throws GraphDoesNotContainSpecifiedVerticesException{
		Graph subGraph = new Graph();
		subGraph.addAll(destination);
		subGraph.add(source);
		if (!graph.containsAll(subGraph)){
			throw new GraphDoesNotContainSpecifiedVerticesException(graph, subGraph);
		}
		this.graph = graph;
		this.destination = destination;
		this.minimallyCloseVertex = null;
		this.minimalPathLength = (int) Float.POSITIVE_INFINITY;
		for (Vertex vertex : graph){
			vertex.setColour(Colour.White);
			vertex.setDistance((int) Float.POSITIVE_INFINITY);
		}
		source.setDistance(source.getWeight());
		this.seekMinimalPath();
	}
	
	public LinkedList<Vertex> getMinimalPath(){
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex vertex = minimallyCloseVertex;
		do  {
			path.offerFirst(vertex);
			vertex = minimallyCloseVertex.getPredecessor();
		} while(vertex.getPredecessor() != null);
		
		return path;
	}
	
	public int getMinimalPathLength(){
		return this.minimalPathLength;
	}
	
	private void seekMinimalPath(){
		List<Vertex> unvisited = new ArrayList<Vertex>(this.graph.getVertices(Colour.White));
		Comparator<Vertex> distanceComparator = new VertexSortByDistance();
		Collections.sort(unvisited, distanceComparator);
		while (unvisited.size() > 0 && unvisited.get(0).getDistance() < this.minimalPathLength){
			visit(unvisited.get(0), unvisited);
			unvisited = unvisited.subList(1, unvisited.size());
			Collections.sort(unvisited, distanceComparator);
		}
	}
	
	// Visit neighbours of vertex filtered by unvisited list
	private void visit(Vertex vertex, List<Vertex> unvisited){
		List<Vertex> neighbours = new ArrayList<Vertex>(vertex.getNeighbours());
		neighbours.retainAll(unvisited);
		for (Vertex neighbour : neighbours){
			int distanceThroughVertex = vertex.getDistance() + neighbour.getWeight();
			if (distanceThroughVertex < neighbour.getDistance()){
				neighbour.setDistance(distanceThroughVertex);
			}
		}
		// improve upon the current minimally close vertex
		if (this.destination.contains(unvisited) &&
				(this.minimallyCloseVertex == null ^ this.minimalPathLength > vertex.getDistance())){
			this.minimallyCloseVertex = vertex;
			this.minimalPathLength = vertex.getDistance();
		}
		// Blacken vertex to mark it as visited
		vertex.setColour(Colour.Black);
	}
}
