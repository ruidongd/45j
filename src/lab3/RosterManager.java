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
	public boolean containsCourse(Course c){
		for (int i = 0; i < total_courses;i++){
			if(courses[i].getCourseCode() == c.getCourseCode())
				return true;
		}
		return false;
	}

	public void addCourse(Course c) throws CourseLimitException, DuplicateCourseException
	{
		if (total_courses == 10)
			throw new CourseLimitException();
		else if (containsCourse(c))
			throw new DuplicateCourseException();
		courses[total_courses++] = c;
	}

	public void deleteCourse(String courseCode) throws CourseNotFoundException
	{
		int index = 0;
		for (; index < total_courses; index++){
			if(courses[index].getCourseCode() == courseCode)
				break; // will not increase index by index++
		}
		// E.g We have [c1, c2, c2], total_number = 3
		// If we delete the first one, the i will be 0 and the third one should be switched to be the first to make sure our index of 1 and index of 2 compose a streak.
		// course[0] = course[2]; Switch
		// course[2] = null
		// total_number = 2, [c3, c2]
		if(index == total_courses) // no execution of break -> not found
			throw new CourseNotFoundException();
		else if (index < 9) // The course is not listed on the last, which means it needs a switch. Index+1 = 10 will be out of index when index == 9(Last one no switch)
			courses[index] = courses[--total_courses];
		courses[total_courses] = null;
	}

	public void addStudent(Student s, String courseCode) throws StudentLimitException,CourseNotFoundException,DuplicateStudentException
	{
		for (int i = 0; i < total_courses;i++){
			if (courses[i].getCourseCode().equals(courseCode)){
				if(!courses[i].containsStudent(s))
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
		System.out.println("********************");
		for (Course c: courses){
			System.out.println(c.getCourseCode()+": "+c.getCourseName());
			System.out.println("Enrolled: "+c.getEnrollment());
			for (Student s: c.getEnrolled()){
				System.out.println("           "+s.getID()+" | "+s.getLastName()+", "+s.getFirstName());

			}
		}
		System.out.println("********************");

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
					printRoster();
				}
			}
			catch(CourseLimitException e){
				System.out.println("Course limit has been met! Cannot add course anymore!");
			}
			catch(DuplicateCourseException e){
				System.out.println("Course has been added! Cannot add again!");
			}
			catch(CourseNotFoundException e){
				System.out.println("Course not found! You haven't added it!");
			}
			catch(StudentLimitException e){
				System.out.println("Student limit has been met! Cannot add student into this course anymore!");
			}
			catch(DuplicateStudentException e){
				System.out.println("Student has been enrolled into this course! Cannot enroll again!");
			}
			catch(StudentNotFoundException e){
				System.out.println("Student not found! You haven't add it!");
			}

		}
		System.out.println("Program Finished!");

		}


}
