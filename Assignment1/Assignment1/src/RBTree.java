public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {

	public RedBlackNode<T,E> root = null;

	
    @Override
    public void insert(T key, E value) {
    	RedBlackNode<T,E> n = new RedBlackNode<T,E>(key,value,null,null);
    	
    	if(root==null){
    		
    		root = new RedBlackNode<T,E>(key, value, null, null);
    		//System.out.println(root==null);
    		root.parent = null;
    	}
    	else{
    		//System.out.println(key+"jerkbrg");
    		
    		n.colour = "red";
    		RedBlackNode<T, E> thead = this.root;
    		RedBlackNode<T, E> prev = null;
    		String p="X";
    		while(thead!=null){
    			prev = thead;
    			if(thead.key.compareTo(key)<0){
    				thead = thead.right;
    				p = "R";
    			}
    			else if(thead.key.compareTo(key)>0){
    				thead = thead.left;
    				p = "L";
    			}
    			else{
    				thead.insert(value);
    				//System.out.println(thead.list.size()+"AAAAAAA");
    				break;
    			}
    		}
    		if(thead==null){
    			
    		if(p.equals("L")){
    			//
    			prev.left = n;
    			n.parent = prev;
    			//System.out.print(n.key);
    			//System.out.println(n.parent.key+"checking");
    			//System.out.println(this.root.left.key);
    		}
    		else if(p.equals("R")){
    			//System.out.println(key+"world");
    			prev.right = n;
    			n.parent = prev;
    			//System.out.print(n.key);
    			//System.out.println(n.parent.key+"checking");
    			
    		}
    		
    		while(n.parent.colour.equals("red")){
    			//System.out.println((n.parent.left==null)+"tjyrjtukilil");
    			if(n.parent.parent.left!=null && n.parent.parent.left.equals(n.parent)){
    				if(n.parent.parent.right==null){
    					RedBlackNode<T, E> gp = n.parent.parent;
    					RedBlackNode<T, E> vgp = n.parent.parent.parent;
    					rotate(n,n.parent,gp,vgp);
    					break;
    				}
    				else if(n.parent.parent.right.colour.equals("black")){
    					RedBlackNode<T, E> gp = n.parent.parent;
    					RedBlackNode<T, E> vgp = n.parent.parent.parent;
    					rotate(n,n.parent,gp,vgp);
    					break;
    				}
    				else{
    					if(n.parent.parent.equals(this.root)){
    						n.parent.colour = "black";
    						n.parent.parent.right.colour = "black";
    					}
    					else{
    						n.parent.colour = "black";
    						n.parent.parent.right.colour = "black";
    						n.parent.parent.colour = "red";
    						n = n.parent.parent;
    					}
    				}
    			}
    			else{
    				if(n.parent.parent.left==null){
    					RedBlackNode<T, E> gp = n.parent.parent;
    					RedBlackNode<T, E> vgp = n.parent.parent.parent;
    					rotate(n,n.parent,gp,vgp);
    					break;
    				}
    				else if(n.parent.parent.left.colour.equals("black")){
    					RedBlackNode<T, E> gp = n.parent.parent;
    					RedBlackNode<T, E> vgp = n.parent.parent.parent;
    					rotate(n,n.parent,gp,vgp);
    					break;
    				}
    				else{
    					
    					if(n.parent.parent.equals(this.root)){
    						
    						n.parent.colour = "black";
    						//if(n.parent.parent.right.equals(n.parent))
    							n.parent.parent.left.colour = "black";
    						//else
    							//n.parent.parent.right.colour = "black";
    							//System.out.println(key+"ektamittal"+(n.parent.left==null)+" "+(n.parent.right==null));
    					}
    					else{
    						n.parent.colour = "black";
    						n.parent.parent.left.colour = "black";
    						n.parent.parent.colour = "red";
    						n = n.parent.parent;
    					}
    				}
    				
    			}
    		}
    		}
    	}
    }
    
    public void rotate(RedBlackNode<T, E> n, RedBlackNode<T, E> prev,RedBlackNode<T, E> gp,RedBlackNode<T, E> vgp){
    	String v ;
    	if(vgp==null)
    		v = "N";
    	else if(vgp.left.equals(gp))
    		v = "L";
    	else
    		v = "R";
    	if(gp.left!=null && gp.left.equals(prev)){
    		if(prev.left!=null && prev.left.equals(n)){
    			if(v.equals("L")){
    				vgp.left = prev;
    				prev.parent = vgp;
    				gp.left = prev.right;
    				if(gp.left!=null)
    				gp.left.parent = gp;
    				prev.right = gp;
    				gp.parent = prev;
    				prev.colour = "black";
    				gp.colour = "red";
    				n.colour = "red";
    			}
    			else if(v.equals("R")){
    				vgp.right = prev;
    				prev.parent = vgp;
    				gp.left = prev.right;
    				if(gp.left!=null)
    				gp.left.parent = gp;
    				prev.right = gp;
    				gp.parent = prev;
    				prev.colour = "black";
    				gp.colour = "red";
    				n.colour = "red";
    			}
    			else{
    				this.root = prev;
    				prev.parent = null;
    				gp.left = prev.right;
    				if(gp.left!=null)
    				gp.left.parent = gp;
    				prev.right = gp;
    				gp.parent = prev;
    				prev.colour = "black";
    				gp.colour = "red";
    				n.colour = "red";
    			}
    						
    		}
    		else{
    			gp.left = n;
    			n.parent = gp;
    			prev.right = n.left;
    			if(prev.right!=null)
    			prev.right.parent = prev;
    			n.left = prev;
    			prev.parent = n;
    			rotate(prev,n,gp,vgp);
    		}
    	}
    	else{
    		if(prev.right!=null && prev.right.equals(n)){
    			if(v.equals("L")){
    				vgp.left = prev;
    				prev.parent = vgp;
    				gp.right = prev.left;
    				if(gp.right!=null)
    				gp.right.parent = gp;
    				prev.left = gp;
    				gp.parent = prev;
    				prev.colour = "black";
    				gp.colour = "red";
    				n.colour = "red";
    			}
    			else if(v.equals("R")){
    				vgp.right = prev;
    				prev.parent = vgp;
    				gp.right = prev.left;
    				if(gp.right!=null)
        			gp.right.parent = gp;
    				prev.left = gp;
    				gp.parent = prev;
    				prev.colour = "black";
    				gp.colour = "red";
    				n.colour = "red";
    			}
    			else{
    				this.root = prev;
    				prev.parent = null;
    				gp.right = prev.left;
    				if(gp.right!=null)
    				gp.right.parent = gp;
    				prev.left = gp;
    				gp.parent = prev;
    				prev.colour = "black";
    				gp.colour = "red";
    				n.colour = "red";
    			}
    		}
    		else{
    			gp.right = n;
    			n.parent = gp;
    			prev.left = n.right;
    			if(prev.left!=null)
    			prev.left.parent = prev;
    			n.right = prev;
    			prev.parent = n;
    			rotate(prev,n,gp,vgp);
    		}
    	}
    }

    @Override
    public RedBlackNode<T, E> search(T key) {
    	RedBlackNode<T, E> thead = this.root;
    	while(thead!=null){
    		if(thead.key.compareTo(key)<0)
    			thead = thead.right;
    		else if(thead.key.compareTo(key)>0)
    			thead = thead.left;
    		else{
    			break;
    		}
    	}
    	
        return thead;
    }
}