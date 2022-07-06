import java.util.Queue;
import java.util.LinkedList;
public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {

	public Node<T> root = null;
	
	
	
	public void insert(T element){
		if(root == null){
			root = new Node<T>(element,null,null);
			root.parent = null;
		}
		else{
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		Node<T> thead = this.root;
		Node<T> n = new Node<T>(element,null,null);
		queue.add(thead);
		while(!queue.isEmpty()){
			thead = queue.peek();
			if(thead.left==null){
				
				thead.left = n;
				n.parent = thead;
				
				//t = thead;
				break;
			}
			else if(thead.right==null){
				thead.right = n;
				n.parent = thead;
				//t = thead;
				break;
			}
			else{
				queue.add(thead.left);
				queue.add(thead.right);
				//t = queue.peek();
				queue.remove();
			}
		}
		//System.out.println(n.parent==null);
		thead = n;
		//System.out.println(thead.parent.data==null);
		T temp;
		while(thead.parent!=null){
			//System.out.println(thead.parent==null);
			//System.out.println(thead.parent.data==null);
			
			if(thead.data.compareTo(thead.parent.data)>0){
				//System.out.println(thead.parent.data==null);

				temp = thead.data;
				thead.data = thead.parent.data;
				thead.parent.data = temp;
				//System.out.println(thead.parent.data==null);

				thead = thead.parent;
			}
			else{
				break;
			}
		}
		}
		//System.out.println(((Student)root.data).getName());
	}
	
	public void add (T element){
		if(root == null){
			root = new Node<T>(element,null,null);
			root.parent = null;
		}
		else{
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		Node<T> thead = this.root;
		Node<T> n = new Node<T>(element,null,null);
		queue.add(thead);
		while(!queue.isEmpty()){
			thead = queue.peek();
			if(thead.left==null){
				
				thead.left = n;
				n.parent = thead;
				
				//t = thead;
				break;
			}
			else if(thead.right==null){
				thead.right = n;
				n.parent = thead;
				//t = thead;
				break;
			}
			else{
				queue.add(thead.left);
				queue.add(thead.right);
				//t = queue.peek();
				queue.remove();
			}
		}
		//System.out.println(n.parent==null);
		thead = n;
		//System.out.println(thead.parent.data==null);
		T temp;
		while(thead.parent!=null){
			//System.out.println(thead.parent==null);
			//System.out.println(thead.parent.data==null);
			
			if(thead.data.compareTo(thead.parent.data)>=0){
				//System.out.println(thead.parent.data==null);

				temp = thead.data;
				thead.data = thead.parent.data;
				thead.parent.data = temp;
				//System.out.println(thead.parent.data==null);

				thead = thead.parent;
			}
			else{
				break;
			}
		}
		}
		//System.out.println(((Student)root.data).getName());
	}

	public T extractMax(){
		T ans = null;
		if(root!=null)
			ans = this.root.data;
		if(root!=null){
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		Node<T> thead = this.root;
		T flag = null;
		Node<T> prev = null;
		Node<T> t = null;
		queue.add(thead);
		while(!queue.isEmpty()){
			thead = queue.peek();
			if(thead.left==null){
				while(queue.size()>1){
					queue.remove();
				}
				flag = queue.peek().data;
				if(t!=null)
					t.right = null;
				else{
					root = null;
				}
				break;
			}
			else if(thead.right==null){
				
				flag = thead.left.data;
				thead.left = null;
				break;
			}
			else{
				queue.add(thead.left);
				queue.add(thead.right);
				t = queue.peek();
				queue.remove();
			}
		}
		
		thead = root;
		T temp = null;
		
		if(thead!=null){
			this.root.data = flag;
			while(thead!=null){
			
				if(thead.left!=null && thead.data.compareTo(thead.left.data)<0){
					if(thead.right==null || (thead.right!=null && thead.right.data.compareTo(thead.left.data)<0)){
						temp = thead.data;
						thead.data = thead.left.data;
						thead.left.data = temp;
						thead = thead.left;
					}
					else{
						temp = thead.data;
						thead.data = thead.right.data;
						thead.right.data = temp;
						thead = thead.right;
					}
				}
				else if(thead.right!=null && thead.data.compareTo(thead.right.data)<0){
					temp = thead.data;
					thead.data = thead.right.data;
					thead.right.data = temp;
					thead = thead.right;
				}
				else{
					break;
				}
				
			}
		}
		}
		return ans;
		
	}
    /*public void insert(T element) {
		Node<T> thead = root;
		Node<T> n = new Node<T>(element,null,null);
		queue.add(element);
		T flag;
		while(thead!=null){
			if(thead.left==null){
				thead.left = n;
				n.parent = thead;
			}
			else if(thead.right == null){
				thead.right = n;
				n.parent = thead;
			}
			else{
				if(thead.parent!=null){
					if(thead.parent.left.equals(thead)){
						thead = thead.parent.right;
					}
					else{
						thead = thead.parent.left.left;
					}
				}
				else{
					thead = thead.left;
				}
			}
		}
		thead = n;
		if(thead.parent!=null){
			while(thead.data.compareTo(thead.parent.data)>0){
				flag = thead.data;
				thead.data = thead.parent.data;
				thead.parent.data = flag;
				thead = thead.parent;
				if(thead.parent==null)
					break;
			}
		}
		
    }
    
    public T extractMax(){
    	Node<T> thead = root;
    	T ans = thead.data;
    	T flag = null;
    	while(thead!=null){
    		if(thead.left==null){
    			if(thead.parent==null)
    				root = null;
    			else{
    				if(thead.parent.left.equals(thead)){
    					flag = thead.parent.right.data;
    					thead.parent.right = null;
    				}
    				else{
    					flag = thead.parent.left.right.data;
    					thead.parent.left.right = null;
    				}
    			}
    		}
    		else if(thead.right==null){
    			flag = thead.left.data;
    			thead.right = null;
    		}
    		else{
    			if(thead.parent==null){
    				thead = thead.left;
    			}
    			else {
    				if(thead.parent.left.equals(thead)){
    					thead = thead.parent.right;
    				}
    				else{
    					if(thead.parent.left.left==null){
    						flag = thead.right.data;
    						thead.right = null;
    					}
    					else
    						thead = thead.parent.left.left; 
    				}
    			}
    		}
    	}
    }

   /* public T extractMax() {
    	Node<T> thead = root;
    	T ans = thead.data;
    	Node<T> prev = null;
    	T flag = null;
    	while(thead!=null){
    		if(thead.left==null){
    			if(prev==null)
    				root = null;
    			else{
    				flag = prev.right.data;
    				prev.right = null;
    			}
    		}
    		else if(thead.right==null){
    			flag = thead.left.data;
    			thead.left = null;
    		}
    		else{
    			if(p)
    			
    			prev = thead;
    			thead = thead.left;
    		}
    	}
    	thead = root;
    	thead.data = flag;
    	T temp;
    	if(root!=null)
    	{
    		
    		while(thead!=null){
    			if(thead.data.compareTo(thead.left.data)<0){
    				temp = thead.left.data;
    				thead.left.data = thead.data;
    				thead.data = temp;
    				thead = thead.left;
    			}
    			else if(thead.data.compareTo(thead.right.data)<0){
    				temp = thead.right.data;
    				thead.right.data = thead.data;
    				thead.data = temp;
    				thead = thead.right;
    			}
    			else
    				break;
    		}
    	}
    	
        return flag;
    }*/
	public void delete(T x){
		Node<T> thead = this.root;
		Node<T> prev = null;
		thead = search(x,thead);
		if(thead!=null){
		if(thead.parent==null)
			this.extractMax();
		else{
			MaxHeap<T> m = new MaxHeap<T>();
			m.root = thead;
			prev = thead.parent;
			String s;
			if(prev.left.equals(thead))
				s = "L";
			else
				s = "R";
			m.extractMax();
			if(s.equals("L"))
				prev.left = m.root;
			else
				prev.right = m.root;
		}
		}
	}
	
	public Node<T> search(T x,Node<T> thead){
		if(thead == null)
			return null;
		else{
			System.out.println((x==null)+" "+(thead.data==null));
		if(x.equals(thead.data))
			return thead;
		else{
			Node<T> a = search(x,thead.left);
			Node<T> b = search(x,thead.right);
			if(a!=null)
				return a;
			else
				return b;
		}
		}
	}

}