package hw6;
import java.io.*;
import java.util.*;
import hw4.Graph;
import hw4.GraphWrapper;
import hw5.MarvelParser;
import hw4.edge;
import javafx.util.Pair;
/**
 * Overview:
 * The MarvelPaths2 is a weighted graph that store marvel character as node and edge as connection between the two character.
 * Abstract function:
 * The graph<String, Double> is used to store the data where each character is a string and the weight as edge label.
 * The weight is the inverse of number of books they both showed up. 
 * No duplicate edge exists between two nodes.
 * 
 * Representation Invariant:
	weightedgraph !=  null
	all edges have positive weight between 0 and 1
	all nodes are not null

 *
 */
public class MarvelPaths2 {
	private Graph<String,Double> weightedgraph;
	/**
	 * @effect construct a empty weighted graph;
	 * 
	 */
	public MarvelPaths2() {
		
		this.weightedgraph = new Graph<String,Double>();
	}
	
	/**
	 * 
	 * @param filename
	 * @effect take in the file and store the character in the graph with weighted edge between them. 
	 * @throws RuntimeException if the file cannot be opened.
	 * NullPointerException if the filename is null. 
	 */
	public void createNewGraph(String filename) {
		if(filename==null) {
			throw new NullPointerException("Filename cannot be null");
		}
		this.weightedgraph = new Graph<String,Double>();
		Set<String> chars = new HashSet<String>();
		Map<String, Set<String>> CIB = new HashMap<String, Set<String> >();
		try {
			MarvelParser.readData(filename, CIB, chars);	
		}
		catch(IOException e){
			throw new RuntimeException("Bad file");
		}
		Iterator<String> char_it = chars.iterator();
		Iterator<String> parent_it=chars.iterator();
		Iterator<String> child_it=chars.iterator();
		String parent;
		String child;
		String edge_prev;
		String child_prev;
		String child_pair;
		Double count =0.0;
		GraphWrapper graph = new GraphWrapper();
		while(char_it.hasNext()) {
			parent = char_it.next();
			graph.addNode(parent);
			this.weightedgraph.addNode(parent);
		}
		for(String chara: chars) {
			for(String book: CIB.keySet()) {
				if(CIB.get(book).contains(chara)) {
					for(String chara2:CIB.get(book)) {
						if(!chara.equals(chara2)) {
							graph.addEdge(chara, chara2, book);
						}
					}
				}
			}
		}
		parent_it = graph.listNodes();
		while(parent_it.hasNext()) {
			parent = parent_it.next();
			child_it = graph.listChildren(parent);
			count = 0.0;
			child_prev = null;
			edge_prev = null;
			child_pair = null;
			while(child_it.hasNext()) {
				child = child_it.next();
				child_pair = child.substring(0,child.indexOf("("));
				if(child_pair.equals(child_prev)) {
					if(!edge_prev.equals(child)) {
						count++;
					}
				}else {
					if(child_prev!=null) {
						this.weightedgraph.addEdge(parent, child_prev, 1/count);
					}
					child_prev = child_pair;
					count = 1.0;
				}
				edge_prev = child;
			}
			if(child_pair!=null) {
				this.weightedgraph.addEdge(parent, child_pair, 1/count);
			}
			
		}
		
	
	
		
	}
	public static  <T> boolean Dijkstra(ArrayList<T> Path, HashMap<T,Double> Dist,Graph<T,Double> graph,T Start,T End) {
		//Start of Dijkstra's algorithm
		//---------------------------------------------------------------------------------------
		PriorityQueue<Pair<T, Double> > Q = new PriorityQueue<Pair<T, Double>>(
				new Comparator<Pair<T, Double>>() {  

			public int compare(Pair<T, Double> p1, Pair<T, Double> p2) {
				if (p1.getValue() < p2.getValue())
                    return -1;
                else if (p1.getValue() > p2.getValue())
                    return 1;
                else
                    return 0;
			}      
        });
		Iterator<T> node_it = graph.listNodes();
		HashMap<T, Pair<T, Double>> Finished = new HashMap<T, Pair<T, Double> >();//Store the minimum edges
	
		T node;
		T child;
		Pair<T, Double> tempPair;
		
		while(node_it.hasNext()) // Setting distances to infinity initially
		{
			node = node_it.next();
			Dist.put(node, Double.POSITIVE_INFINITY);
			Finished.put(node, null);
		}
		Dist.put(Start, 0.00); // Adds the initial node with distance 0
		node_it = graph.listNodes();
		while(node_it.hasNext()) // Setting distances to infinity initially
		{
			node = node_it.next();
			tempPair = new Pair<T, Double>(node, Dist.get(node));
			Q.add(tempPair);	
		}
		
		
		edge<T,Double> temp_edge;
		T minDest;
		Double weight;
		
		while(!Q.isEmpty()) 
		{ 
			minDest = Q.remove().getKey();
			if(minDest.equals(End)) 
			{
				break;
			}
			Iterator<edge<T,Double>> edge_it = graph.listChildren(minDest);
			
			while(edge_it.hasNext()) 
			{ 		
				temp_edge = edge_it.next(); 
				child = temp_edge.getchild();
				weight = temp_edge.getlabel();		
				if(Dist.get(child) > (Dist.get(minDest) + weight))
				{
					Dist.put(child, Dist.get(minDest) + weight); 
					Finished.put(child, new Pair<T, Double>(minDest, Dist.get(minDest))); 
					Q.add(new Pair<T, Double>(child, Dist.get(child))); 
				}
			}
		}
		//End of Dijkstra's algorithm
	
		if(Dist.get(End) == Double.POSITIVE_INFINITY) 
		{
			return false;
		}
		T nxt = End;
		while(Finished.get(nxt)!= null) 
		{
			Path.add(nxt); 
			nxt = Finished.get(nxt).getKey(); 
		}
		Path.add(Start);
		return true;
	}
	/**
	 * 
	 * @param node1
	 * @param node2
	 * @return the minimum weight path from node1 to node2 
	 * if at least one node is not in the graph, output will indicate unknown character.
	 * The weighted path from the node to itself is defined as 0.
	 * The returned value has 3 significant digit
	 * @throws NullPointerException if input is null
	 */
	public String findPath(String node1,String node2) {
		if(node1==null ||node2==null) {
			throw new NullPointerException("Input cannot be null");
		}
		Iterator<String> node_it = this.weightedgraph.listNodes();
		String char_name;
		String output = "";
		boolean node1_exist = false;
		boolean node2_exist = false;
		while(node_it.hasNext()) {
			char_name = node_it.next();
			if(char_name.equals(node1)) {
				node1_exist = true;
			}
			if(char_name.equals(node2)) {
				node2_exist = true;
			}
		}
		if(!node1_exist&&!node2_exist) {
			if(node1.equals(node2)) {
				output = output.concat("unknown character " +node1+"\n");
			}
			else {
				output += "unknown character "+node1+"\n";
				output += "unknown character "+node2+"\n";
			}
			return output;
			
		}
		if(!node1_exist) {
			output += "unknown character "+node1+"\n";
			return output;
		}
		if(!node2_exist) {
			output += "unknown character "+node2+"\n";
			return output;
		}
		
		output ="path from "+ node1 + " to " +node2+":\n";
		if(node1.equals(node2)) {
			output+= String.format("total cost: %.3f\n", 0.00);
			return output;
		}
		ArrayList<String> Path = new ArrayList<String>();
		HashMap<String, Double> Dist = new HashMap<String, Double>();
		if(!Dijkstra(Path,Dist,this.weightedgraph,node1,node2)) 
		{
			output += "no path found\n";
			return output;
		}else {
			for(int i = Path.size()-1; i > 0; i--) 
			{ 
				output += String.format(Path.get(i).concat(" to ").concat(Path.get(i-1)).concat(" with weight %.3f\n"), Dist.get(Path.get(i-1))-Dist.get(Path.get(i))
						);
			}
			output += String.format("total cost: %.3f\n", Dist.get(node2));
			
			return output;
		}
		
		
		/**
		//Start of Dijkstra's algorithm
		//---------------------------------------------------------------------------------------
		PriorityQueue<Pair<String, Double> > Q = new PriorityQueue<Pair<String, Double>>(
				new Comparator<Pair<String, Double>>() {  

			public int compare(Pair<String, Double> p1, Pair<String, Double> p2) {
				if (p1.getValue() < p2.getValue())
                    return -1;
                else if (p1.getValue() > p2.getValue())
                    return 1;
                else
                    return 0;
			}      
        });
		
		HashMap<String, Double> Dist = new HashMap<String, Double>();//Store distance of every nodes
		HashMap<String, Pair<String, Double>> Finished = new HashMap<String, Pair<String, Double> >();//Store the minimum edges
		node_it = this.weightedgraph.listNodes(); // iterates through all the nodes
		String node;
		String child;
		Pair<String, Double> tempPair;
		
		while(node_it.hasNext()) // Setting distances to infinity initially
		{
			node = node_it.next();
			Dist.put(node, Double.POSITIVE_INFINITY);
			Finished.put(node, null);
		}
		Dist.put(node1, 0.00); // Adds the initial node with distance 0
		node_it = this.weightedgraph.listNodes();
		while(node_it.hasNext()) // Setting distances to infinity initially
		{
			node = node_it.next();
			tempPair = new Pair<String, Double>(node, Dist.get(node));
			Q.add(tempPair);	
		}
		
		
		edge<String,Double> temp_edge;
		String minDest;
		Double weight;
		
		while(!Q.isEmpty()) 
		{ 
			minDest = Q.remove().getKey();
			if(minDest.equals(node2)) 
			{
				break;
			}
			Iterator<edge<String,Double>> edge_it = this.weightedgraph.listChildren(minDest);
			
			while(edge_it.hasNext()) 
			{ 		
				temp_edge = edge_it.next(); 
				child = temp_edge.getchild();
				weight = temp_edge.getlabel();		
				if(Dist.get(child) > (Dist.get(minDest) + weight))
				{
					Dist.put(child, Dist.get(minDest) + weight); 
					Finished.put(child, new Pair<String, Double>(minDest, Dist.get(minDest))); 
					Q.add(new Pair<String, Double>(child, Dist.get(child))); 
				}
			}
		}
		//End of Dijkstra's algorithm
	
		if(Dist.get(node2) == Double.POSITIVE_INFINITY) 
		{
			output += "no path found\n";
			return output;
		}
		String nxt = node2;
		ArrayList<String> Path = new ArrayList<String>();
		while(Finished.get(nxt)!= null) 
		{
			Path.add(nxt); 
			nxt = Finished.get(nxt).getKey(); 
		}
		Path.add(node1);
		**/
		
	}
	/**
	private static void MemUse() {
		  Runtime runtime = Runtime.getRuntime();
		         // Run the garbage collector
		         runtime.gc();
		         
		         long memory = runtime.totalMemory() - runtime.freeMemory();
		         System.out.println("Used memory is bytes: " + memory);  
		  }
	public static void main(String args[]) {
		  //System.out.println("path from "+"Josh"+" to "+"Maliaia"+":"+"\nno path found\n");
		  MarvelPaths2 tmp = new MarvelPaths2();
		  tmp.createNewGraph("data/marvel.csv");
		  System.out.println(tmp.findPath("PRISM", "HUMAN ROBOT"));
		  System.out.println("complete");
		  MemUse();
		 }
		*/

}
