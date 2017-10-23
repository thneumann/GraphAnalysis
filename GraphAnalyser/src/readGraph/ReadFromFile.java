package readGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import objects.Graph;
import objects.Node;

public class ReadFromFile {

	public static Graph readFile(String filename){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			// first line reads the number of nodes
			int numberOfNodes = Integer.parseInt(br.readLine());
			
			Map<Integer, Node> allNodes = new HashMap<Integer, Node>();
			for (int i = 0; i < numberOfNodes; i++) {
				allNodes.put((i+1), new Node((i+1), new ArrayList<Node>()));
			}
			
			while(br.ready()){
				String line = br.readLine();
				// first number (node) has an edge to the second number (node), numbers are seperated by a space
				String [] split = line.split(" ");
				int firstNode = Integer.parseInt(split[0]);
				int secondNode = Integer.parseInt(split[1]);
				Node n = allNodes.get(secondNode);
				allNodes.get(firstNode).getEdgesToNode().add(n);
				
			}
			
			return new Graph(allNodes);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
