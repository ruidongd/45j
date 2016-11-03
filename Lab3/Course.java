package Lab3;

public class Course {
	private String courseCode;
	private String courseName;
	private int enrollment;
	private Student[] enrolled_students;

	public Course()
	{
		courseCode = "";
		courseName = "";
		enrollment = 0;
		enrolled_students = new Student[50];
	}

	public boolean containsStudent(Student s){
		for (int i = 0; i < enrollment;i++){
			if (enrolled_students[i].getID() == s.getID() ){
				return true;
			}
		}
		return false;
	}
	public void addStudent(Student s) throws StudentLimitException,DuplicateStudentException
	{
		if (enrollment == 50)
			throw new StudentLimitException();
		else if (containsStudent(s))
			throw new DuplicateStudentException();
		else
			enrolled_students[enrollment++] = s;
	}

	public void removeStudent(int studentID) throws StudentNotFoundException, EmptyStudentListException
	{
		if (enrollment == 0){
			throw new EmptyStudentListException();
		}
		// Same logic with removeCourse
		int index = 0;
		for(; index < enrollment; index++)
		{
			if(enrolled_students[index].getID() == studentID)
				break;
		}
		if(index == enrollment)
			throw new StudentNotFoundException();
		else if(index < 49)
			enrolled_students[index] = enrolled_students[--enrollment];
		enrolled_students[enrollment] = null;
		
	}


	public void setCourseCode(String code)
	{
		courseCode = code;
	}
	public void setCourseName(String name)
	{
		courseName = name;
	}
	public void setEnrollment(int e)
	{
		enrollment = e;
	}
	public String getCourseCode()
	{
		return courseCode;
	}
	public String getCourseName()
	{
		return courseName;
	}
	public int getEnrollment()
	{
		return enrollment;
	}
	public Student[] getEnrolled()
	{
		return enrolled_students;
	}
}
