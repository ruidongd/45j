package Lab3;

public class Student {
	private int studentID;
	private String firstName;
	private String lastName;

	public Student() {
		studentID = 0;
		firstName = null;
		lastName = null;
	}

	public void setID(int id)
	{
		studentID = id;
	}

	public void setFirstName(String first)
	{
		firstName = first;
	}

	public void setLastName(String last)
	{
		lastName = last;
	}
	public int getID()
	{
		return studentID;
	}

	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	
	
}
