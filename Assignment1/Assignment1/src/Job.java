
public class Job implements Comparable<Job>{
	
	public String name;
	//public User user;
	//public Project project;
	public int runtime;
	public String pname,uname;
	public int priority;
	public String status;
	public int check = 0;
	
	//public int execution_time;
	public int end_time;
	
	public Job(String name, int priority, int runtime,String pname,String uname){
		this.runtime = runtime;
		this.name = name; 
		this.priority = priority;
		this.status = "REQUESTED";
		this.uname = uname;
		this.pname = pname;
	}

	public int compareTo(Job job){
		if(this.priority>(job.priority))
			return 1;
		else if(this.priority<(job.priority))
			return -1;
		else
			return 0;
	}
	
	public String toString(){
		return "Job{user=’"+this.uname+"’, project=’"+this.pname+"’, jobstatus="+this.status+", execution_time="+this.runtime+", end_time="+this.end_time+", name=’"+this.name+"’}";
	}
}
