package com.example.JPAdemo;

import com.example.JPAdemo.entity.*;
import com.example.JPAdemo.hibernateRepository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.JPAdemo.hibernateRepository.PersonRepository;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@Transactional
public class JpAdemoApplication implements CommandLineRunner {

	@Autowired
	PersonRepository repository;
	@Autowired
	EntityManager entityManager;
	@Autowired
	EmployeeRepository employeeRepo;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(JpAdemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//testBasicCrud();
		//testOneToOne();
		testManyToMany();
		//testOneToMany();
		testEmployeeInheritace();
	}

	//hibernet
	@Transactional
	public void testOneToOne(){
		Passport passport = new Passport(1234);
		Student student = new Student("Jenny");
		student.setPassport(passport);
		entityManager.persist(student);
		entityManager.flush();

		Student student1 = entityManager.find(Student.class, 1);
		logger.info("=====> Student is : "+student1);
	}

	//hibernet
	public void testManyToMany(){
		Student student = new Student("Jonny");
		Course course1 = new Course("Java in 100 steps");
		Course course2 = new Course("Hibernate in 100 steps");
		student.addCourse(course1);
		student.addCourse(course2);
		entityManager.persist(student);
		entityManager.flush();

		TypedQuery query = entityManager.createNamedQuery("FindByStudentName",Student.class);
		query.setParameter(1, "Jonny");
		Student student1 = (Student) query.getSingleResult();
		logger.info("=====> Student is : "+student1);
		logger.info("=====> Courses enrolled are : "+student1.getCourses());
	}
	//hibernet
	public void testOneToMany(){
		Course course = new Course("Java in 100 steps");
		Review review = new Review(Rating.FIVE, "Great course");
		course.addReviews(review);
		entityManager.persist(course);
		entityManager.flush();

		//query
		Query query = entityManager.createQuery("select c from Course c where c.name= :name");
		query.setParameter("name", "Java in 100 steps");
		List<Course> resultList = query.getResultList();
		logger.info("===> Java in 100 steps reviews are : ");
		for(Course c : resultList){
			c.getReviews().stream().forEach(System.out::print);
		}

	}


	@Transactional
	public void testBasicCrud(){
		Person person = repository.findById(1001);
		logger.info("===> Person with id 1001: "+person);

		Person p1 = new Person("Jonny");
		logger.info("===> Saved person is: "+repository.save(p1));

		Person p2 = new Person(1001, "Marco updated");
		logger.info("===> Updated person is: "+repository.update(p2));

		//more
		Person p3 = new Person("Jenny");
		Person p4 = new Person("Rose");

		entityManager.persist(p3);
		p3.setName("Jenny J.");
	}

	@Transactional
	public void testEmployeeInheritace(){
		// for @Inheritance annotation: all are entities
//		employeeRepo.insertEmployee(new PartTimeEmployee("Ino", new BigDecimal(1000)));
//		employeeRepo.insertEmployee(new FullTimeEmployee("Sai", new BigDecimal(100000)));
//		employeeRepo.findAll();

		//for @MappedSuperclass
		employeeRepo.insertEmployee(new PartTimeEmployee("Ino", new BigDecimal(1000)));
		employeeRepo.insertEmployee(new FullTimeEmployee("Sai", new BigDecimal(100000)));
		employeeRepo.findAllPartTimeEmployee();
		employeeRepo.findAllFullTimeEmployee();


	}
}
