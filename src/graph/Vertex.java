package graph;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Vertex {

	private int weight;
	private int distance;
	private Colour colour;
	private Vertex predecessor;
	private Graph neighbours;
	
	public Vertex(){
		this.setPredecessor(null);
		this.neighbours = new Graph();
	}

	public Vertex(int weight) {
		this();
		this.setWeight(weight);
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}
	
	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public Graph getNeighbours() {
		return neighbours;
	}

	public void addNeighbours(Collection<Vertex> children) {
		this.neighbours.addAll(children);
		
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}

	public Graph getNeighbours(Colour requiredColour) {
		return this.getNeighbours().getVertices(requiredColour);
	}
}
