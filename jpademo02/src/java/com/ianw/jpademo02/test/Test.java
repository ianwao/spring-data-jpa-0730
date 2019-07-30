package com.ianw.jpademo02.test;



import com.ianw.jpademo02.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;




public class Test {

	public static void main(String[] args) {

		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaText");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

		//持久化操作
		//addPerson(entityManager);
		//addAddress(entityManager);
		//addStudent(entityManager);
		//addClasses(entityManager);
		//addUser(entityManager);

		//updateStudent(entityManager);
		//delStudent(entityManager);

		//addAuthor(entityManager);
		// addArticle(entityManager);
		//searchMsg(entityManager, "测试数据1");
		//delAuthor(entityManager,"测试数据1");
		//searchByNameQuery(entityManager);








		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	private static void delStudent(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		int rs = entityManager.createQuery("delete from Student where sid=?1")
				.setParameter("1",5).executeUpdate();
		System.out.println(rs);

		// 5. 提交事务
		transaction.commit();
	}

	private static void updateStudent(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		 int rs =entityManager.createQuery("update Student s set s.studnetname = ?1 where sid=?2")
				.setParameter("1","小华")
				.setParameter("2",4).executeUpdate();
		 System.out.println("更新了"+rs);

		// 5. 提交事务
		transaction.commit();

	}

	private static void searchByNameQuery(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		List<People> list = new ArrayList<People>();
		list = entityManager.createNamedQuery("people.all").getResultList();for (People p:list
             ) {
            System.out.println("人员信息："+p.toString());
        }

		// 5. 提交事务
		transaction.commit();
	}

	private static void delAuthor(EntityManager entityManager,String name) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		int re = entityManager
				.createQuery("delete from Author a where a.name = ?1")
				.setParameter("1",name).executeUpdate();
		System.out.println("删除了"+re);
		// 5. 提交事务
		transaction.commit();
	}

	private static void searchMsg(EntityManager entityManager, String name) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		Author author = entityManager.createQuery("select a from Author a where a.name = ?1",Author.class)
				.setParameter("1",name).getSingleResult();
		System.out.println(author.toString());
		// 5. 提交事务
		transaction.commit();
	}

	private static void addArticle(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		Article article = new Article();
		article.setTitle("测试数据2");
		article.setContent("测试数据2");

		Author author = new Author();
		author.setName("测试数据2");

		article.setAuthor(author);
		// 添加user到数据库，相当于hibernate的save();
		//entityManager.persist(author);
		entityManager.persist(article);

		// 5. 提交事务
		transaction.commit();

	}

	private static void addAuthor(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		Author author = new Author();
		author.setName("测试数据1");
		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(author);


		// 5. 提交事务
		transaction.commit();
	}

	private static void addUser(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		List<User> userList = new ArrayList<>();
		User user = new User();
		user.setUsername("aaa1");
		user.setPassword("123456");

		List<Authority> authorityList = new ArrayList<>();
		Authority authority = new Authority();
		authority.setName("bbb1");
		authorityList.add(authority);

		user.setAuthorityList(authorityList);
		//authority.setUserList(userList);
		userList.add(user);

		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(user);
		entityManager.persist(authority);

		// 5. 提交事务
		transaction.commit();
	}

	private static void addClasses(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		Classes c = new Classes();
		c.setClassname("一班");

		List<Student> list = new ArrayList<>();
		Student student = new Student() ;
		student.setStudnetname("小明");
		list.add(student);

		c.setStudent(list);
		//student.setClasses(c);

		// 添加user到数据库，相当于hibernate的save();

		entityManager.persist(student);
		entityManager.persist(c);

		// 5. 提交事务
		transaction.commit();
	}

	private static void addStudent(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		Student s = new Student();
		s.setStudnetname("王小明");

		// 添加user到数据库，相当于hibernate的save();
		entityManager.remove(s);

		// 5. 提交事务
		transaction.commit();
	}

	private static void addAddress(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		People p = new People();
		p.setName("小红");
		p.setSex("女");
		Address a = new Address();
		a.setAddress("北京");
		p.setAddress(a);

		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(a);
		entityManager.persist(p);

		// 5. 提交事务
		transaction.commit();
	}

	private static void addPerson(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		People p = new People();
		p.setName("小明");
		p.setSex("男");
		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(p);

		// 5. 提交事务
		transaction.commit();
	}
}
