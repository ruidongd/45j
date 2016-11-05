package Lab3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testLab3 {
	
	private RosterManager rm;
	private Course course;
	
	
	
	//construct object
	@Before
	public void init(){
		rm =  new RosterManager();	
		course = new Course();
		course.setCourseCode( "CS122B");
		course.setCourseName("Introduction to Advanced Programming");
	}
	
	//Normal case
	@Test
	public void testaddcourse1() throws CourseLimitException, DuplicateCourseException, CourseNotFoundException {
		//Normal Case
		Course c1 = new Course();
		c1.setCourseCode("ICS31");
		c1.setCourseName("Introduction to Python");
		rm.addCourse(c1);
		assertTrue("Courses[] contains ICS31",rm.containsCourse(c1.getCourseCode()));
	}
	//Normal case
	@Test
	public void testaddcourse2()throws  CourseLimitException, DuplicateCourseException, CourseNotFoundException {
		Course c2 = new Course();
		c2.setCourseCode("ICS45C");
		c2.setCourseName("Praogramming in C/C++");
		rm.addCourse(c2);
		assertTrue("Courses[] contains ICS45C",rm.containsCourse(c2.getCourseCode()));
	}
	//Normal case
	@Test
	public void testaddcourse3()throws  CourseLimitException, DuplicateCourseException, CourseNotFoundException {
		Course c3 = new Course();
		c3.setCourseCode("ICS46");
		c3.setCourseName("Data Structure Implementation and Analysis");
		rm.addCourse(c3);
		assertTrue("Courses[] contains ICS46",rm.containsCourse(c3.getCourseCode()));
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
		assertTrue("Courses[] contains c1",rm.containsCourse(c1.getCourseCode()));
		rm.deleteCourse("ICS31");
		assertFalse("Courses[] does not contains c1",rm.containsCourse(c1.getCourseCode()));
		assertTrue("Courses[] contains c2",rm.containsCourse(c2.getCourseCode()));
		assertTrue("Courses[] contains c3",rm.containsCourse(c3.getCourseCode()));
	}
	
	//test delete course, boundary case
	@Test
	public void testDeleteCoursesboundary() throws CourseNotFoundException, EmptyCourseListException, CourseLimitException, DuplicateCourseException{
		Course c1 = new Course();
		c1.setCourseCode("ICS31");
		c1.setCourseName("Introduction to Python");
		rm.addCourse(c1);
		assertTrue("Courses[] contains c1",rm.containsCourse(c1.getCourseCode()));
		rm.deleteCourse("ICS31");
		assertFalse("Courses[] does not contains c1",rm.containsCourse(c1.getCourseCode()));
		
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
		
		for(int i = 0; i<=10; ++i )
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
		System.out.println(course.getEnrollment());
		rm.addCourse(course);
		rm.deleteStudent(1, "CS122B");
	}
		
	//test add students,normal case
	@Test
	public void testaddStudents() throws CourseLimitException, DuplicateCourseException, StudentLimitException, CourseNotFoundException, DuplicateStudentException, EmptyCourseListException, EmptyStudentListException{
		rm.addCourse(course);
		Student s = new Student();
		s.setID(1);
		s.setFirstName("Jacky");
		s.setLastName("Lin");
		rm.addStudent(s,course.getCourseCode());	
		assertTrue("course c should contains student s",course.containsStudent(s.getID()));
		
	}
	//test add student, normal cases
	@Test
	public void addStudent2() throws StudentLimitException, DuplicateStudentException, CourseLimitException, DuplicateCourseException, CourseNotFoundException, EmptyCourseListException, EmptyStudentListException{
		rm.addCourse(course);
		
		Student s = new Student();
		s.setFirstName("Bob");
		s.setLastName("Y");
		s.setID(1);
		
		Student s2 = new Student();
		s2.setFirstName("Alice");
		s2.setLastName("Y");
		s.setID(2);
		
		Student s3 = new Student();
		s3.setFirstName("Bob");
		s3.setLastName("A");
		s3.setID(12);
		
		
		Student[] students = new Student[50];
		students[0] = s;
		assertEquals(course.getEnrollment(),0);
		//add student s
		rm.addStudent(s,course.getCourseCode());
		assertArrayEquals(course.getEnrolled(),students);
		assertEquals(course.getEnrollment(),1);
		//insert student s2
		students[0] = s2;
		students[1] = s;
		rm.addStudent(s2,course.getCourseCode());
		assertArrayEquals(course.getEnrolled(),students);
		assertEquals(course.getEnrollment(),2);
		//insert student s3
		students[0] = s3;
		students[1] = s2;
		students[2] = s;
		rm.addStudent(s3,course.getCourseCode());
		assertArrayEquals(course.getEnrolled(),students);
		assertEquals(course.getEnrollment(),3);
		
	}
	//test add student, boundary cases
	@Test
	public void addStudentboundary() throws StudentLimitException, DuplicateStudentException, CourseLimitException, DuplicateCourseException, CourseNotFoundException, EmptyCourseListException, EmptyStudentListException{
		rm.addCourse(course);
		for(int i = 0; i <= 49; ++i)
		{
			Student s = new Student();
			s.setID(i);
			s.setFirstName(" ");
			s.setLastName(" ");
			rm.addStudent(s,course.getCourseCode());
			}
	}
	//test StudentLimitException
	@Test(expected=StudentLimitException.class)			
	public void testStudentLimitException() throws StudentLimitException, DuplicateStudentException, CourseLimitException, DuplicateCourseException, CourseNotFoundException, EmptyCourseListException, EmptyStudentListException{
		rm.addCourse(course);
		for(int i = 0; i <= 51; ++i)
		{
				Student s = new Student();
				s.setID(i);
				s.setFirstName(" ");
				s.setLastName(" ");
				rm.addStudent(s,course.getCourseCode());
			}
	}
	//test DuplicateStudentException
	@Test(expected=DuplicateStudentException.class)
		public void testDuplicateStudentException() throws StudentLimitException, DuplicateStudentException, CourseLimitException,EmptyCourseListException, DuplicateCourseException{
		rm.addCourse(course);
		Student s = new Student();
		s.setFirstName("Alice");
		s.setLastName("Lee");
		rm.addStudent(s, course.getCourseCode());
		rm.addStudent(s, course.getCourseCode());
	}
	//test DeleteStudent,normal case
	@Test
	public void testDeleteStudents1() throws CourseLimitException, DuplicateCourseException, StudentLimitException, CourseNotFoundException, DuplicateStudentException, EmptyCourseListException, EmptyStudentListException, StudentNotFoundException{
		rm.addCourse(course);
		Student s = new Student();
		s.setFirstName("Bob");
		s.setLastName("Y");
		s.setID(1);
		
		Student s2 = new Student();
		s2.setFirstName("Alice");
		s2.setLastName("Y");
		s.setID(2);
		
		Student s3 = new Student();
		s3.setFirstName("Bob");
		s3.setLastName("A");
		s3.setID(12);
		//add three students
		rm.addStudent(s,course.getCourseCode());
		rm.addStudent(s2,course.getCourseCode());
		rm.addStudent(s3,course.getCourseCode());
		//assert enrollment number is 3
		assertEquals(course.getEnrollment(),3);
		//remove s3
		rm.deleteStudent(s3.getID(),course.getCourseCode());
		//assert enrollment number is 3
		assertEquals(course.getEnrollment(),2);
		//assert s3 does not enroll in course
		assertFalse(course.containsStudent(s3.getID()));
		
	}
	//test DeleteStudent, boundary case
	@Test
	public void testDeleteStudentsboundarycase() throws CourseLimitException, DuplicateCourseException, StudentLimitException, CourseNotFoundException, DuplicateStudentException, EmptyCourseListException, EmptyStudentListException, StudentNotFoundException{
		rm.addCourse(course);
		Student s = new Student();
		s.setFirstName("Bob");
		s.setLastName("Y");
		s.setID(1);
		rm.addStudent(s ,course.getCourseCode());
		assertEquals(course.getEnrollment(),1);
		rm.deleteStudent(s.getID(), course.getCourseCode());
		assertEquals(course.getEnrollment(),0);
		
	}
	
	//testStudentNotFoundException
	@Test(expected=StudentNotFoundException.class)
	public void testStudentNotFoundException() throws CourseLimitException, DuplicateCourseException, StudentLimitException, CourseNotFoundException, DuplicateStudentException, EmptyCourseListException, EmptyStudentListException, StudentNotFoundException{
		rm.addCourse(course);
		Student s = new Student();
		s.setFirstName("Bob");
		s.setLastName("Y");
		s.setID(1);
		rm.addStudent(s,course.getCourseCode());
		rm.deleteStudent(5,course.getCourseCode());
	}
	
	
	
	
	
	

}