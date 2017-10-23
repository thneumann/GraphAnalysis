package evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import objects.Graph;
import objects.Node;

public class EvaluateBetweenness {


	Map<Integer, Map<Integer,List<List<Integer>>>> result = new HashMap<Integer, Map<Integer,List<List<Integer>>>>();
	Map<Integer,Double> betweennessResult = new HashMap<Integer, Double>();
	Graph g;
	
	public EvaluateBetweenness(Graph g){
		this.g = g;
		getNumberOfShortestPaths(g);
		Map<Integer,Node> allNodes = g.getAllNodes();
		Collection<Node> c = allNodes.values();
		for (Iterator<Node> iterator = c.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			double sum = 0;
			for (int i = 1; i <= c.size(); i++) {
				if(node.getId() == i){
					continue;
				}
				for (int j = i+1; j <= c.size(); j++) {
					if(node.getId() == j){
						continue;
					}
					List<List<Integer>> shortestPathsFromIToJ = result.get(i).get(j);
					int numberOfShortestPaths = shortestPathsFromIToJ.size();
					int ShortestPathsOverNode = 0;
					for (int k = 0; k < shortestPathsFromIToJ.size(); k++) {
						if(shortestPathsFromIToJ.get(k).contains(node.getId())){
							ShortestPathsOverNode++;
						}
					}
					sum += ((ShortestPathsOverNode+0.0)/numberOfShortestPaths);
				}
			}
			betweennessResult.put(node.getId(),sum);
	
		}
			
	}
	
	private void getNumberOfShortestPaths(Graph g){
		Map<Integer,Node> allNodes = g.getAllNodes();
		Collection<Node> c = allNodes.values();
		for (Iterator<Node> iterator = c.iterator(); iterator.hasNext();) {
			Map<Integer, List<List<Integer>>> shortestPathsForCurrentNode =  new HashMap<Integer, List<List<Integer>>>();
			Node node = iterator.next();
			for (int i = 1; i <= c.size(); i++) {
				if(node.getId() == i){
					shortestPathsForCurrentNode.put(i, new ArrayList<List<Integer>>());
				}else{
					List<Integer> visited = new ArrayList<Integer>();
					shortestPathsForCurrentNode.put(i, getShortestPaths(node, i, new ArrayList<Integer>(), visited));
				}
			}
			result.put(node.getId(), shortestPathsForCurrentNode);
		}
	}
	
	private List<List<Integer>> getShortestPaths(Node fromNode, int toNode, List<Integer> path, List<Integer> visited){
		if(fromNode.getId() == toNode){
			int lastPathNode = path.remove(path.size()-1);
			List<List<Integer>> paths = new ArrayList<List<Integer>>();
			paths.add(new ArrayList<Integer>(path));
			path.add(lastPathNode);
			return paths;
		}
		
		visited.add(fromNode.getId());
		
		List<List<Integer>> tmpResults = new ArrayList<List<Integer>>();
		int currentMinDistance = Integer.MAX_VALUE;
		for (int i = 0; i < fromNode.getEdgesToNode().size(); i++) {
			if(visited.contains(fromNode.getEdgesToNode().get(i).getId())){
				continue;
			}
			path.add(fromNode.getEdgesToNode().get(i).getId());
			List<List<Integer>> latestResults = getShortestPaths(g.getAllNodes().get(fromNode.getEdgesToNode().get(i).getId()), toNode, path, visited);
			path.remove(path.indexOf(fromNode.getEdgesToNode().get(i).getId()));
			for (int j = 0; j < latestResults.size(); j++) {
				if(latestResults.get(j).size() == currentMinDistance){
					tmpResults.add(latestResults.get(j));
				}else if(latestResults.get(j).size() < currentMinDistance){
					tmpResults = new ArrayList<List<Integer>>();
					tmpResults.add(latestResults.get(j));
					currentMinDistance = latestResults.get(j).size();
				}
				//else skip this bad result
			}
		}
		
		visited.remove(visited.indexOf(fromNode.getId()));
		
		return tmpResults;
	}
	
	
	@Override
	public String toString() {
		
		// show shortest paths
		
		String r = "Shortest Paths: \n";
//		Collection<Integer> c = result.keySet();
//		for (Iterator<Integer> iterator = c.iterator(); iterator.hasNext();) {
//			int fromNode = iterator.next();
//			Map<Integer,List<List<Integer>>> distancesForThisNode = result.get(fromNode);
//			Collection<Integer> c2 = distancesForThisNode.keySet();
//			for (Iterator<Integer> iterator2 = c2.iterator(); iterator2.hasNext();) {
//				int toNode = (Integer) iterator2.next();
//				List<List<Integer>> numberOfPathsAndDistance = distancesForThisNode.get(toNode);
//				r += fromNode + " --> " + toNode + " | numberOfPaths: " + numberOfPathsAndDistance.size() + "\n";
//				for (int i = 0; i < numberOfPathsAndDistance.size(); i++) {
//					r += fromNode + " -> ";
//					for (int j = 0; j < numberOfPathsAndDistance.get(i).size(); j++) {
//						r += numberOfPathsAndDistance.get(i).get(j) + " -> ";
//					}
//					r += toNode + "\n";
//				}
//			}
//			r += "-------------------------------------------------\n";
//		}
//		
//		r+= "\n\n";
		
		// show betweeness result
		r += "Betweenness: \n";
		Collection<Integer> cb = betweennessResult.keySet();
		
		double sum = 0;
		
		for (Iterator<Integer> iterator = cb.iterator(); iterator.hasNext();) {
			Integer node =  iterator.next();
			double value = betweennessResult.get(node);
			r += node + " | " + value + "\n";
			sum += value;
		}
		
		r += "\n";
		r += "global betweenness: " + sum/cb.size() + "\n";
		
		return r;
	}
	
}
