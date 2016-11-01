package lab3;
public class RosterManager {
	//an array of courses
	private Course[] courses;
	//Total number of current courses.
	private int total_courses;

	public RosterManager()
	{
		courses = new Course[10];
		total_courses = 0;
	}
	
	public void addCourse(Course c) throws CourseLimitException
	{
		if (total_courses == 10){
			throw new CourseLimitException();
		}
		courses[total_courses++] = c;		
	}

	public void deleteCourse(String courseCode) throws CourseNotFoundException
	{
		boolean found = false;
		for (int i = 0; i < total_courses-1; i++){
			if(courses[i].getCourseCode() == courseCode){
				found = true;
			}
			if(found == true){
				courses[i] = courses[i++];
			}
		}
		if (found == true){
			courses[total_courses] = null;
		}
		else{
			throw new CourseNotFoundException();
		}
	}

	
	public void addStudent(Student s, String courseCode) throws StudentLimitException,CourseNotFoundException
	{
		for (int i = 0; i < total_courses;i++){
			if (courses[i].getCourseCode().equals(courseCode)){
				courses[i].addStudent(s);
				break;
			}
		}
		
	}
	

	public void deleteStudent(int studentId, String courseCode) throws StudentNotFoundException,CourseNotFoundException
	{
		for (int i = 0; i < total_courses;i++){
			if(courses[i].getCourseCode().equals(courseCode)){
				courses[i].removetudent(studentId);
				
				break;
			}
		}
		
	}


	public void printRoster()
	{
		System.out.println("");
	}
	
	public void run()
	{
		System.out.println("Welcome to Class Roster Manager!");
		boolean quit = false;
		String command;
		while(!quit)
		{
			command = ClassRosterUI.getCommand().toUpperCase();
			if(command == "Q"){
					quit = true;
			}
		}
		System.out.println("Program Finished!");

		}
	
	
}
