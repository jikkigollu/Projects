//import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class Trie<T> implements TrieInterface<T>{
	
	TrieNode<T>[] root = null;
	@SuppressWarnings("unchecked")
	public Trie(){
		root = (TrieNode<T>[]) new TrieNode[54];
	}
	
	@SuppressWarnings("unchecked")
	public boolean insert(String word, T value){
		TrieNode<T> last = null;
		TrieNode<T>[] thead = this.root;
		int previdx = 0;
		TrieNode<T>[] prev = null;
	while(!word.equals("")){
		int flag = 0;
		if(word.charAt(0)>=97 && word.charAt(0)<=122)
			flag = word.charAt(0) - 'a';
		else if((int)word.charAt(0) == 32)
			flag = 52;
		else if(word.charAt(0)>=65 && word.charAt(0)<=90)
			flag = word.charAt(0)-'A'+26;
		if(thead[flag] == null)
			thead[flag] = new TrieNode<T>(word.charAt(0),null,value,previdx,prev);
		last = thead[flag];
		prev = thead;
		previdx = flag;
		if(thead[flag].next == null)
			thead[flag].next = (TrieNode<T>[])new TrieNode[54];
		thead = thead[flag].next;
		
		word = word.substring(1);
		
	}
	if(word.equals("")){
	last.next[53] = new TrieNode<T>('#',null,value,previdx,prev);
	return true;
	}
	else
		return false;
	}
	
	public TrieNode<T> search(String word){
		TrieNode<T> ans = null;
		TrieNode<T>[] thead = root;
		int i = 0;
		while(word.length()>i){
			int flag = 0;
			//System.out.println("wrfbhrwvhr"+(int)word.charAt(i));
			if(word.charAt(i)>=97 && word.charAt(i)<=122)
				flag = word.charAt(i) - 'a';
			else if((int)word.charAt(i) == 32)
				flag = 52;
			else if(word.charAt(i)>=65 && word.charAt(i)<=90)
				flag = word.charAt(i)-'A'+26;
			ans = thead[flag];
			if(thead[flag]==null)
				break;
			i++;
			thead = thead[flag].next; 
		}
		if(thead[53]!=null)
			i++;
		if(i == word.length()+1)
			return ans;
		else return null;
	}
	
	public boolean delete(String word){
		//System.out.println((this.search(word)==null)+"hbrh2gru24hru4ht");
		List<Integer> arr = new LinkedList<Integer>();
		List<Integer> brr = new LinkedList<Integer>();
		List<TrieNode<T>[]> crr = new LinkedList<TrieNode<T>[]>();
		if(this.search(word)==null){
			System.out.println("ERROR DELETING");
			return false;
		}
		else{
			//TrieNode<T> prev = null;
			TrieNode<T>[] thead = root,temp = null;
			int i = 0;
			while(i<word.length()){
				int flag = 0;
				if(word.charAt(i)>=97 && word.charAt(i)<=122)
					flag = word.charAt(i) - 'a';
				else if((int)word.charAt(i) == 32)
					flag = 52;
				else if(word.charAt(i)>=65 && word.charAt(i)<=90)
					flag = word.charAt(i)-'A' + 26;
				temp = thead[flag].next;
				int sum = 0;
				for(int count = 0;count<temp.length;count++){
					if(temp[count]!=null)
						sum++;
				}
				arr.add(sum);
				brr.add(flag);
				crr.add(thead);
				//thead[flag] = null;
				thead = temp;
				i++;
			}
			thead[53] = null;
			int idx = -1;
			for(int k = 0;k<arr.size();k++){
				if(arr.get(k)>1)
					idx = k;
			}
			if(idx!=arr.size()-1){
				for(int a=idx+1;a<crr.size();a++){
					crr.get(a)[brr.get(a)] = null;
				}
			}
				
			System.out.println("DELETED");
			return true;
		}
	}
	
	public TrieNode<T> startsWith(String word){
		TrieNode<T> ans = null;
		TrieNode<T>[] thead = root;
		int i = 0;
		while(i<word.length()){
			int flag = 0;
			if(word.charAt(i)>=97 && word.charAt(i)<=122)
				flag = word.charAt(i) - 'a';
			else if((int)word.charAt(i) == 32)
				flag = 52;
			else if(word.charAt(i)>=65 && word.charAt(i)<=90)
				flag = word.charAt(i)-'A' + 26;
			if(thead[flag]==null)
				break;
			else{
				i++;
				ans = thead[flag];
				thead = thead[flag].next;
			}
		}
		if(i==word.length())
			return ans;
		else 
			return null;
	}
	
	
	
	public void printLevel(int level){
		TrieNode<T>[] thead = root;
	/*	for(int i=0;i<thead.length;i++){
			if(thead[i]!=null){
				for(int j=0;j<thead[i].next.length;j++){
					if(thead[i].next[j]!=null)
						System.out.println(thead[i].next[j].data);
				}
				System.out.println(String.valueOf(thead[i].data)+"sdqkg");
			}
				
		}
		*/
		int c = 1;
		System.out.print("Level "+level+": ");
		String result = levelprinting(level,thead,c);
		//System.out.println(level+"    "+result.length()+result+"gggg");
		if(result.equals("#")){
			System.out.println("");
		}
		else{
			char[] answer = new char[result.length()];
			for(int p = 0; p<result.length();p++){
				answer[p] = result.charAt(p);
			}
			Arrays.sort(answer);
			for(int i=0;i<result.length();i++){
				if((int)answer[i]!=35 && (int)answer[i]!=32){
				if(i<result.length()-1)
					System.out.print(String.valueOf(answer[i])+",");
				else
					System.out.println(String.valueOf(answer[i]));
				}
			}
		}
		//System.out.println(result);
	}
	
	public String levelprinting(int level, TrieNode<T>[] thead, int c){
		
		String result = "";
		
		if(thead!=null){
		if(level>c){
			
			for(int i=0;i<thead.length;i++){
				if(thead[i]!=null){
					//System.out.println("AAAAAAAA"+String.valueOf(thead[i].data));
					result = result + levelprinting(level,thead[i].next,c+1);
				}
			}
			
			
			
		}
		else if(level==c){
			//String result = "";
			for(int i=0;i<thead.length;i++){
				if(thead[i]!=null){
					//if(thead.data)
					//System.out.println("AAAAAAAABBBBBB"+String.valueOf(thead[i].data));
					result+=String.valueOf(thead[i].data);
				}
			}
			
			//return result;
			
		}
		}
		return result;
	}
	
	
	
	
	/*public void printLevel(int level){
		String ans = "";
		String result = "";
		//previous index??
		int l = 1;
		TrieNode<T>[] thead = root,head = null;
		TrieNode<T>[] prev = null;
		while(thead!=null){
			if(l<level){
				int i;
				for(i=0; i<thead.length; i++){
					if(thead[i]!=null){
						ans += thead[i];
						break;
					}
				}
				prev = thead;
		        head = thead[i].next;
		        int j ;
		        for(j=0;j<head.length;j++){
					if(head[j]!=null){
						break;
					}
				}
		        if(j<head.length)
		        	thead = head;
		        else
		        	l--;
				l++;
			}
			else{
				int i;
				for(i=0;i<thead.length;i++){
					if(thead[i]!=null){
						result = result + (ans+thead[i]+",");
					}
				}
				l--;
				if(i!=thead.length)
					thead = thead[i].prev;
				else
					thead = prev;
			}
		}
		System.out.println(result.substring(0, result.length()-1));
	}
	*/
	public int findmaxheight(TrieNode<T>[] root){
		int c = 0,ans = 0;
		int p = 0;
		for(int i = 0;i < root.length; i++){
			if(root[i] != null){
				p = 1;
				//ans = max(ans,findmaxheight(root[i].next));
				if(root[i].next!=null)
					if(ans<findmaxheight((root[i]).next))
						ans = findmaxheight(root[i].next);
			}
		}
		return ans+p;
	}
	
	public void print(){
		TrieNode<T>[] thead = root;
		int c = 0;
		int h = findmaxheight(thead);
		for(int l = 1; l<=h ; l++){
			printLevel(l);
		}
	}
	
	public void printTrie(TrieNode trienode){
		String ans = "";
		TrieNode<T> temp = trienode;
		while(trienode!=null){
			ans += trienode.data;
			if(trienode.prev!=null)
				trienode = trienode.prev[trienode.previdx];
			else
				trienode = null;
		}
		TrieNode<T>[] thead = temp.next;
		for(int i=0;i<thead.length;i++){
			if(thead[i]!=null){
				printall(thead[i].next);
				//System.out.println()
			}
		}
	
		
	}
	
	public void printall(TrieNode<T>[] thead){
		if(thead!=null){
		if(thead[53]!=null){
			//String[] arr = .split(" ", 2);
			System.out.println(thead[53].value.toString());
		}
		for(int i=0;i<53;i++){
			if(thead[i]!=null){
				printall(thead[i].next);
			}
		}
		}			
	}
}
