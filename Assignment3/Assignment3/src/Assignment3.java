import java.io.*;
public class Assignment3 {
	public static void main(String[] args) throws IOException,NotFoundException
	{
		int hashtablesize=73;
		//int hashtablesize=Integer.valueOf(args[0]);
		String HashType="SCBST";
		//String HashType=args[1];
		File file=new File("inp");
//		File file=new File("/home/dell/Desktop/inputfile.txt");
		//File file=new File("/home/dell/Desktop/inputfile2.txt");

		//File file=new File(args[2]);
		MyHashTable_<Pair,Student> arr;
		Student dobj= new Student("d","d","d","d","d"); 
		MyHashTableDH<Pair,Student> arr1=new MyHashTableDH<Pair,Student>(hashtablesize,dobj);
		MyHashTableSC<Pair,Student> arr2=new MyHashTableSC<Pair,Student>(hashtablesize);
		BufferedReader br=new BufferedReader(new FileReader(file));
//		String line=null;
		String line=br.readLine();

		while(line!=null)
		{
			String[] a1=line.split(" ",2);
			if(a1[0].equals("insert"))
			 {
				 String[] brr=a1[1].split(" ");
				 Student s=new Student(brr[0],brr[1],brr[2],brr[3],brr[4]);
				 Pair p=new Pair(brr[0],brr[1]);
				 if(HashType.equals("DH"))
					 System.out.println(arr1.insert(p, s));
				 else if(HashType.equals("SCBST"))
					 System.out.println(arr2.insert(p, s));
			 }
			 else if(a1[0].equals("update"))
			 {
				 String[] brr=a1[1].split(" ");
				 Student s=new Student(brr[0],brr[1],brr[2],brr[3],brr[4]);
				 Pair p=new Pair(brr[0],brr[1]);
				 if(HashType.equals("DH"))
					 System.out.println(arr1.update(p, s));
				 else if(HashType.equals("SCBST"))
					 System.out.println(arr2.update(p, s));
		     }
			 else if(a1[0].equals("delete"))
			 {
				 String[] brr=a1[1].split(" ",2);
				 Pair p=new Pair(brr[0],brr[1]);
				 if(HashType.equals("DH"))
					 System.out.println(arr1.delete(p));
				 else if(HashType.equals("SCBST")) {
					 int x;
				 x= arr2.delete(p);
					 if (x!=0)
					 System.out.println(x);}
			}
			 else if(a1[0].equals("contains"))
			 {
				 String[] brr=a1[1].split(" ",2);
				 Pair p=new Pair(brr[0],brr[1]);
				 //System.out.println(arr.contains(p));
				 if(HashType.equals("DH"))
				 {
					 if(arr1.contains(p))
						 System.out.println("T");
					 else
						 System.out.println("F");
				 }
				 else if(HashType.equals("SCBST"))
				 {
					 if(arr2.contains(p))
						 System.out.println("T");
					 else
						 System.out.println("F");
				 }
			 }
			 else if(a1[0].equals("get"))
			 {
				 String[] brr=a1[1].split(" ",2);
				 Pair p=new Pair(brr[0],brr[1]);
				 if(HashType.equals("DH"))
				 {
				 Student st=arr1.get(p);
				 if(st==null)
				 {
					 System.out.println("E");
				 }
				 else
				 {
				 System.out.println(st.fname+" "+st.lname+" "+st.hostel+" "+st.department+" "+st.cgpa);
				 }
				 }
				 if(HashType.equals("SCBST"))
				 {
				 Student st=arr2.get(p);
				 if(st==null)
				 {
					 System.out.println("E");
				 }
				 else
				 {
				 System.out.println(st.fname+" "+st.lname+" "+st.hostel+" "+st.department+" "+st.cgpa);
				 }
				 }

			 }
			 else if(a1[0].equals("address"))
			 {
				 String[] brr=a1[1].split(" ",2);
				 Pair p=new Pair(brr[0],brr[1]);
				 if(HashType.equals("DH"))
					 System.out.println(arr1.address(p));
				 else if(HashType.equals("SCBST"))
					 System.out.println(arr2.address(p));
			 }
			line=br.readLine();

		}
		br.close();
	}
	
}