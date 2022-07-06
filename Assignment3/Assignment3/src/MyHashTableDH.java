import java.lang.Math; 
public class MyHashTableDH<K,T> implements MyHashTable_<K,T>
{
	public int size=0;
	public T dobj;
	T[] HashArray;
	K[] KArray;
	public MyHashTableDH(int Arraysize, T dobj)
	{
		size=Arraysize;
		this.dobj=dobj;
		HashArray= (T[])new Object[size];
		KArray= (K[])new Object[size];
	}

	public int insert(K key, T obj)
	{
		 K p=key;
		 String[] a=p.toString().split(" ",2);
		 String str=a[0]+a[1];
		
		int hash1=(int)HashFunction.djb2(str, size);
		int hash2=(int)HashFunction.sdbm(str, size);
		int i=0;
		while(HashArray[hash1%size]!=null && !HashArray[hash1%size].equals(dobj))
		{
			hash1+=hash2;
			i+=1;
		}
		HashArray[hash1%size]=obj;
		KArray[hash1%size]=key;
		return i+1;
	}
	public int update(K key, T obj)
	{   
		K p=key;
	    String[] a=p.toString().split(" ",2);
	    String str=a[0]+a[1];
	    int hash1=(int)HashFunction.djb2(str, size);
		int hash2=(int)HashFunction.sdbm(str, size);
		int i=0;
		K x=KArray[hash1%size];
		//System.out.println(x==null);
	
		while(KArray[hash1%size]!=null||HashArray[hash1%size]==dobj)
		{
			//System.out.println(x==null);
			
			if(KArray[hash1%size]!=null)
			{
				 String[] ax=x.toString().split(" ",2);
				    String str2=ax[0]+ax[1];
				if((str2).equals(str))
				{
					HashArray[hash1%size]=obj;
					break;
				}
			}
			hash1+=hash2;
			i+=1;
			x=KArray[hash1%size];
		}
		HashArray[hash1%size]=obj;
		return i+1;
	}
	public int delete(K key)
	{
		 K p=key;
		 String[] a=p.toString().split(" ",2);
		    String str=a[0]+a[1];
		
		int hash1=(int)HashFunction.djb2(str, size);
		int hash2=(int)HashFunction.sdbm(str, size);
		int i=0;
		 K x=KArray[hash1%size];
	
		while(KArray[hash1%size]!=null ||HashArray[hash1%size]==dobj)
		{
			if(HashArray[hash1%size]!=dobj)
			{
				 String[] ax=x.toString().split(" ",2);
				    String str2=ax[0]+ax[1];
			if((str2).equals(str))
			{
				break;
			}
			else
			{
				hash1+=hash2;
				 x=KArray[hash1%size];
			}
			}
			else
			{
				hash1+=hash2;
				x=KArray[hash1%size];
			}
			i+=1;
		}
		/*while(!(x.f+x.s).equals(str))
		{
			hash1+=hash2;
			i+=1;
			x=(Pair)KArray[hash1%size];
		}*/
		HashArray[hash1%size]=dobj;
		KArray[hash1%size]=null;
		return i+1;
	}
	public boolean contains(K key)
	{
		K p=key;
		 String[] a=p.toString().split(" ",2);
		    String str=a[0]+a[1];
		
		int hash1=(int)HashFunction.djb2(str, size);
		int hash2=(int)HashFunction.sdbm(str, size);
		int i=0;
		boolean t=false;
		K x=KArray[hash1%size];
		//System.out.println(x==null);
		while(KArray[hash1%size]!=null||HashArray[hash1%size]==dobj)
		{
			
			//System.out.println(x==null);
			if(HashArray[hash1%size]!=dobj)
			{
				 String[] ax=x.toString().split(" ",2);
				    String str2=ax[0]+ax[1];
				if((str2).equals(str))
				{
					t=true;
					break;
				}
				else
				{
					hash1+=hash2;
					x=KArray[hash1%size];
				}
			}
			else
			{
				hash1+=hash2;
				//System.out.println(hash1+"jfjhtfgfhgktf");
				//System.out.println(hash2+"jfjf");
				x=KArray[hash1%size];
			}
		}
		return t;
	}
	public T get(K key) throws NotFoundException
	{
		K p=key;
		 String[] a=p.toString().split(" ",2);
		    String str=a[0]+a[1];
		int hash1=(int)HashFunction.djb2(str, size);
		int hash2=(int)HashFunction.sdbm(str, size);
	       K x=KArray[hash1%size];
	     
		T g=null;
		while(KArray[hash1%size]!=null||HashArray[hash1%size]==dobj)
		{
			if(HashArray[hash1%size]!=dobj)
			{
				  String[] ax=x.toString().split(" ",2);
				    String str2=ax[0]+ax[1];
			if((str2).equals(str))
			{
				g=HashArray[hash1%size];
				break;
			}
			else
			{
			hash1+=hash2;
			x=KArray[hash1%size];
			}
			}
			else
			{
				hash1+=hash2;
				x=KArray[hash1%size];
			}
		}
		if(KArray[hash1%size]==null)
		{
			return null;
			//throw new NotFoundException("no such key is found");
		}
		return g;
	}
	public String address(K key) throws NotFoundException
	{
		K p=key;
		 String[] a=p.toString().split(" ",2);
		    String str=a[0]+a[1];
		
		int hash1=(int)HashFunction.djb2(str, size);
		int hash2=(int)HashFunction.sdbm(str, size);
		K x=KArray[hash1%size];

		int g=0;
		while(KArray[hash1%size]!=null||HashArray[hash1%size]==dobj)
		{
			if(HashArray[hash1%size]!=dobj)
			{
				 String[] ax=x.toString().split(" ",2);
				    String str2=ax[0]+ax[1];
			if((str2).equals(str))
			{
				g=hash1%size;
				break;
			}
			else
			{
			hash1+=hash2;
			x=KArray[hash1%size];
			}
			}
			else
			{
				hash1+=hash2;
				x=KArray[hash1%size];
			}
		}
		if(KArray[hash1%size]==null)
		{
			return "E";
			//throw new NotFoundException("no such key is found");
		}
		return Integer.toString(g);
	}
	
}