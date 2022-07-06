public interface Student_ { 
   public String fname();      // Return student’s first name 
   public String lname();      // Return student’s last name 
   public String hostel();     // Return student’s hostel name 
   public String department(); // Return student’s department name 
   public String cgpa();       // Return student’s cgpa 
} 
class Student implements Student_
{
	public String fname,lname,hostel,department,cgpa;
	public Student(String fname,String lname,String hostel,String department,String cgpa)
	{
		this.fname=fname;
		this.lname=lname;
		this.hostel=hostel;
		this.department=department;
		this.cgpa=cgpa;
	}
	public String fname()
	{
		return fname;
	}
	public String lname()
	{
		return lname;
	}
	public String hostel()
	{
		return hostel;
	}
	public String department()
	{
		return department;
	}
	public String cgpa()
	{
		return cgpa;
	}
	public String toString() {
		return fname+" "+lname;
	}
}