package Lab3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testStudents {
	private RosterManager rm;
	private Course c;
	
	@Before
	public void init(){
		rm =  new RosterManager();	
		c = new Course();
		c.setCourseCode("ICS31");
		c.setCourseName("Introduction to Programming");
		rm.addCourse(c);
	}

	//test EmptyStudentListException
	@Test(expected=EmptyStudentListException.class)
	public void testStudenEmptyExceptiont()
			throws DuplicateCourseException, CourseLimitException, CourseNotFoundException, 
			EmptyStudentListException, StudentNotFoundException
	{
		c.removeStudent(573);
	}
	
	//test add student, normal cases
	@Test
	public void addStudent() throws StudentLimitException, DuplicateStudentException{
		Student s = new Student();
		s.setFirstName("Alice");
		s.setLastName("Lee");
		s.setID(1);
		assertFalse("course c should not contains student s",c.containsStudent(s));
		c.addStudent(s);
		assertTrue("course c should contains student s",c.containsStudent(s));
	}
	
	//test add student, normal cases
	@Test
	public void addStudent2() throws StudentLimitException, DuplicateStudentException{
		Student s = new Student();
		s.setFirstName("Alice");
		s.setLastName("Lee");
		s.setID(1);
		Student s2 = new Student();
		s2.setFirstName("Tina");
		s2.setLastName("Lee");
		s.setID(2);
		assertFalse("course c should not contains student s",c.containsStudent(s));
		assertFalse("course c should not contains student s2",c.containsStudent(s2));
		c.addStudent(s);
		c.addStudent(s2);
		assertTrue("course c should contains student s",c.containsStudent(s));
		assertTrue("course c should contains student s",c.containsStudent(s2));
	}

	//test add student, boundary cases
	@Test
	public void addStudentboundary() throws StudentLimitException, DuplicateStudentException{
		for(int i = 0; i <= 49; ++i)
		{
			Student s = new Student();
			s.setID(i);
			s.setFirstName(" ");
			s.setLastName(" ");
			c.addStudent(s);
			}
		}
	
	//test delete student,normal case
	@Test
	public void deletestudent() throws StudentLimitException, DuplicateStudentException, StudentNotFoundException, EmptyStudentListException{
		Student s = new Student();
		s.setFirstName("Alice");
		s.setLastName("Lee");
		s.setID(1);
		Student s2 = new Student();
		s2.setFirstName("Tina");
		s2.setLastName("Lee");
		s.setID(2);
		c.addStudent(s);
		c.addStudent(s2);
		assertTrue("course c should contains student s",c.containsStudent(s));
		assertTrue("course c should contains student s2",c.containsStudent(s2));
		c.removeStudent(2);
		assertFalse("course c should not contains student s2",c.containsStudent(s2));
		
	}
	
	//test delete student, boundary case
	@Test
	public void deletestudentboundary() throws StudentLimitException, DuplicateStudentException, StudentNotFoundException, EmptyStudentListException{
		Student s = new Student();
		s.setFirstName("Alice");
		s.setLastName("Lee");
		s.setID(1);
		c.addStudent(s);
		assertTrue("course c should contains student s",c.containsStudent(s));
		c.removeStudent(1);
		assertFalse("course c should not contains student s",c.containsStudent(s));
		
	}
	//test StudentLimitException
	@Test(expected=StudentLimitException.class)			
	public void testStudentLimitException() throws StudentLimitException, DuplicateStudentException{
		for(int i = 0; i <= 51; ++i)
		{
			Student s = new Student();
			s.setID(i);
			s.setFirstName(" ");
			s.setLastName(" ");
			c.addStudent(s);
			}
		}
	
	//test DuplicateStudentException
	@Test(expected=DuplicateStudentException.class)
	public void testDuplicateStudentException() throws StudentLimitException, DuplicateStudentException{
		Student s = new Student();
		s.setFirstName("Alice");
		s.setLastName("Lee");
		c.addStudent(s);
		c.addStudent(s);
		
	}
	//test StudentNotFoundException
	
	//test add student, normal cases
		@Test
		public void addStudent() throws StudentLimitException, DuplicateStudentException, CourseLimitException, DuplicateCourseException, CourseNotFoundException, EmptyCourseListException, EmptyStudentListException{
			Course course = new Course();
			course.setCourseCode("ICS45C");
			course.setCourseName("Praogramming in C/C++");
			rm.addCourse(course);
			Student s = new Student();
			s.setFirstName("Alice");
			s.setLastName("Lee");
			s.setID(1);
			assertFalse("course c should not contains student s",course.containsStudent(s));
			rm.addStudent(s, course.getCourseCode());
			assertTrue("course c should contains student s",course.containsStudent(s));
		}
		//test add student, normal cases
		@Test
		public void addStudent2() throws StudentLimitException, DuplicateStudentException, CourseLimitException, DuplicateCourseException, CourseNotFoundException, EmptyCourseListException, EmptyStudentListException{
			Course course = new Course();
			course.setCourseCode("ICS45C");
			course.setCourseName("Praogramming in C/C++");
			rm.addCourse(course);
			Student s = new Student();
			s.setFirstName("Alice");
			s.setLastName("Lee");
			s.setID(1);
			Student s2 = new Student();
			s2.setFirstName("Tina");			
			s2.setLastName("Lee");
			s.setID(2);
			assertFalse("course c should not contains student s",course.containsStudent(s));
			assertFalse("course c should not contains student s2",course.containsStudent(s2));
			rm.addStudent(s, course.getCourseCode());
			rm.addStudent(s2, course.getCourseCode());
			assertTrue("course c should contains student s",course.containsStudent(s));
			assertTrue("course c should contains student s",course.containsStudent(s2));
		}
		
		//test add student, boundary cases
		@Test
		public void addStudentboundary() throws StudentLimitException, DuplicateStudentException, CourseNotFoundException, CourseLimitException, DuplicateCourseException, EmptyCourseListException, EmptyStudentListException{
			Course course = new Course();
			course.setCourseCode("ICS45C");
			course.setCourseName("Praogramming in C/C++");
			rm.addCourse(course);
			for(int i = 0; i <= 49; i++)
			{
				Student s = new Student();
				s.setID(i);
				s.setFirstName(" ");
				s.setLastName(" ");
				rm.addStudent(s,course.getCourseCode());
				
			}
		}
		
	
}
