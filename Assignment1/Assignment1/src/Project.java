
public class Project {

	public String name;
	public int budget;
	public int priority;
	public Project(String name,int priority, int budget){
		this.name = name;
		this.budget = budget;
		this.priority = priority;
	}
	public String toString(){
		return Integer.toString(this.priority);
	}
}
