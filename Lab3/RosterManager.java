package Lab3;
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
	public boolean containsCourse(String code)
	{
		for (int i = 0; i < total_courses;i++){
			if(courses[i].getCourseCode().toLowerCase().equals(code.toLowerCase()))
				return true;
		}
		return false;
		
	}
	
	public void addCourse(Course c) throws DuplicateCourseException, CourseLimitException
	{
		if(total_courses == 10)
			throw new CourseLimitException();
		if(containsCourse(c.getCourseCode()))
			throw new DuplicateCourseException();
		courses[total_courses++] = c;
	}

	public void deleteCourse(String courseCode) throws EmptyCourseListException, CourseNotFoundException
	{
		if(total_courses == 0)
			throw new EmptyCourseListException();
		int index = 0;
		for (; index < total_courses; index++){
			if(courses[index].getCourseCode().toLowerCase() .equals(courseCode.toLowerCase()))
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

	public void addStudent(Student s, String courseCode) throws EmptyCourseListException, StudentLimitException, DuplicateStudentException
	{
		if(total_courses == 0)
			throw new EmptyCourseListException();
		int i = 0;
		for (; i < total_courses;i++){
			if (courses[i].getCourseCode().toLowerCase().equals(courseCode.toLowerCase()))
				break;
		}
		if(courses[i].getEnrollment() >= 50)
			throw new StudentLimitException();
		if(courses[i].containsStudent(s.getID()))
			throw new DuplicateStudentException();
		else
			courses[i].addStudent(s);
	}


	public void deleteStudent(int studentId, String courseCode) throws EmptyStudentListException, CourseNotFoundException, StudentNotFoundException
	{
		int i = 0;
		for (; i < total_courses;i++){
			if(courses[i].getCourseCode().toLowerCase().equals(courseCode.toLowerCase()))
				break;
		}
		if(i == total_courses)
			throw new CourseNotFoundException();
		courses[i].removeStudent(studentId);
	}

	public void printRoster()
	{
		System.out.println("********************");
		for (Course c: courses){
			if ( c != null){
				System.out.println(c.getCourseCode()+": "+c.getCourseName());
				System.out.println("Enrolled: "+c.getEnrollment());
				for (Student s: c.getEnrolled()){
					if (s != null)
						System.out.println("          "+s.getID()+" | "+s.getLastName()+", "+s.getFirstName());
				}
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
				ClassRosterUI.printMenu();
				command = ClassRosterUI.getCommand();
				
				if(command.equals("Q")){
					quit = true;
				}
				
				else if(command.equals("AC")){
					if(total_courses == 10)
						throw new CourseLimitException();
					String courseCode = ClassRosterUI.getCourseCode();
					if(containsCourse(courseCode))
						throw new DuplicateCourseException();
					addCourse(ClassRosterUI.getCourse(courseCode));
				}
				
				else if(command.equals("DC")){
					if(total_courses == 0)
						throw new EmptyCourseListException();
					deleteCourse(ClassRosterUI.getCourseCode());
				}
				
				else if (command.equals("AS")){
					if(total_courses == 0)
						throw new EmptyCourseListException();
					String courseCode =  ClassRosterUI.getCourseCode();
					if(!containsCourse(courseCode))
						throw new CourseNotFoundException();
					for(int index = 0; index < total_courses; index++)
					{
						if(courses[index].getCourseCode().equals(courseCode) && courses[index].getEnrollment() == 50)
							throw new StudentLimitException();
					}
					int id = ClassRosterUI.getStudentID();
					for(int i = 0; i < total_courses; i++)
					{
						if(courses[i].getCourseCode().equals(courseCode) && courses[i].containsStudent(id))
							throw new DuplicateStudentException();
					}
					addStudent(ClassRosterUI.getStudent(id), courseCode);
				}
				
				else if (command.equals("DS")){
					if(total_courses == 0)
						throw new EmptyCourseListException();
					String courseCode =  ClassRosterUI.getCourseCode();
					if(containsCourse(courseCode)){
						for(int index = 0; index < total_courses; index++)
						{
							if(courses[index].getCourseCode().equals(courseCode) && courses[index].getEnrollment() == 0)
									throw new EmptyStudentListException();
						}
						int id = ClassRosterUI.getStudentID();
						for(int index = 0; index < total_courses; index++)
						{
							if(courses[index].getCourseCode().equals(courseCode) && courses[index].containsStudent(id))
								deleteStudent(ClassRosterUI.getStudentID(), courseCode);
						}
						throw new StudentNotFoundException();
		
					}
					
				}
				
				else if (command.equals("P"))
					printRoster();
			}
			catch(CourseLimitException e){
				System.out.println("ERROR: Course limit has been met.");
			}
			catch(DuplicateCourseException e){
				System.out.println("ERROR: Course has been added.");
			}
			catch(CourseNotFoundException e){
				System.out.println("ERROR: Could not find course.");
			}
			catch(StudentLimitException e){
				System.out.println("ERROR: Student limit has been met.");
			}
			catch(DuplicateStudentException e){
				System.out.println("ERROR: Student has been enrolled into this course.");
			}
			catch(StudentNotFoundException e){
				System.out.println("ERROR: Student could not be found.");
			}
			catch(EmptyCourseListException e){
				System.out.println("ERRPR: Courses list is empty.");
			}
			catch(EmptyStudentListException e){
				System.out.println("ERROR: Students list is empty.");
			}

		}
		System.out.println("Program Finished!");
		ClassRosterUI.closereader();

		}


}
