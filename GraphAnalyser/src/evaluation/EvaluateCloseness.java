package evaluation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import objects.Graph;
import objects.Node;

public class EvaluateCloseness {

	Map<Integer, Double> result = new HashMap<Integer, Double>();
	
	public EvaluateCloseness(Graph g){
		Map<Integer,Node> allNodes = g.getAllNodes();
		Collection<Node> c = allNodes.values();
		for (Iterator<Node> iterator = c.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			// now try to visit all nodes in copy withe node as start node
			Queue<Node> currentDistanceQueue = new LinkedList<Node>();
			for (int i = 0; i < node.getEdgesToNode().size(); i++) {
				currentDistanceQueue.add(node.getEdgesToNode().get(i));
			}
			int distance = 1;
			Map<Integer, Integer> distancesForThisNode = new HashMap<Integer, Integer>();
			distancesForThisNode.put(node.getId(), 0);
			
			while(!currentDistanceQueue.isEmpty()){
				Queue<Node> nextDistanceQueue = new LinkedList<Node>();
				while(!currentDistanceQueue.isEmpty()){
					Node n = currentDistanceQueue.poll();
					if(!distancesForThisNode.containsKey(n.getId())){
						distancesForThisNode.put(n.getId(), distance);
					}
					for (int i = 0; i < n.getEdgesToNode().size(); i++) {
						if(!distancesForThisNode.containsKey(n.getEdgesToNode().get(i).getId())){
							nextDistanceQueue.add(n.getEdgesToNode().get(i));
						}
						//remove from next queue, thus this node does not appear again for the next distance
						if(nextDistanceQueue.contains(n)){
							nextDistanceQueue.remove(n);
						}
					}
				}
				currentDistanceQueue = nextDistanceQueue;
				distance++;
			}
			
			int distanceSum = 0;
			for (Iterator<Integer> iterator2 = distancesForThisNode.values().iterator(); iterator2
					.hasNext();) {
				distanceSum += iterator2.next();
			}
			
			result.put(node.getId(), 1./distanceSum);
		
		}
	}
	
	
	public Map<Integer, Double> getResult(){
		return result;
	}
	
	@Override
	public String toString() {
		String r = "Closeness: \n";
		Collection<Integer> c = result.keySet();
		double sum = 0;
		for (Iterator<Integer> iterator = c.iterator(); iterator.hasNext();) {
			int n = iterator.next();
			r += n + " | 1/" + Math.round(1/result.get(n)) + "\n";
			sum += result.get(n);
		}
		r += "\n";
		r += "global closeness: " + sum/c.size() + "\n";
		
		return r;
	}
	
	
}
