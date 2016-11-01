package lab3;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ClassRosterUI {
	private static RosterManager rm;
	
	public ClassRosterUI(){
		rm = new RosterManager();
	}
	
	public static void printMenu()
	{
		System.out.println("Select an action based on the following menu:\n----------\nac: Add Course\ndc: Drop Course\nas: Add Student\nds: Drop Student\np: Print ClassRoster\n"
				+ "q: Quit Program\n----------\n");
	}
	
	private static void addcourse(String coursecode,String name) throws CourseLimitException{
		Course c = new Course();
		c.setCourseCode(coursecode);
		c.setCourseName(name);
		rm.addCourse(c);	
	}
	
	private static void deletecourse(String coursecode) throws CourseNotFoundException{
		rm.deleteCourse(coursecode);
	}
	
	private static void addstudent(String coursecode, String firstname,String lastname,int studentid)throws CourseNotFoundException,StudentLimitException{
		Student s = new Student();
		s.setFirstName(firstname);
		s.setLastName(lastname);
		s.setID(studentid);
		rm.addStudent(s, coursecode);
	}
	
	
	private static void deletestudent(String coursecode,int studentid) throws StudentNotFoundException, CourseNotFoundException{
		rm.deleteStudent(studentid, coursecode);
		
	}
	public static String getCommand()
	{
		String command;
		List<String> command_list = Arrays.asList("ac", "dc", "as", "ds", "p", "q");
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Command: ");
		command  = reader.nextLine();
		
		if(!command_list.contains(command))
		{
				System.out.println("Invalid command! Please re-enter the command!");
				command = reader.nextLine();
		}
		else if(command.equals("ac")){
				System.out.println("Enter Course Code: ");
				String coursecode = reader.nextLine();
				System.out.println("Enter Course Name: ");
				String coursename = reader.nextLine();
				try {
					addcourse(coursecode,coursename);
				} catch (CourseLimitException e) {
					e.printStackTrace();
				}
				
		}
		else if(command.equals("dc")){
				System.out.println("Enter Course Code: ");
				String removecourse = reader.nextLine();
				try{
					deletecourse(removecourse);
				}
				catch(CourseNotFoundException e){
					e.printStackTrace();
				}
				
		}
		else if (command.equals("as")){
				System.out.println("Enter Course Code: ");
				String ascoursecode = reader.nextLine();
				System.out.println("Enter Course Name: ");
				String ascoursename = reader.nextLine();
				System.out.println("Enter StudentID: ");
				int studentid = reader.nextInt();
				System.out.println("Enter Last Name: ");
				String lastname = reader.nextLine();
				System.out.println("Enter First Name: ");
				String firstname = reader.nextLine();
				try {
					addstudent(ascoursecode,firstname,lastname,studentid);
				}
				catch(CourseNotFoundException e){
					e.printStackTrace();
					}
				catch(StudentLimitException e){
					e.printStackTrace();
				}
				
				
		}
		else if (command.equals("ds")){
				System.out.println("Enter Course Code: ");
				String dscoursecode = reader.nextLine();
				System.out.println("Enter StudentID: ");
				int dsstudentid = reader.nextInt();
				try {
					deletestudent(dscoursecode,dsstudentid);
				}
				catch(CourseNotFoundException e){
					e.printStackTrace();
					}
				catch(StudentNotFoundException e){
					e.printStackTrace();
				}
				
		}
		else if (command.equals("p")){
				
		}
		
		reader.close();
		return command;
		
	}

}
