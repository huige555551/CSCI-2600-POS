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

public class Graph <T,V>{
	private HashMap<T,ArrayList<edge<T,V>>> nodes = new HashMap<T, ArrayList<edge<T,V>> >();

	/**
	 * @effects Constructs a new Graph and initialize the container
	 */
	public Graph()
	{
		this.nodes = new HashMap<T, ArrayList<edge<T,V>> >();
	}


	/**
	 * @param node : the label of the new node
	 * @effects places a new HashMap<T, ArrayList<V> > equal to null in 
	 * nodes values with a key of nodeData mapped to it. 
	 * If nodeData is already in the keySet of nodes then nothing is modified.
	 * @modifies nodes values and keySet if nodeData is not in the keySet of nodes.keySet()
	 * @throws NullPonterException"Add Node cannot be null" if node is null
	 */
	public void addNode(T node)
	{ 
		if (node == null) {throw new NullPointerException("Add Node cannot be null");}
		if (!this.nodes.containsKey(node))
		{
			ArrayList<edge<T,V>> temp = new ArrayList<edge<T,V>>();
			this.nodes.put(node, temp);
			
		}
		//checkRep();
	}


	/**
	 * @param parentNode(Generic T), childNode(Generic T) and edgeLabel(Generic V)
	 * @modifies 
	 * If the parentNode and childNode are in the graph, the edgeLabel will be added to the graph.
	 * if the parentNode or the childNode are not in the graph, modifies nothing. 
	 * @throws NullPointerException if at least one input is null
	 */
	
	public void addEdge(T parentNode, T childNode, V edgeLabel)
	{
		if(parentNode==null||childNode ==null) {
			throw new NullPointerException("Add Node cannot be null");
		}
		if(edgeLabel ==null) {
			throw new NullPointerException("Edge label cannot be null");
		}
		//if(parentNode.equals(childNode)) {
		//	return;
		//}
		if(this.nodes.containsKey(parentNode) && this.nodes.containsKey(childNode))
		{
			ArrayList<edge<T,V>> temp = this.nodes.get(parentNode);
			edge<T,V> A = new edge<T,V>(parentNode,childNode,edgeLabel);
			temp.add(A);
		}
		//checkRep();	
	}
	/**
	 * @returns  an iterator to a new TreeSet containing the keySet of nodes
	 * This iterator returns the nodes in lexicographical (alphabetical) order.
	 
	 */
	public Iterator<T> listNodes()
	{
		Set<T> list_of_nodes = new TreeSet<T>();
		list_of_nodes.addAll(this.nodes.keySet());
		Iterator<T> graph_it = list_of_nodes.iterator();
		return graph_it;
	}
	/**
	 * @returns returns null if parentNode is not in the keySet.
	 * If parentNode is in the graph, the iterator returns the list of childNode(edgeLabel) in lexicographical (alphabetical) order 
	 * by node name and secondarily by edge label. 
	 */
	public Iterator<edge<T,V>> listChildren(T parentNode)
	{ 	if(parentNode==null) {
			return null;
		}
		if(this.nodes.keySet().isEmpty()) 
		{
			return null;
		}
		if(this.nodes.get(parentNode) != null)
		{ 
			ArrayList<edge<T,V>> list_of_children = new ArrayList<edge<T,V>>();
			Iterator<edge<T,V>> key_it = this.nodes.get(parentNode).iterator();
			while(key_it.hasNext())
			{
				list_of_children.add( key_it.next());
				
			}
			Iterator<edge<T,V>> graph_it = list_of_children.iterator();
			return graph_it;
		}
		else 
		{	
			return null;
		}
	}
	public int size() {
		return this.nodes.size();
	}
	
	/**
     * Checks that the representation invariant holds
	 * @throws Runtime Exceptions if representation invariant is violated.
	 */
	private void checkRep() throws RuntimeException
	{
		if(this.nodes == null)
		{
            throw new RuntimeException("nodes == null");
		}
		if(this.nodes.containsKey(null))
		{
            throw new RuntimeException("node's keySet contains null");
		}
		if(this.nodes.keySet() == null)
		{
            throw new RuntimeException("nodes' keySet == null");
		}
		if(this.nodes.values() == null)
		{
            throw new RuntimeException("node's HashMap == null");
		}
		if(this.nodes.containsValue(null))
		{
            throw new RuntimeException("a key in nodes maps to null");
		}
		Iterator<T> node_it = this.nodes.keySet().iterator();
		Iterator<T> chil_it;
		T node_key;
		T chil_key;
		
		
	}
}
