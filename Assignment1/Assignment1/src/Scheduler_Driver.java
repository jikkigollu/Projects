import java.io.*;
import java.util.LinkedList;;

public class Scheduler_Driver extends Thread implements SchedulerInterface {
	
	public Trie<User> ulist = new Trie<User>();
	public Trie<Project> plist = new Trie<Project>();
	public MaxHeap<Job> jlist = new MaxHeap<Job>();
	public Trie<Job> tlist = new Trie<Job>();
	public RBTree<String, Job> jplist = new RBTree<String, Job>();
	//public RBTree<String, Project> prlist = new RBTree<String, Project>();
	public int count = 0;
	public int global_time = 0;
	LinkedList<Job> clist = new LinkedList<Job>();
	LinkedList<Job> nlist = new LinkedList<Job>();
	public int check = 0;
	public RedBlackNode<String, Job> flag = null;

    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();

        File file;
       // if (args.length == 0) {
           // URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File("inp");
        

        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {

       // URL url = Scheduler_Driver.class.getResource("INP");
        file = new File("inp");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    //System.out.println("kfgbkrgkherkerkjrbtkjr");
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();

    }




    @Override
    public void run() {
        // till there are JOBS
        schedule();
    }


    @Override
    public void run_to_completion() {
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+count);
    	Job j;
    	Project p;
    	int x = 0;
    	//int temp = 0;
    	j = jlist.extractMax();
    	while(j!=null){
    		
    		
        	p = plist.search(j.pname).value;
        	System.out.println("Executing: "+j.name+" from: "+p.name);
        	
    	if(p.budget>=j.runtime){
    		x = j.runtime;
    		p.budget = p.budget - j.runtime;
    		System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    		j.status = "COMPLETED";
    		j.end_time = global_time+x;
    		clist.add(j);
    		//temp = 1;
    		System.out.println("System execution completed");
    		j = jlist.extractMax();
    		count--;
    		if(j!=null){
    			System.out.println("Running code");
    			System.out.println("Remaining jobs: "+count);
    		}
    	}
    	else{
    		count--;
    		System.out.println("Un-sufficient budget.");
    		j = jlist.extractMax();
    		//temp = 0;
    	}
    	
    	
    	global_time += x;
    	}
    	
    	
    	
    }

    @Override
    public void handle_project(String[] cmd) {

    	System.out.println("Creating project");
    	Project project = new Project(cmd[1], Integer.valueOf(cmd[2]), Integer.valueOf(cmd[3]));
    	plist.insert(cmd[1], project);
    	//prlist.insert(cmd[1], project);
    	jplist.insert(cmd[1], null);
    }

    @Override
    public void handle_job(String[] cmd) {

    	System.out.println("Creating job");
    	
    	//System.out.println()
    	if(plist.search(cmd[2]) == null)
    			System.out.println("No such project exists. "+cmd[2]);
    	else if(ulist.search(cmd[3]) == null)
    		System.out.println("No such user exists: "+cmd[3]);
    	else{
    		Project p = plist.search(cmd[2]).value;
        	User u = ulist.search(cmd[3]).value;
        	int runtime = Integer.valueOf(cmd[4]);
        	Job job =new Job(cmd[1], p.priority, runtime,p.name,u.name);
        	job.pname = cmd[2];
    			count++;
    			jlist.insert(job);
    			tlist.insert(job.name,job);
    			jplist.search(cmd[2]).list.add(job);
    			nlist.add(job);
    	}
    	
    }

    @Override
    public void handle_user(String name) {

    	System.out.println("Creating user");
    	User user = new User(name);
    	ulist.insert(name, user);
    }

    @Override
    public void handle_query(String key) {

    	System.out.println("Querying");
    	
    	System.out.print(key+": ");
    	if(tlist.search(key) == null)
    		System.out.println("NO SUCH JOB");
    	else{
    		if(tlist.search(key).value.status.equals("REQUESTED"))
    			System.out.println("NOT FINISHED");
    		else
    			System.out.println("COMPLETED");
    	}
    }

    @Override
    public void handle_empty_line() {

    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+count);
    	Job j;
    	Project p;
    	int x = 0;
    	int temp = 0;
    	while(temp == 0){
    		
    		j = jlist.extractMax();
        	p = plist.search(j.pname).value;
        	System.out.println("Executing: "+j.name+" from: "+p.name);
        	
    	if(p.budget>=j.runtime){
    		x = j.runtime;
    		p.budget = p.budget - j.runtime;
    		System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    		j.status = "COMPLETED";
    		j.end_time = global_time+x;
    		temp = 1;
    		clist.add(j);
    	}
    	else{
    		System.out.println("Un-sufficient budget.");
    		temp = 0;
    	}
    	count--;
    	}
    	
    	global_time += x;
    	System.out.println("Execution cycle completed");
    	/*if(check == 1){
    		
    		int k = 1;
    		while(k<flag.list.size()){
    			jlist.delete(flag.list.get(k));
    			k++;
    		}
    		check = 0;
    	}*/
    }

    
    /*public void handle_empty_line(){
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+count);
    	Job j;
    	Project p;
    	int x = 0;
    	int temp = 0;
    	j = jlist.extractMax();
    	Node<Job> thead = jlist.root;
    	p = plist.search(j.pname).value;
    	System.out.println("Executing: "+j.name+" from: "+p.name);
    	if(p.budget>j.runtime){
    		x = j.runtime;
    		p.budget = p.budget - j.runtime;
    		System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    		j.status = "COMPLETED";
    		j.end_time = global_time+x;
    		temp = 1;
    		clist.add(j);
    	}
    	else{
    		while(temp == 0){
    			if(p.budget>j.runtime){
    	    		x = j.runtime;
    	    		p.budget = p.budget - j.runtime;
    	    		System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    	    		j.status = "COMPLETED";
    	    		j.end_time = global_time+x;
    	    		jlist.delete(j);
    	    		temp = 1;
    	    		clist.add(j);
    	    	}
    			else{
    				if(j.check==0)
    					System.out.println("Un-sufficient budget.");
    				if(thead.left.data.priority>thead.right.data.priority){
    					thead = thead.left;
    					j = thead.left.data;
    					p = plist.search(j.pname).value;
    				}
    				else
    			}
    		}
    	}
    }
    
    
    */
    
    @Override
    public void handle_add(String[] cmd) {

    	System.out.println("ADDING Budget");
    	Project p = plist.search(cmd[1]).value;
    	p.budget += Integer.valueOf(cmd[2]);
    	RedBlackNode<String, Job> head = jplist.search(cmd[1]);
    	
    	
    	int i = head.list.size()-1;
    	while(i>0){
    		if(head.list.get(i).status.equals("REQUESTED")){
    			jlist.add(head.list.get(i));
    			count++;
    		}
    		i--;
    	}
    	
    	check = 1;
    	flag = head;
    }

    
    
    
    
    
    
    
    @Override
    public void print_stats() {

    	System.out.println("--------------STATS---------------");
    	System.out.println("Total jobs done: "+clist.size());
    	int k = 0;
    	while(k<clist.size()){
    		System.out.println(clist.get(k));
    		k++;
    	}
    	   	
    	System.out.println("------------------------");
    	System.out.println("Unfinished jobs:");
    	int i = 0;
    	while(i<nlist.size())
    	{
    		if(nlist.get(i).status.equals("REQUESTED"))
    			System.out.println(nlist.get(i));
    		i++;
    	}
    	System.out.println("Total unfinished jobs: "+(nlist.size()-clist.size()));
    	System.out.println("--------------STATS DONE---------------");
    }

    @Override
    public void schedule() {

    }
}