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

<<<<<<< HEAD
	public boolean containsStudent(int id){
=======
	//return true if students enrolled in course
	public boolean containsStudent(Student s){
>>>>>>> jzeng5/master
		for (int i = 0; i < enrollment;i++){
			if (enrolled_students[i].getID() == id)
				return true;
		}
		return false;
	}
<<<<<<< HEAD
	public void addStudent(Student s)
=======
	//add student to the course with lexicographical order
	public void addStudent(Student s) throws StudentLimitException,DuplicateStudentException
>>>>>>> jzeng5/master
	{
			int i = 0;
			for (; i < enrollment; i++){
<<<<<<< HEAD
				boolean compareLastName = enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) > 0;

				boolean compareFirstName = ( enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) == 0)
						&&  enrolled_students[i].getFirstName().toLowerCase().compareTo(s.getFirstName().toLowerCase()) > 0;

				boolean compareID = ( enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) == 0)
						&&  enrolled_students[i].getFirstName().toLowerCase().compareTo(s.getFirstName().toLowerCase()) == 0
						&&  enrolled_students[i].getID() > s.getID();

=======

				//compare last name
				boolean compareLastName = enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) > 0;
				
				//if last name is the same,compare first name
				boolean compareFirstName = ( enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) == 0) 
						&&  enrolled_students[i].getFirstName().toLowerCase().compareTo(s.getFirstName().toLowerCase()) > 0;
				
				//if last name and first name are the same, compare student id
				boolean compareID = ( enrolled_students[i].getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase()) == 0) 
						&&  enrolled_students[i].getFirstName().toLowerCase().compareTo(s.getFirstName().toLowerCase()) == 0 
						&&  enrolled_students[i].getID() > s.getID();		
>>>>>>> jzeng5/master
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

<<<<<<< HEAD
	public void removeStudent(int studentID) throws EmptyStudentListException, StudentNotFoundException
=======
	//remove student in the course
	public void removeStudent(int studentID) throws StudentNotFoundException, EmptyStudentListException
>>>>>>> jzeng5/master
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
