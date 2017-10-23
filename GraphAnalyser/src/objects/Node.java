package objects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {

	private int id;
	// maybe its better to use a set instead of a list!
	private List<Node> edgesToNode;
	
	public Node(int id, List<Node> edgesToNode) {
		this.id = id;
		this.edgesToNode = edgesToNode;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Node> getEdgesToNode() {
		return edgesToNode;
	}
	public void setEdgesToNode(List<Node> edgesToNode) {
		this.edgesToNode = edgesToNode;
	}
	
	public boolean hasEdgeToNode(Node n){
		for (int i = 0; i < edgesToNode.size(); i++) {
			if(n.getId() == edgesToNode.get(i).getId()){
				return true;
			}
		}
		
		return false;
	}
	
	
	
}
