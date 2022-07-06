
public interface TrieInterface<T> {

	boolean insert(String word, T value);
	TrieNode<T> search(String word);
	TrieNode<T> startsWith(String prefix);
	void printTrie(TrieNode trieNode);
	boolean delete(String word);
	void print();
	void printLevel(int level);
}

