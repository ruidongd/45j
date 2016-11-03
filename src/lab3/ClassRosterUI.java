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
		reader.close();
		return command;

	}
	public static String getCourseCode(){
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Course Code: ");
		String courseCode = reader.nextLine();
		reader.close();
		return courseCode;
	}
	public static Course getCourse()
	{
		Scanner reader = new Scanner(System.in);
		Course c = new Course();
		String coursecode = getCourseCode();
		System.out.println("Enter Course Name: ");
		String coursename = reader.nextLine();
		c.setCourseCode(coursecode);
		c.setCourseName(coursename);
		reader.close();
		return c;
	}
	public static int getStudentID()
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter StudentID: ");
		int studentID = reader.nextInt();
		reader.close();
		return studentID;
	}
	public static Student getStudent()
	{
		Scanner reader = new Scanner(System.in);
		int StudentID = getStudentID();
		System.out.println("Enter Last Name: ");
		String lastname = reader.nextLine();
		System.out.println("Enter First Name: ");
		String firstname = reader.nextLine();
		Student s = new Student();
		s.setFirstName(firstname);
		s.setLastName(lastname);
		s.setID(StudentID);
		reader.close();
		return s;
	}

}
