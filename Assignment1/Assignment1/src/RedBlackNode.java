import java.util.List;
import java.util.LinkedList;
public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {

	public T key;
	public E value;
	public List<E> list;
	public String colour;
	public RedBlackNode<T, E> left,right;
	public RedBlackNode<T, E> parent;
	public RedBlackNode(T key, E value,RedBlackNode<T, E> left,RedBlackNode<T, E> right){
		this.key = key;
		this.value = value;
		list = new LinkedList<E>();
		list.add(value);
		this.left = left;
		this.right = right;
		this.colour = "black";
	}
	
	public void insert(E val){
		this.list.add(val);
	}
    @Override
    public E getValue() {
        return this.value;
    }

    @Override
    public List<E> getValues() {
        return this.list;
    }
}