package lab3;

public class Course {
	private String courseCode;
	private String courseName;
	private int enrollment;
	private Student[] enrolled_students;

	public Course()
	{
		courseCode = null;
		courseName = null;
		enrollment = 0;
		enrolled_students = new Student[50];
	}

	public void addStudent(Student s) throws StudentLimitException
	{
		if (enrollment == 50){
			throw new StudentLimitException();
		}
		else {
			enrolled_students[enrollment++] = s;
		}

	}

	public void removetudent(int studentID) throws StudentNotFoundException
	{

		boolean found = false;
		for(int index = 0; index < enrollment-1; index++)
		{
			if (enrolled_students[index].getID() == studentID){
				found = true;
			}
			if (found == true){
				enrolled_students[index] = enrolled_students[index++];
			}
		}
		if(found == true){
			enrolled_students[enrollment] = null;
		}
		else{
			throw new StudentNotFoundException();
		}	
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
