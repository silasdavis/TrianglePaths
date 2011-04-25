package graph;

import java.util.Comparator;

public class VertexSortByDistance implements Comparator<Vertex>{

	@Override
	public int compare(Vertex v1, Vertex v2) {
		return v1.getDistance() - v2.getDistance();
	}

}
