import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Course: EECS 114 Fall 2015
 *
 * First Name: Stefan
 * Last Name: Cao
 * Lab Section: 1A
 * email address: stefanc1@uci.edu
 *
 *
 * Assignment: lab8
 * Filename : Graph.java
 *
 * I hereby certify that the contents of this file represent
 * my own original individual work. Nowhere herein is there 
 * code from any outside resources such as another individual,
 * a website, or publishings unless specifically designated as
 * permissible by the instructor or TA.
 */ 

public class Graph {

	private class Vertex{
		
		//List of neighbors
		private List<Vertex> neighborList; 
		private int distance;
		private String label;
		
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Vertex(String label){
			this.label = label;
			neighborList = new ArrayList();
			
		}
		
	}	// end of Vertex class
	

	//private data member, indexed container to hold the vertices in the graph
	private Vertex[] vertexArray;
	
	private int numOfNodes;
	private int numOfEdges;
	
	private Vertex sourceVertex;
	
	private static final int INFINITY = Integer.MAX_VALUE;

	public Vertex getSourceVertex(){
		return sourceVertex;
	}

	public Graph(String filename){
		
		build_graph(filename);
				
	}
	
	
	// method - invoked from a class constructor, reads the graph input file and instantiates the graph object
	public void build_graph(String filename){
		File file = new File(filename);

		try{
			@SuppressWarnings("resource")
			Scanner input = new Scanner(file).useDelimiter("\n");
			
			// reading the first and second line to initialize the array and vertices
			for(int i = 0; i < 2; i++){
				
				// first line - number of nodes/vertices
				if(i == 0){
					numOfNodes = input.nextInt();
					vertexArray = new Vertex[numOfNodes];
				}
				
				// second line - number of edges
				else{
					numOfEdges = input.nextInt();
				}	
			}
			
			// reading and setting the node labels
			for(int i = 0; i < numOfNodes; i++){
				vertexArray[i] = new Vertex(input.next());
			}
			
			// setting the source vertex
			sourceVertex = vertexArray[0];
			
			// reading the pairs and set its neighbors
			for(int i = 0; i < numOfEdges; i++){
				String tempString = input.next();
				String[] stringSplitted = tempString.split(" "); 
			
				//trying to find the vertex 
				int lcIndex = 0;
				Vertex tempVertex1 = null;
				Vertex tempVertex2 = null;
				
				boolean foundVertex1 = false;
				boolean foundVertex2 = false;
				
				while(lcIndex < numOfNodes + 1){
				
					// if found both vertices
					if(foundVertex1 && foundVertex2){
						tempVertex1.neighborList.add(tempVertex2);
						break;
					}
					
					// if found the right vertex
					if(vertexArray[lcIndex].label.equals(stringSplitted[0])){
						tempVertex1 = vertexArray[lcIndex];
						foundVertex1 = true;
					}
					
					// if found the left vertex
					if(vertexArray[lcIndex].label.equals(stringSplitted[1])){
						tempVertex2 = vertexArray[lcIndex];
						foundVertex2 = true;
					}

					lcIndex++;
				}
				
			}

			input.close();
			
		} catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	// method - prints out the graph's adjacency list 
	public void display(){

		for(int i = 0; i < vertexArray.length; i++){
			System.out.print(vertexArray[i].label + " -> ");
			
			// check if the node has NO neighbors
			if(vertexArray[i].neighborList.isEmpty()){
				System.out.print("empty");
			}
			
			else{
				for(int j = 0; j < vertexArray[i].neighborList.size(); j++){
					System.out.print(vertexArray[i].neighborList.get(j).label + ", ");
				}
			}
			
			System.out.println("");
		}
	}
	
	//method - prints out the graph's vertices with each vertex's distance from the source vertex
	public void display_levels(){
		
		// for every node n in graph, initialize the distance to be infinity
		for(int n = 0; n < vertexArray.length; n++){
			if(vertexArray[n].distance != INFINITY){
				System.out.print(vertexArray[n].label + "'s distance from source vertex: ");
				System.out.print(vertexArray[n].distance);
			}
			
			else{
				System.out.print(vertexArray[n].label + " cannot be reached from source vertex");
			}
			
			System.out.println("");

		}	
	}
	
	
	// method - via BFS traversal, this method should assign each individuals vertex's distance to be equal to the number of hops tha the vertex is from a source vertex
	public void set_levels(Vertex source_vertex){
		
		// for every node n in graph, initialize the distance to be infinity
		for(int n = 0; n < vertexArray.length; n++){
			vertexArray[n].distance = INFINITY;	

		}
		
		// creating an empty queue
		Queue<Vertex> Q = new LinkedList<Vertex>();
		
		source_vertex.distance = 0;

		Q.add(source_vertex);
		
		while(!Q.isEmpty()){
			Vertex u = Q.remove();

			for(int v = 0; v < u.neighborList.size(); v++){
				if(u.neighborList.get(v).distance == INFINITY){		
					u.neighborList.get(v).distance = u.distance + 1;
					Q.add(u.neighborList.get(v));
				}
			}
		}
	}
	
	
	// method - via a BFS traversal, checks if the entire graph can be visited starting at the source vertex
	public boolean is_connected(Vertex source_vertex){
		
		// call set levels to set the distance
		set_levels(source_vertex);
		
		for(int i = 0; i < vertexArray.length; i++){
			
			// if one vertex's distance is equal to infinity that means that it cannot be visited starting at source vertex
			if(vertexArray[i].distance == INFINITY){
				return false;
			}
		}
		
		return true;
	}

}	// end of Graph class
