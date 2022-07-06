import java.lang.Math;
public class MyHashTableSC<K,T> implements MyHashTable_<K,T>
{
	public int size;
	BST<T>[] SArray;
	public MyHashTableSC(int n)
	{
		this.size=n;
		SArray=(BST<T>[])new BST[n];
	}
	public int insert(K key, T obj)
	{
		 K p=key;
		 String[] a=p.toString().split(" ",2);
		    String str=a[0]+a[1];
		String str2=a[0];
		int hash1=(int)HashFunction.djb2(str, size);
		int i=1;
		Node<T> x=new Node<T>(obj,null,null);
	    if(SArray[hash1%size]==null)
			SArray[hash1%size]= new BST<T>(x);
		else
		
			i+=SArray[hash1%size].insert(x,str2);
	       //System.out.println(hash1%size+"fytfhg");
		
	       //System.out.println(hash1%size+"fytfhg");
		return i;
	}
	public int update(K key, T obj)
	{
		K p=key;
		 String[] a=p.toString().split(" ",2);
		    String ptr=a[0]+a[1];
		String str=p.toString();
		
		int hash1=(int)HashFunction.djb2(ptr, size);
		//System.out.println(SArray[hash1%size]==null);
		Node<T> nd=SArray[hash1%size].find(str);
		//System.out.println(nd==null);
		if(nd!=null)
			nd.data=obj;
		else return -1;
		//System.out.println(SArray[hash1%size]==null);
		//System.out.println(SArray[hash1%size].search(str));
		return SArray[hash1%size].search(str);
	}
	public int delete(K key)
	{
		K p=key;
		 String[] a=p.toString().split(" ",2);
		    String ptr=a[0]+a[1];
		String str=p.toString();
	
		int hash1=(int)HashFunction.djb2(ptr, size);
		Node<T> nd=SArray[hash1%size].find(str);
		//System.out.println(SArray[hash1%size].search(str));
		if(contains(key)) {
			
		
		int d=SArray[hash1%size].search(str)+SArray[hash1%size].delete(nd);
		return d;}
		else {
			System.out.println("E");
			return 0;
		}
	}
	public boolean contains(K key)
	{
		 K p=key;
		 String[] a=p.toString().split(" ",2);
		    String ptr=a[0]+a[1];
		String str=p.toString();
		
		int hash1=(int)HashFunction.djb2(ptr, size);
		//System.out.println(str2==null);
		if(SArray[hash1%size]!=null)
		{
		Node<T> k=SArray[hash1%size].find(str);
		if(k==null)
			return false;
		else
			return true;
		}
		else return false;
	}
	   public T get(K key) throws NotFoundException
	   {
			K p=key;
			 String[] a=p.toString().split(" ",2);
			    String ptr=a[0]+a[1];
			String str=p.toString();
		
			int hash1=(int)HashFunction.djb2(ptr, size);
			int k=SArray[hash1%size].search(str);
			if(k!=-1)
			{
				Node<T> nd=SArray[hash1%size].find(str);
				if(nd!=null)
				  return nd.data;
				else 
					return null;
			}
			else
				return null;
	   }
	   public String address(K key) throws NotFoundException
	   {
		   K p=key;
		   String[] a=p.toString().split(" ",2);
		    String ptr=a[0]+a[1];
			String str=p.toString();
			
			int hash1=(int)HashFunction.djb2(ptr, size);
			int k=SArray[hash1%size].search(str);
			String add=Integer.toString(hash1%size)+"-";
			if(k!=-1)
			{
				Node<T> r=SArray[hash1%size].root;
				while(r!=null)
				{    String[] a1=r.data.toString().split(" ",2);
					if(a1[0].compareTo(a[0])<0)
					{
						r=r.right;
						add+="R";
					}
					else if(a1[0].compareTo(a[0])>0)
					{
						r=r.left;
						add+="L";
					}
					else
					{
						if(a1[1].compareTo(a[1])<0)
						{
							r=r.right;
							add+="R";
						}
						else
						  break;
					}
				}
				return add;
			}
			else
				return "E";
	   }

}