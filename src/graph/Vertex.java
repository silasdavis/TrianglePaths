package graph;
import java.util.ArrayList;

public class Vertex {

	private int weight;
	private Vertex predecessor;
	private ArrayList<Vertex> neighbours;
	
	public Vertex(){
		this.setWeight(weight);
		this.setPredecessor(null);
		neighbours = new ArrayList<Vertex>();
	}

	public Vertex(int weight) {
		this.setWeight(weight);
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setNeighbours(ArrayList<Vertex> neighbours) {
		this.neighbours = neighbours;
	}

	public ArrayList<Vertex> getNeighbours() {
		return neighbours;
	}
}
