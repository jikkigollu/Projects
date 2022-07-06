class Node<T>
{
	public T data;
	public Node<T> left;
	public Node<T> right;
	public Node(T data, Node<T> left, Node<T> right)
	{
		this.data=data;
		this.left=left;
		this.right=right;
	}
}
public class BST<T>
{
	public Node<T> root;
	public BST(Node<T> root)
	{
		this.root=root;
	}
    public boolean isEmpty()
    {
        return root == null;
    }
	public int insert(Node<T> n,String str)
	{
		int s=0;
		Node<T> r=root;
		
		while(r!=null)
		{   String[] a=r.data.toString().split(" ",2);
		     
			
			if(a[0].compareTo(str)>0) {
				if(r.left!=null)
				r=r.left;
				else {
					r.left = n;
					break;
				}
			}
			else{
				if(r.right!=null)
				r=r.right;
				else {
					r.right = n;
					break;
				}
			}
			s+=1;
		}
		//System.out.println(((Student)r.data).fname+((Student)r.data).lname);
		return s+1;
	}
	public Node<T> find(String str)
	{
		Node<T> temp=null;
		Node<T> r=root;
		String[] str2=str.split(" ",2);
		//System.out.println(str2[1]+str2[0]);
		//System.out.println(str2[0]+"hgtyfyg");
		//System.out.println(str2[1]+"hjvhtdyr");
		//System.out.println(str2.length);
		//System.out.println(((Student)r.data).fname);
		while(r!=null)
		{   String[] a=r.data.toString().split(" ",2);
			//System.out.println(temp);
			if(a[0].compareTo(str2[0])<0)
				r=r.right;
			else if(a[0].compareTo(str2[0])>0)
				r=r.left;
			else
			{
				if(a[1].compareTo(str2[1])!=0)
				{
					r=r.right;
				}
				else
					temp=r;
					break;
			}
		}
		//System.out.println(r);
		if(r==null)
		{
			//System.out.println(str+" not found");
			return null;
		}
		else
			return temp;
	}
	public int search(String str)
	{
		int s=1;
		Node<T> r=root;
		String[] str2=str.split(" ",2);
		while(r!=null)
		{    String[] a=r.data.toString().split(" ",2);
			if(a[0].compareTo(str2[0])<0)
				r=r.right;
			else if(a[0].compareTo(str2[0])>0)
				r=r.left;
			else
			{ 
				if(a[1].compareTo(str2[1])==0)
				{
					break;
				}
				else
					r=r.right;
			}
			s+=1;
		}
		if(r==null)
			return -1;
		else
			return s;
	}
	
	public int delete(Node<T> t)
	{
		//Node<T> r=root;
		Node<T> p2=null;
		Node<T> rn=null;
		Node<T> ln=null;
		Node<T> p1=null;
		if(t!=null)
		{
		if(t.left!=null)
			ln=t.left;
		if(t.right!=null)
		{
			rn=t.right;
			p1=rn;
		}
		}
		//Node<T> ln=t.left;
		//Node<T> rn=t.right;
		//Node<T> p1=rn;

		int i=0;
		String[] a=null;
				if(t!=null) {
				a=t.data.toString().split(" ",2);	
				};
		if(isEmpty()) {
			System.out.println("BST is empty");
		}
		//else if(search(t.data.toString())==-1)  
			//System.out.println(a[0]+a[1]+" is not present");
		else
		{
			if(t!=null)
			{
				//System.out.println(t.right==null);
				//System.out.println(rn==null);
				//System.out.println(p1==null);
				//System.out.println(t.right==rn);
				//System.out.println(rn==p1);
				if(t.left==null && t.right==null)
					t=null;
				else if(t.left==null)
				{
					t.data=rn.data;
					t.right=rn.right;
					t.left=rn.left;
					//System.out.print(rn.data+"jgierghbtuyru");
					
					rn=null;
					//System.out.print(t.data+"jgierghbtuyru");
					i+=1;
				}
				else if(t.right==null)
				{
					t.data=ln.data;
					t.right=ln.right;
					t.left=ln.left;
					ln=null;
					i+=1;
				}
				

				else 
				{
					System.out.println(rn==null);
					System.out.println(p1==null);

					while(p1!=null)
					{
						//System.out.println("hello");
				
						p2=p1;
						p1=p1.left;
						i+=1;
					}
					//System.out.println(p2==null);
					t.data=p2.data;
					delete(p2);
				}
				
			}
		}
		return i;
	}
}