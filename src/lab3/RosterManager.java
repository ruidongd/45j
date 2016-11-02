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

	public void addCourse(Course c) throws CourseLimitException, DuplicateCourseException
	{
		if (total_courses == 10)
			throw new CourseLimitException();
		else if (courses.contains(c))
			throw new DuplicateCourseException();
		courses[total_courses++] = c;
	}

	public void deleteCourse(String courseCode) throws CourseNotFoundException
	{
		boolean found = false;
		int index = 0;
		for (; index < total_courses; index++){
			if(courses[index].getCourseCode() == courseCode)
			{
				found = true;
				break; // will not increase index by index++
			}
		}
		if(found)
			// E.g We have [c1, c2, c2], total_number = 3
			// If we delete the second one, the i will be 1 and the third one should be switched to be the second.
			// course[1] = course[2]; Switch
			// course[2] = null; total_number becomes 2
			if(index < 9)
				courses[index] = courses[index+1]; // index+1 = 10 will be out of index when index == 9
			courses[--total_courses] = null;
		else
			throw new CourseNotFoundException();
	}

	public void addStudent(Student s, String courseCode) throws StudentLimitException,CourseNotFoundException,DuplicateStudentException
	{
		for (int i = 0; i < total_courses;i++){
			if (courses[i].getCourseCode().equals(courseCode)){
				if(!courses[i].enrolled_students.contains(s))
					courses[i].addStudent(s);
				break;
			}
		}
		throw new CourseNotFoundException();

	}


	public void deleteStudent(int studentId, String courseCode) throws StudentNotFoundException,CourseNotFoundException
	{
		for (int i = 0; i < total_courses;i++){
			if(courses[i].getCourseCode().equals(courseCode)){
				courses[i].removeStudent(studentId);
				break;
			}
		}
		throw new CourseNotFoundException();
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
			try{
				command = ClassRosterUI.getCommand().toUpperCase();
				if(command == "Q"){
					quit = true;
				}
				else if(command.equals("AC")){
					addCourse(ClassRosterUI.getCourse());
				}
				else if(command.equals("DC")){
					deleteCourse(ClassRosterUI.getCourseCode());
				}
				else if (command.equals("AS")){
					addStudent(ClassRosterUI.getStudent(), ClassRosterUI.getCourseCode());
				}
				else if (command.equals("DS")){
					deleteStudent(ClassRosterUI.getStudentID(), ClassRosterUI.getCourseCode());
				}
				else if (command.equals("P")){
					// need implemented
				}
			}
			catch(CourseLimitException)
				System.out.println("Course limit has been met! Cannot add course anymore!");
			catch(DuplicateCourseException)
				System.out.println("Course has been added! Cannot add again!");
			catch(CourseNotFoundException)
				System.out.println("Course not found! You haven't added it!");
			catch(StudentLimitException)
				System.out.println("Student limit has been met! Cannot add student into this course anymore!");
			catch(DuplicateStudentException)
				System.out.println("Student has been enrolled into this course! Cannot enroll again!");
			catch(StudentNotFoundException)
				System.out.println("Student not found! You haven't add it!");

		}
		System.out.println("Program Finished!");

		}


}
