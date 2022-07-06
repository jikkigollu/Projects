import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
//import java.net.URL;

public class RedBlackDriverCode {
    public static void main(String[] args) throws IOException {
        RBTree<String, Person> rbt = new RBTree();
        File file;
        
            file = new File("inp_rbtree");
       

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
                case "INSERT":
                    String contact = br.readLine();
                    Person person = new Person(contact.split(",")[0].trim(), contact.split(",")[1].trim());
                    System.out.println("Inserting: " + person.getName());
                    rbt.insert(person.getName(), person);
                    break;
                case "SEARCH":
                    String search_key = br.readLine();
                    System.out.println("Searching for: " + search_key);
                    RedBlackNode search = rbt.search(search_key);
                    if (search != null && search.getValues() != null) {
                    	//System.out.println(search.list.size()+"mainmain");
                        for (Object person1 : search.getValues()) {
                            System.out.println(person1);
                        }
                    } else {
                        System.out.println("Not Found");
                    }

                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }
        /*RedBlackNode<String, Person> r = rbt.root;
        System.out.println(rbt.root.left.key+rbt.root.right.key);
        RedBlackNode<String, Person> p = null;
        Queue<RedBlackNode<String, Person>> q = new LinkedList<RedBlackNode<String, Person>>();
        q.add(r);
        while(q.size()!=0){
        	p = q.peek();
        	if(p.left!=null)
        		q.add(p.left);
        	if(p.right!=null)
        		q.add(p.right);
        	System.out.println(p.key+"hello");
        	q.remove();
        }*/
    }
}