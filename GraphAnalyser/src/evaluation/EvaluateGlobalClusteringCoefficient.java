package evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import objects.Graph;
import objects.Node;

public class EvaluateGlobalClusteringCoefficient {

	private List<Integer> triples = new ArrayList<Integer>();
	private List<Integer> triangles = new ArrayList<Integer>();
	private double result;
	
	public EvaluateGlobalClusteringCoefficient(Graph g){
		
		Map<Integer,Node> allNodes = g.getAllNodes();
		Collection<Node> c = allNodes.values();
		for (Iterator<Node> iterator = c.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			
			for (int i = 0; i < node.getEdgesToNode().size(); i++) {
				for (int j = 0; j < node.getEdgesToNode().get(i).getEdgesToNode().size(); j++) {
					if(node.getEdgesToNode().get(i).getEdgesToNode().get(j) == node){
						continue;
					}
					// add triple
					addTriple(node.getId(), node.getEdgesToNode().get(i).getId(), node.getEdgesToNode().get(i).getEdgesToNode().get(j).getId());
					// check for triangle
					if(node.getEdgesToNode().get(i).getEdgesToNode().get(j).getEdgesToNode().contains(node)){
						addTriangle(node.getId(), node.getEdgesToNode().get(i).getId(), node.getEdgesToNode().get(i).getEdgesToNode().get(j).getId());
					}
				}
			}
		}
		
		result = (3*triangles.size()+0.0)/triples.size();
	}
	
	private void addTriple(int a, int b, int c){
		int triple = formatThreeIntegersToOne(a, b, c);
		if(!triples.contains(triple)){
			triples.add(triple);
		}
	}
	
	private void addTriangle(int a, int b, int c){
		int triangle = formatThreeIntegersToOne(a, b, c);
		if(!triangles.contains(triangle)){
			triangles.add(triangle);
		}
	}
	
	private int formatThreeIntegersToOne(int a, int b, int c){
		if(a < b && b < c){
			return Integer.parseInt(a+""+b+""+c);
		}else if(a < b && a < c && b > c){
			return Integer.parseInt(a+""+c+""+b);
		}else if(b < a && a < c){
			return Integer.parseInt(b+""+a+""+c);
		}else if(b < a && b < c && a > c){
			return Integer.parseInt(b+""+c+""+a);
		}else if(c < a && a < b){
			return Integer.parseInt(c+""+a+""+b);
		}else {
			return Integer.parseInt(c+""+b+""+a);
		}
	}
	
	@Override
	public String toString() {

String ret = "All Triples: \n";
		
		for (int i = 0; i < triples.size(); i++) {
			ret += triples.get(i) + ", ";
		}
		ret += "\n";
		
ret += "All Triangles: \n";
		
		for (int i = 0; i < triangles.size(); i++) {
			ret += triangles.get(i) + ", ";
		}
		ret += "\n";
		
		ret += "Global Clustering Coefficient: ";
		ret += result;
		
		
		
		return ret;
		
	}
	
}
