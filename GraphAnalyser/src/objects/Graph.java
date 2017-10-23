package objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Graph {

	private Map<Integer, Node> allNodes;

	public Graph(Map<Integer, Node> allNodes2) {
		super();
		this.allNodes = allNodes2;
	}

	public Map<Integer, Node> getAllNodes() {
		return allNodes;
	}

	public void setAllNodes(HashMap<Integer, Node> allNodes) {
		this.allNodes = allNodes;
	}

	@Override
	public String toString() {
		String r = "";
		Collection<Node> c = allNodes.values();
		for (Iterator<Node> iterator = c.iterator(); iterator.hasNext();) {
			Node n = iterator.next();
			r += n.getId() + " | ";
			for (int i = 0; i < n.getEdgesToNode().size(); i++) {
				r += n.getEdgesToNode().get(i).getId() + ", ";
			}
			r += "\n";
		}
		
		return r;
	}
	
	
}
