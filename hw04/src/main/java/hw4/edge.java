package hw4;

public class edge<T,V>{
	private T node1;
	private T node2;
	private V label;
	public edge(T node1,T node2,V label) {
		this.node1 = node1;
		this.node2 = node2;
		this.label = label;
	}
	public T getparent() {
		return node1;
	}
	public T getchild() {
		return node2;
	}
	public V getlabel() {
		return label;
	}

}
