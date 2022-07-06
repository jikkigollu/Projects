
public class Node<T> {

	public T data;
	public Node<T> left;
	public Node<T> right;
	public Node<T> parent;
	public Node(T data, Node<T> left, Node<T> right){
		this.data = data;
		this.right = right;
		this.left = left;
	}
}
