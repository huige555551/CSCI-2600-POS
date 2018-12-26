package hw4;

import java.util.*;
/**
	Overview: 
		The GraphWrapper is a mutable directed labeled mutigraph.
		No duplicate vertex is allowed, but duplicate edges are allowed.

	Abstract Function:
		The graph is stored as a multilayer map.
		There is a set of nodes in the first level of the map, with each node containing 
		a HashMap with set of its children as keys. 
		Each children maps to an array of all of the edges from the parentnode to the childnode. Every edges are labeled via the edgeLabels. 

	Representation Invariant:
		(g.nodes !=  null)
		&& (no nulls in keySet) 
		&& (keySet is not null)
		&& (value of keySet is not null)
		&& (no duplicate keys in keyset)
		&& (for all keys in keyset g.nodes.get(key).containsKey(null) == false)
		&& (for all keys in keyset g.nodes.get(key).keySet() != null) 
		&& (for all keys in keyset g.nodes.get(key).values() != null);
		&& (for all keys in keyset g.nodes.get(key).get(key in other keyset).contains(null) == false);

	 */

public class GraphWrapper {

	private Graph<String,String> graph;

	/**
	 * @effects Constructs a new Graph and initialize the container
	 */
	public GraphWrapper()
	{
		this.graph = new Graph<String,String>();
	}


	/**
	 * @requires node is not null
	 * @param node : the label of the new node
	 * @effects places a new HashMap<String, ArrayList<V> > equal to null in 
	 * nodes values with a key of nodeData mapped to it. 
	 * If nodeData is already in the keySet of nodes then nothing is modified.
	 * @modifies nodes values and keySet if nodeData is not in the keySet of nodes.keySet()
	 */
	public void addNode(String node)
	{ 
		this.graph.addNode(node);
	}


	/**
	 * @requires the input Strings are not null
	 * @param parentNode, childNode and edgeLabel
	 * @modifies If at least one input is null, modifies nothing.
	 * If the parentNode and childNode are in the graph, the edgeLabel will be added to the graph.
	 * if the parentNode or the childNode are not in the graph, modifies nothing. 
	 */
	
	public void addEdge(String parentNode, String childNode, String edgeLabel)
	{
		this.graph.addEdge(parentNode, childNode, edgeLabel);
	}
	/**
	 * @returns returns an iterator to a new TreeSet containing the keySet of nodes
	 * This iterator returns the nodes in lexicographical (alphabetical) order.
	 
	 */
	public Iterator<String> listNodes()
	{
		return this.graph.listNodes();
	}
	/**
	 * @returns returns null if parentNode is not in the keySet.
	 * If parentNode is in the graph, the iterator returns the list of childNode(edgeLabel) in lexicographical (alphabetical) order 
	 * by node name and secondarily by edge label. 
	 */
	public Iterator<String> listChildren(String parentNode)
	{ 	Iterator<edge<String,String>> it = graph.listChildren(parentNode);
		if(it!= null)
		{ 
			ArrayList<String> list_of_children = new ArrayList<String>();
			while(it.hasNext())
			{
				edge<String,String> curr_key = it.next();
				list_of_children.add(curr_key.getchild() + "(" +curr_key.getlabel() + ")");
				
			}
			Collections.sort(list_of_children);
			Iterator<String> graph_it = list_of_children.iterator();
			return graph_it;
		}
		else 
		{	
			return null;
		}
	}
	public int size() {
		return this.graph.size();
	}
}
