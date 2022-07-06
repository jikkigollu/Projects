
public class User implements Comparable<User>{

	//public Job job;
	public String name;
	public User(String name){
		this.name = name;
		//job = new Job(null,null,this.name,0);
	}
	
	public int compareTo(User user){
		if(this.toString().compareTo(user.toString())>0)
			return 1;
		else if(this.toString().compareTo(user.toString())<0)
			return -1;
		else
			return 0;
	}
}
