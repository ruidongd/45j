package Lab3;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ClassRosterUI {

    private static Scanner reader = new Scanner(System.in);

	public ClassRosterUI(){
	}
	public static void closereader(){
		reader.close();
	}
	public static void printMenu()
	{
		System.out.print("Select an action based on the following menu:\n----------\nac: Add Course\ndc: Drop Course\nas: Add Student\nds: Drop Student\np:  Print ClassRoster\n"
				+ "q:  Quit Program\n----------\n");
	}

	public static String getCommand()
	{
		String command;
		List<String> command_list = Arrays.asList("AC", "DC", "AS", "DS", "P", "Q");

		System.out.print("Enter Command: ");
		command  = reader.nextLine().toUpperCase();
		if(!command_list.contains(command))
		{
				System.out.print("Invalid command! Please re-enter the command!");
				command = reader.nextLine().toUpperCase();
		}
		return command;

	}
	public static String getCourseCode(){

		System.out.print("Enter Course Code: ");
		String courseCode = reader.nextLine();
		return courseCode;
	}
	public static Course getCourse()
	{
		Course c = new Course();
		String coursecode = getCourseCode();
		System.out.print("Enter Course Name: ");
		String coursename = reader.nextLine();
		c.setCourseCode(coursecode);
		c.setCourseName(coursename);
		return c;
	}
	public static Course getCourse(String courseCode)
	{
		Course c = new Course();
		System.out.print("Enter Course Name: ");
		String coursename = reader.nextLine();
		c.setCourseCode(courseCode);
		c.setCourseName(coursename);
		return c;
	}
	public static int getStudentID()
	{

		System.out.print("Enter StudentID: ");
		int studentID = Integer.parseInt(reader.nextLine());
		return studentID;
	}
	public static Student getStudent()
	{

		int StudentID = getStudentID();
		System.out.print("Enter Last Name: ");
		String lastname = reader.nextLine();
		System.out.print("Enter First Name: ");
		String firstname = reader.nextLine();
		Student s = new Student();
		s.setFirstName(firstname);
		s.setLastName(lastname);
		s.setID(StudentID);

		return s;
	}
	
	public static Student getStudent(int id)
	{
		System.out.print("Enter Last Name: ");
		String lastname = reader.nextLine();
		System.out.print("Enter First Name: ");
		String firstname = reader.nextLine();
		Student s = new Student();
		s.setFirstName(firstname);
		s.setLastName(lastname);
		s.setID(id);
		return s;
	}
}
