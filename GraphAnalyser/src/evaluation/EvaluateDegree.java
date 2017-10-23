package evaluation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import objects.Graph;
import objects.Node;

public class EvaluateDegree {

	private Map<Integer, Integer> result = new HashMap<Integer, Integer>();
	
	public EvaluateDegree(Graph g){
		Collection<Node> c = g.getAllNodes().values();
		for (Iterator<Node> iterator = c.iterator(); iterator.hasNext();) {
			Node n = iterator.next();
			result.put(n.getId(), n.getEdgesToNode().size());
		}
	}
	
	public Map<Integer, Integer> getResult(){
		return result;
	}
	
	@Override
	public String toString() {
		String r = "Degree: \n";
		Collection<Integer> c = result.keySet();
		for (Iterator<Integer> iterator = c.iterator(); iterator.hasNext();) {
			int n = iterator.next();
			r += n + " | " + result.get(n) + "\n";
		}
		
		return r;
	}
	
}
