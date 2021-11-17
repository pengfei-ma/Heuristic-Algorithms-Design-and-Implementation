// Demonstrates how to read graph info from a text file
// And, create adjacency matrix and adjacency list

import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleGraphRepresentation {

	// map a node (a character) to an index in the nodeArray
	public static int nodeToIndex(char[] nodeArray, char node) {
		
		int index = 0;
		for (int i=0; i<nodeArray.length; i++) {
			if (nodeArray[i] == node) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static void main(String[] args) throws IOException {
		
		// input file has small weighted graph
		Scanner fileInput = new Scanner (new File("small_graph.txt"));

		// read the top row in the input file and create nodeArray
		String[] aRow;
		aRow = fileInput.nextLine().trim().split("\\s+");
		int numNodes = aRow.length;
		System.out.println("Numbee of nodes = " + numNodes);
		char[] nodeArray = new char[numNodes];
		HashMap<Character, Integer> nodeHash = new HashMap<>();
		
		int j = 0;
		for (j=0; j<aRow.length; j++) {
			nodeArray[j] = aRow[j].charAt(0); // store nodes in array
			nodeHash.put(aRow[j].charAt(0), j); // store <node, index> pairs in hash map
		}
		
		// map a node (a character) to an index using nodeArray 
		System.out.println("\nNode to index mapping, using array\n");
		for (char c: nodeArray) {
			System.out.println(c + " -> " + nodeToIndex(nodeArray, c));
		}
		
		// map a node (a character) to an index using nodeHash
		System.out.println("\nNode to index mapping, using hash map\n");
		for (char c: nodeArray) {
			System.out.println(c + " -> " + nodeHash.get(c));
		}
	
		// read subsequent lines in the input file and create adjacency matrix
		int[][] adjMatrix = new int[numNodes][numNodes];
		
		int i = 0;
		while (fileInput.hasNext()){
			aRow = fileInput.nextLine().split("\\s+");
			for (j=1; j<aRow.length; j++) {
				int e = Integer.parseInt(aRow[j]);
				adjMatrix[i][j-1] = e;
			}
			i++;
		}
		
		// print adjacency matrix
		System.out.println("\nPrint adjacency matrix\n");
		for (i=0; i<numNodes; i++) {
			for (j=0; j<numNodes; j++) {
				System.out.printf("%3d", adjMatrix[i][j]);
			}
			System.out.println();
		}
		
		// create adjacency list from adjacency matrix
		// array of ArrayList is used
		ArrayList<Character>[] adjList = new ArrayList[numNodes];
		for (i=0; i<numNodes; i++) {
			ArrayList<Character> aList = new ArrayList<>();
			for (j=0; j<numNodes; j++) {
				if (adjMatrix[i][j] != 0) {
					aList.add(nodeArray[j]);
				}
			}
			adjList[i] = aList;
		}
		
		// print adjacency list
		System.out.println("\nPrint adjacency list\n");
		for (i=0; i<numNodes; i++) {
			ArrayList<Character> aList = adjList[i];
			Character[] nodeList = new Character[aList.size()];
			System.out.print(nodeArray[i] + ": ");
			for (j=0; j<aList.size(); j++) {
				System.out.print(aList.get(j));
				if (j < aList.size()-1) System.out.print(" -> ");
			}
			System.out.println();
		}		
		fileInput.close();
	}
}
