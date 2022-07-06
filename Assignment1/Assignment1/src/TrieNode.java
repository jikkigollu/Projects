
public class TrieNode<T> {
	
	public char data;
	public TrieNode<T>[] next;
	//public T[] brr;
	public T value;
	public int previdx;
	public TrieNode<T>[] prev;
	public TrieNode(char data, TrieNode<T>[] next,T value,int previdx,TrieNode<T>[] prev) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.next = next;
		//this.brr = brr; , T[] brr
		this.value = value;
		this.previdx = previdx;
		this.prev = prev;
	}
	public T getValue(){
		
		return this.value;
	}
}
