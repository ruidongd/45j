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

	public boolean containsStudent(int id){
		for (int i = 0; i < enrollment;i++){
			if (enrolled_students[i].getID() == id)
				return true;
		}
		return false;
	}
	public void addStudent(Student s)
	{
			int i = 0;
			for (; i < enrollment; i++){
				boolean compareLastName = enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) > 0;

				boolean compareFirstName = ( enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) == 0)
						&&  enrolled_students[i].getFirstName().toLowerCase().compareTo(s.getFirstName().toLowerCase()) > 0;

				boolean compareID = ( enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) == 0)
						&&  enrolled_students[i].getFirstName().toLowerCase().compareTo(s.getFirstName().toLowerCase()) == 0
						&&  enrolled_students[i].getID() > s.getID();

				if(compareLastName || compareFirstName || compareID)
					break;
			}
			for(int j = enrollment; j > i; --j)
			{
				enrolled_students[j] = enrolled_students[j-1];
			}
			enrolled_students[i] = s;
			++enrollment;
	}

	public void removeStudent(int studentID) throws EmptyStudentListException, StudentNotFoundException
	{
		// Same logic with removeCourse
		if(enrollment == 0)
			throw new EmptyStudentListException();
		int index = 0;
		for(; index < enrollment; index++)
		{
			if(enrolled_students[index].getID() == studentID)
				break;
		}
		if(index == enrollment)
			throw new StudentNotFoundException();
		if(index < 49)
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
