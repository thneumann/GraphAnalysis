package main;

import evaluation.EvaluateBetweenness;
import evaluation.EvaluateCloseness;
import evaluation.EvaluateDegree;
import evaluation.EvaluateGlobalClusteringCoefficient;
import objects.Graph;
import readGraph.ReadFromFile;

public class Main {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(args[i]);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Graph g = ReadFromFile.readFile(args[i]);
			System.out.println(g);
			EvaluateDegree ed = new EvaluateDegree(g);
			System.out.println(ed);
			EvaluateCloseness ec = new EvaluateCloseness(g);
			System.err.println(ec);
			EvaluateBetweenness eb = new EvaluateBetweenness(g);
			System.out.println(eb);
			EvaluateGlobalClusteringCoefficient egcc = new EvaluateGlobalClusteringCoefficient(
					g);
			System.out.println(egcc);
		}
	}
}
