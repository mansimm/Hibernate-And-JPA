package com.example.JPAdemo;

import com.example.JPAdemo.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@Transactional
class HibernetBasicApplicationTests {

	@Autowired
	EntityManager entityManager;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Test
	@DirtiesContext//after test is completed data will be reverted to original state
	void TestInsert() {
		Person person = new Person("Naruto");
		entityManager.persist(person);
		entityManager.flush();
		Person person1 = entityManager.find(Person.class, 1);
		Assertions.assertEquals("Naruto",person1.getName());
	}
	@Test
	@DirtiesContext//after test is completed data will be reverted to original state
	void TestMerge() {
		Person person = new Person("Naruto");
		entityManager.persist(person);
		entityManager.flush();
		person.setName("Nine tailed fox kid");
		entityManager.merge(person);
		Assertions.assertEquals("Nine tailed fox kid",person.getName());
	}
	@Test
	@DirtiesContext//after test is completed data will be reverted to original state
	void TestUpdate() {
		Person person = new Person("Naruto");
		entityManager.persist(person);
		entityManager.flush();
		Person person1 = entityManager.find(Person.class, 1);
		person1.setName("Naruto Uzumaki");
		Assertions.assertEquals("Naruto Uzumaki",person1.getName());
	}

	@Test
	@DirtiesContext//after test is completed data will be reverted to original state
	void TestDelete() {
		Person person = new Person("Naruto");
		entityManager.persist(person);
		entityManager.flush();
		Person person1 = entityManager.find(Person.class, 1);
		entityManager.remove(person1);
		Person person2 = entityManager.find(Person.class, 1);
		Assertions.assertNull(person2);
	}

	@Test
	@DirtiesContext//after test is completed data will be reverted to original state
	void TestDetach() {
		Person p1 = new Person("Naruto");
		Person p2 = new Person("Hinata");
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.flush();// flush changes to db immediatly

		entityManager.detach(p1);// since p1 is dettached, it's changes will not reflect in persistent contexct and db
		//em.clear()// detach all the entities
		p1.setName("Naruto Uzyumaki");
		p2.setName("Hinata Hyuga");

		Person person1 = entityManager.find(Person.class, 1);
		Person person2 = entityManager.find(Person.class, 2);

		Assertions.assertEquals("Naruto",person1.getName());
		Assertions.assertEquals("Hinata Hyuga",person2.getName());
	}

	@Test
	@DirtiesContext//after test is completed data will be reverted to original state
	void TestClear() {
		Person p1 = new Person("Naruto");
		Person p2 = new Person("Hinata");
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.flush();// flush changes to db immediatly

		entityManager.clear();
		//em.clear()// detach all the entities
		p1.setName("Naruto Uzyumaki");
		p2.setName("Hinata Hyuga");

		Person person1 = entityManager.find(Person.class, 1);
		Person person2 = entityManager.find(Person.class, 2);

		Assertions.assertEquals("Naruto",person1.getName());
		Assertions.assertEquals("Hinata",person2.getName());
	}

	@Test
	@DirtiesContext//after test is completed data will be reverted to original state
	void TestRefresh() {
		Person p1 = new Person("Naruto");
		Person p2 = new Person("Hinata");
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.flush();// flush changes to db immediatly

		p1.setName("Naruto Uzyumaki");
		p2.setName("Hinata Hyuga");

		entityManager.refresh(p1);//p1 will be refreshed from db and changes made will be lost

		Person person1 = entityManager.find(Person.class, 1);
		Person person2 = entityManager.find(Person.class, 2);

		Assertions.assertEquals("Naruto",person1.getName());
		Assertions.assertEquals("Hinata Hyuga",person2.getName());
	}

}
