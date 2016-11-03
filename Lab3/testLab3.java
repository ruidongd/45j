package Lab3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testLab3 {
	
	private RosterManager rm;
//	private Course course;
//	private String coursecode;
	
	
	//construct object
	@Before
	public void init(){
		rm =  new RosterManager();	
//		course = new Course();
//		coursecode = "ICS32";
//		course.setCourseCode(coursecode);
//		course.setCourseName("Introduction to Advanced Programming");
	}
	
	//Normal case
	@Test
	public void testaddcourse1() throws CourseLimitException, DuplicateCourseException {
		//Normal Case
		Course c1 = new Course();
		c1.setCourseCode("ICS31");
		c1.setCourseName("Introduction to Python");
		rm.addCourse(c1);
		assertTrue("Courses[] contains ICS31",rm.containsCourse(c1));
	}
	//Normal case
	@Test
	public void testaddcourse2()throws  CourseLimitException, DuplicateCourseException {
		Course c2 = new Course();
		c2.setCourseCode("ICS45C");
		c2.setCourseName("Praogramming in C/C++");
		rm.addCourse(c2);
		assertTrue("Courses[] contains ICS45C",rm.containsCourse(c2));
	}
	//Normal case
	@Test
	public void testaddcourse3()throws  CourseLimitException, DuplicateCourseException {
		Course c3 = new Course();
		c3.setCourseCode("ICS46");
		c3.setCourseName("Data Structure Implementation and Analysis");
		rm.addCourse(c3);
		assertTrue("Courses[] contains ICS46",rm.containsCourse(c3));
	}
	
	//add 9 courses, boundary test case
	@Test 
	public void testboundaryaddcourse()throws CourseLimitException, DuplicateCourseException {
		for(int i = 0; i <=	9; ++i )
		{
			Course c = new Course();
			c.setCourseCode(Integer.toString(i));
			c.setCourseName(" ");
			rm.addCourse(c);
		}
		
	}
	//test delete course,normal case
	@Test
	public void testDeleteCourses() throws CourseLimitException, DuplicateCourseException, CourseNotFoundException, EmptyCourseListException{
		Course c1 = new Course();
		c1.setCourseCode("ICS31");
		c1.setCourseName("Introduction to Python");
		Course c2 = new Course();
		c2.setCourseCode("ICS45C");
		c2.setCourseName("Praogramming in C/C++");
		Course c3 = new Course();
		c3.setCourseCode("ICS46");
		c3.setCourseName("Data Structure Implementation and Analysis");
		rm.addCourse(c1);
		rm.addCourse(c2);
		rm.addCourse(c3);
		assertTrue("Courses[] contains c1",rm.containsCourse(c1));
		rm.deleteCourse("ICS31");
		assertFalse("Courses[] does not contains c1",rm.containsCourse(c1));
		assertTrue("Courses[] contains c2",rm.containsCourse(c2));
		assertTrue("Courses[] contains c3",rm.containsCourse(c3));
	}
	
	//test delete course, boundary case
	@Test
	public void testDeleteCoursesboundary() throws CourseNotFoundException, EmptyCourseListException, CourseLimitException, DuplicateCourseException{
		Course c1 = new Course();
		c1.setCourseCode("ICS31");
		c1.setCourseName("Introduction to Python");
		rm.addCourse(c1);
		assertTrue("Courses[] contains c1",rm.containsCourse(c1));
		rm.deleteCourse("ICS31");
		assertFalse("Courses[] does not contains c1",rm.containsCourse(c1));
		
	}
	
	//test DuplicateCourseException
	@Test(expected=DuplicateCourseException.class)
	public void testAddCourseDuplicateException()
			throws DuplicateCourseException, CourseLimitException
	{
	
		Course c = new Course();
		c.setCourseCode("ICS45C");
		c.setCourseName("Praogramming in C/C++");
		rm.addCourse(c);
		rm.addCourse(c);
	}
	
	//test CourseLimitException
	@Test(expected=CourseLimitException.class)
	public void testAddCourseLimitException()
		throws CourseLimitException, DuplicateCourseException
	{
		
		for(int i = 0; i <=	10; ++i )
		{
			Course c = new Course();
			c.setCourseCode(Integer.toString(i));
			c.setCourseName(" ");
			rm.addCourse(c);
		}
	}
	//test EmptyCourseListException
	@Test(expected=EmptyCourseListException.class)
	public void testDeleteCourseEmptyException()throws CourseNotFoundException,	EmptyCourseListException
	{
		rm.deleteCourse("ICS45J");
	}
	
	//test CourseNotFoundException

	@Test(expected=CourseNotFoundException.class)
	public void testDeleteCourseNotFoundException()
			throws DuplicateCourseException, CourseLimitException, CourseNotFoundException,	
			EmptyCourseListException
	{
		Course c = new Course();
		c.setCourseCode("ICS45C");
		c.setCourseName("Praogramming in C/C++");
		rm.addCourse(c);
		rm.deleteCourse("ICS46");
	}
	
	
	//test EmptyStudentListException
	@Test(expected=EmptyStudentListException.class)
		public void testStudenEmptyExceptiont()
				throws DuplicateCourseException, CourseLimitException, CourseNotFoundException, 
				EmptyStudentListException, StudentNotFoundException, EmptyCourseListException
		{
		Course course = new Course();
		course.setCourseCode("ICS45C");
		course.setCourseName("Praogramming in C/C++");
		rm.addCourse(course);
		rm.deleteStudent(1, "ICS45C");
		}
		
	//test students...

}