public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
    	this.name = trim;
    	this.marks = parseInt;
    }


    public int compareTo(Student student) {
    	if(this.marks==student.marks)
    		return 0;
    	else if(this.marks>student.marks)
    		return 1;
    	else
    		return -1;
    }

    public String getName() {
        return name;
    }
    
    public String toString(){
    	return "Student{name='"+this.name+"', marks="+this.marks+"}";
    }
}