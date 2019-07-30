package cn.com.taiji.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import cn.com.taiji.domain.Address;
import cn.com.taiji.domain.People;

public class OneToOneTest {

	public static void addtest(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4.进行持久化操作
		// 4.1 添加数据
		// People people1=new People();
		// people1.setName("花花");
		// people1.setSex("女");
		//
		// Address a1 = new Address();
		// a1.setAddress("广东");
		//
		// people1.setAddress(a1);
		//
		// //执行保存操作
		// entityManager.persist(a1);
		// entityManager.persist(people1);

		// 4.2查询数据
		// 查询的方式
		// 方式一：
		List<Address> list = entityManager.createQuery("select address from Address").getResultList();
		People people = new People();
		people.setName("张三");
		people.setSex("男");
		people.setAddress(list.get(2));
		System.out.println(people.getName());

		// 方式二：
		People p = entityManager.find(People.class, 2);
		System.out.println(p.getName());

		// 5.提交事务
		transaction.commit();
	}

	// 方式二：使用原生的sql进行数据库操作，from后面要对应表名
	public static void searchbyNativeQuery(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<People> listPeople = entityManager.createNativeQuery("select * from people").getResultList();
		System.out.println("------listPeople------" + listPeople.size());

		// 5.提交事务
		transaction.commit();

	}

	// 方式一：使用@NamedQuery(name="address.findAll",query="select a from Address
	// a")和@NamedQuery(name="people.findAll",query="select p from People p")
	// 进行数据库操作，address.findAll一定要对应，，，注意，这里的sql语句中from后对应的是类名
	public static void searchbyNameQuery(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<Address> listAddress = entityManager.createNamedQuery("address.findAll").getResultList();
		List<People> listPeople = entityManager.createNamedQuery("people.findAll").getResultList();
		System.out.println("------listAddress-------" + listAddress.size());
		System.out.println("------listPeople-------" + listPeople.size());
		// 4.进行持久化操作

		// 5.提交事务
		transaction.commit();

	}

	public static void addAddress(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4.进行持久化操作
		// 查询方式一：
		Address address = entityManager.find(Address.class, 4);
		System.out.println("------address-------" + address.getAddress());

		// 5.提交事务
		transaction.commit();

	}

	public static void addPeople(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4.进行持久化操作
		// 查询方式二：
		List<Address> list = entityManager.createQuery("SELECT a FROM Address a ").getResultList();
		People p = new People();
		p.setName("王五");
		p.setSex("男");
		p.setAddress(list.get(0));// 0对应索引的第一个
		System.out.println("------list--------" + list.size());

		// 5.提交事务
		transaction.commit();

	}

	public static void persist(EntityManager entityManager) {
		People people = new People();
		people.setName("张三");
		people.setSex("男");

		Address address = new Address();
		address.setAddress("广东");

		// 设置关联关系
		people.setAddress(address);

		// 执行保存操作,建议先保存不维护关联关系的一方，即没有外间的一方，这样不会多出update语句
		entityManager.persist(people);
		entityManager.persist(address);

	}

	public static void find(EntityManager entityManager) {
		// 先来获取维护关联关系的一方,这时会通过左外连接获取其关联的对象
		// 如不想通过左外连接获取，可以在@OneToOne中将fecht设置为LAZY
		// People people = entityManager.find(People.class, 1);
		// System.out.println(people.getName());
		// System.out.println(people.getAddress().getClass().getName());
		//
		// 先来获取不维护关联关系的一方
		Address address = entityManager.find(Address.class, 1);
		System.out.println(address.getAddress());
		System.err.println(address.getClass().getName());
	}

	public static void remove(EntityManager entityManager) {
		People people = entityManager.find(People.class, 1);
		entityManager.remove(people);

		// 不能通过不维护关系的一方来进行级联删除
		// Address address=entityManager.find(Address.class, 2);
		// entityManager.remove(address);
	}

	public static void update(EntityManager entityManager) {
		People people = entityManager.find(People.class, 2);
		people.getAddress().setAddress("上海");
		
	}

}
