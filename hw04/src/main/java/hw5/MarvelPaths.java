package hw5;
import hw4.GraphWrapper;
import java.util.*;
import java.io.*;


public class MarvelPaths {
	private GraphWrapper graph;
	/**
	 * @effect construct an empty graph
	 * @modifies graph variable is created
	 */
	public MarvelPaths() {
		this.graph = new GraphWrapper();
	}
	/**
	 * 
	 * @param filename
	 * @modifies store book names and characters in graph
	 */
	public void createNewGraph(String filename) {
		
		Set<String> chars = new HashSet<String>();
		Map<String, Set<String>> CIB = new HashMap<String, Set<String> >();
		try {
			MarvelParser.readData(filename, CIB, chars);	
		}
		catch(IOException e){
			throw new RuntimeException("Bad file");
		}
		Iterator<String> char_it = chars.iterator();
		
		this.graph = new GraphWrapper();
		while(char_it.hasNext()) {
			this.graph.addNode(char_it.next());
		}
		for(String chara: chars) {
			for(String book: CIB.keySet()) {
				if(CIB.get(book).contains(chara)) {
					for(String chara2:CIB.get(book)) {
						if(!chara.equals(chara2)) {
							this.graph.addEdge(chara, chara2, book);
						}
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param node1
	 * @param node2
	 * @return the path from node1(Start character) node2(Destination Character).
	 * If no path found, return"no path found".
	 * If at least one of the character does not exist in the graph, return"unknown character XXX".
	 * If the two character name are the same, return no result but a start statement "path from XXX to XXX:".
	 */
	public String findPath(String node1, String node2) {
		Iterator<String> node_it = this.graph.listNodes();
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
		String n;
		String edge;
		String book_name;
		Queue<String> Q = new LinkedList<String>();
		Map<String,ArrayList<String> > M = new HashMap<String, ArrayList<String> >();
		Q.add(node1);
		ArrayList<String> empty = new ArrayList<String>();
		M.put(node1, empty);
		while(!Q.isEmpty()) {
			n = Q.poll();
			if(n.equals(node2)) {
				for(int i =0;i<M.get(n).size();i++) {
					output = output.concat(M.get(n).get(i));
					output = output.concat("\n");
				}
				return output;
			}
			Iterator<String> edge_it = this.graph.listChildren(n);
			if(edge_it!=null) {
			while(edge_it.hasNext()) {
				edge = edge_it.next();
				book_name = edge.substring(edge.indexOf("(")+1, edge.indexOf(")"));
				char_name = edge.substring(0,edge.indexOf("("));
				if(!M.containsKey(char_name)) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.addAll(M.get(n));
					temp.add(n+" to " + char_name +" via " +book_name);
					M.put(char_name, temp);
					Q.add(char_name);
				}
			}
			}
			
		}
		
		output += "no path found\n";
		return output;
	}
	

}

